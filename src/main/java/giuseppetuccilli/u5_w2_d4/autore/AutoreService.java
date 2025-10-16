package giuseppetuccilli.u5_w2_d4.autore;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import giuseppetuccilli.u5_w2_d4.blog.Blog;
import giuseppetuccilli.u5_w2_d4.blog.BlogRepository;
import giuseppetuccilli.u5_w2_d4.exeptions.BadRequestExeption;
import giuseppetuccilli.u5_w2_d4.exeptions.InvalidDateStringExeption;
import giuseppetuccilli.u5_w2_d4.exeptions.NotFoundExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AutoreService {
    @Autowired
    private AutoreRepository autoreRepository;
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private Cloudinary imgUploader;


    public Page<Autore> fidAll(int pageNumber) {
        Pageable pg = PageRequest.of(pageNumber, 10);
        return autoreRepository.findAll(pg);
    }

    //prende la data in stringa dal json e ritorna localdate
    private LocalDate getData(String data) {
        String dataString = "";
        if (data.length() > 10) {
            dataString = data.substring(0, 10);
        } else if (data.length() == 10) {
            dataString = data;
        } else {
            throw new InvalidDateStringExeption(data);
        }
        try {
            String[] dataArray = dataString.split("-");
            int anno = Integer.parseInt(dataArray[0]);
            int mese = Integer.parseInt(dataArray[1]);
            int giorno = Integer.parseInt(dataArray[2]);
            LocalDate dataNascita = LocalDate.of(anno, mese, giorno);
            return dataNascita;
        } catch (Exception ex) {
            throw new BadRequestExeption("data non valida");
        }

    }

    public Autore findById(int autId) {
        Optional<Autore> found = autoreRepository.findById(autId);
        if (found.isPresent()) {
            return found.get();
        } else {
            throw new NotFoundExeption(autId);
        }
    }

    public Autore editAutore(int id, AutorePayload payload) {
        Autore found = findById(id);
        found.setNome(payload.getNome());
        found.setCognome(payload.getCognome());
        if (!payload.getEmail().equals(found.getEmail())) {
            if (autoreRepository.existsByEmail(payload.getEmail())) {
                throw new BadRequestExeption("email già presente");
            }
        }
        found.setEmail(payload.getEmail());
        LocalDate d = this.getData(payload.getDataNascita());
        found.setDataNascita(d);
        found.setAvatar(payload.getAvatar());
        autoreRepository.save(found);
        return found;
    }

    public Autore saveAutore(AutorePayload payload) {
        //la data che arriva da un frontend in formato Date è una stringa
        LocalDate dataNascita = this.getData(payload.getDataNascita());
        if (autoreRepository.existsByEmail(payload.getEmail())) {
            throw new BadRequestExeption("l'email " + payload.getEmail() + " p già in uso");
        }
        Autore newAut = new Autore(dataNascita, payload.getEmail(), payload.getCognome(), payload.getNome());
        autoreRepository.save(newAut);
        System.out.println("autore salvato");
        return newAut;
    }

    public void deleteAutore(int id) {
        Autore found = findById(id);
        List<Blog> blogList = blogRepository.findByAutore(found);
        if (!blogList.isEmpty()) {
            for (int i = 0; i < blogList.size(); i++) {
                blogRepository.delete(blogList.get(i));
            }
        }
        autoreRepository.delete(found);
        System.out.println("autore eliminato");
    }

    public Autore changeAvatar(MultipartFile file, int id) {
        //controllo utente
        Autore found = findById(id);

        if (file.isEmpty()) {
            throw new BadRequestExeption("file vuoto");
        }
        try {
            Map res = imgUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String imgUrl = (String) res.get("url");
            found.setAvatar(imgUrl);
            autoreRepository.save(found);
            return found;
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }

    }
}
