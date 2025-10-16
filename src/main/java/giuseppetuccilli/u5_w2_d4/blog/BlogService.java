package giuseppetuccilli.u5_w2_d4.blog;

import giuseppetuccilli.u5_w2_d4.autore.Autore;
import giuseppetuccilli.u5_w2_d4.autore.AutoreService;
import giuseppetuccilli.u5_w2_d4.exeptions.NotFoundExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    private List<Blog> blogList = new ArrayList<>();
    @Autowired
    private AutoreService autoreService;
    @Autowired
    private BlogRepository blogRepository;

    public Page<Blog> findAll(int pageNumber) {
        Pageable pg = PageRequest.of(pageNumber, 10);
        return blogRepository.findAll(pg);
    }

    public Blog saveBlog(BlogPayload payload) {
        Autore autore = autoreService.findById(payload.getIdAutore());

        Blog newBlog = new Blog(payload.getCategoria(), payload.getTitolo(), payload.getContenuto(), payload.getTempoLettura(), autore);
        blogRepository.save(newBlog);
        System.out.println("blog salvato");
        return newBlog;
    }

    public Blog findById(int id) {
        Optional<Blog> found = blogRepository.findById(id);
        if (found.isPresent()) {
            return found.get();
        } else {
            throw new NotFoundExeption(id);
        }
    }

    public Blog findAndUpdate(int id, BlogPayload payload) {
        Blog found = findById(id);
        found.setCategoria(payload.getCategoria());
        found.setContenuto(payload.getContenuto());
        found.setCover(payload.getCover());
        found.setTitolo(payload.getTitolo());
        found.setTempoLettura(payload.getTempoLettura());
        blogRepository.save(found);
        return found;
    }

    public void findAndDelete(int id) {
        Blog found = findById(id);
        blogRepository.delete(found);

    }
}
