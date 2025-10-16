package giuseppetuccilli.u5_w2_d4.autore;

import giuseppetuccilli.u5_w2_d4.exeptions.ValidExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/autori")
public class AutoreController {
    @Autowired
    private AutoreService autoreService;

    @GetMapping
    public Page<Autore> getAutori(@RequestParam(defaultValue = "0") int pageNumber) {
        return autoreService.fidAll(pageNumber);
    }

    @GetMapping("/{autId}")
    public Autore getAutore(@PathVariable int autId) {
        return autoreService.findById(autId);
    }

    @PostMapping
    public Autore postAutore(@RequestBody @Validated AutorePayload body, BindingResult valRes) {
        if (valRes.hasErrors()) {
            List<String> errsList = new ArrayList<String>();
            for (int i = 0; i < valRes.getFieldErrors().size(); i++) {
                errsList.add(valRes.getFieldErrors().get(i).getDefaultMessage());
            }
            throw new ValidExeption(errsList);
        }
        return autoreService.saveAutore(body);
    }

    @PutMapping("/{autId}")
    public Autore editAutore(@PathVariable int autId, @RequestBody AutorePayload body) {
        return autoreService.editAutore(autId, body);
    }

    @DeleteMapping("/{autId}")
    public void deleteAutore(@PathVariable int autId) {
        autoreService.deleteAutore(autId);
        System.out.println("autore eliminato");
    }
}
