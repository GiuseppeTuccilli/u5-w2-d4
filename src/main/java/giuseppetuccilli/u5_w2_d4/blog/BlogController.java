package giuseppetuccilli.u5_w2_d4.blog;

import giuseppetuccilli.u5_w2_d4.exeptions.ValidExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/blogs")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @GetMapping
    public Page<Blog> getBlogs(@RequestParam(defaultValue = "0") int pageNumber) {
        return blogService.findAll(pageNumber);
    }

    @GetMapping("/{blogId}")
    public Blog getById(@PathVariable int blogId) {
        return blogService.findById(blogId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Blog creatBlog(@RequestBody @Validated BlogPayload body, BindingResult valRes) {
        if (valRes.hasErrors()) {
            List<String> errList = new ArrayList<String>();
            for (int i = 0; i < valRes.getFieldErrors().size(); i++) {
                errList.add(valRes.getFieldErrors().get(i).getDefaultMessage());
            }
            throw new ValidExeption(errList);
        }
        return blogService.saveBlog(body);
    }

    @PutMapping("/{blogId}")
    public Blog editBlog(@PathVariable int blogId, @RequestBody BlogPayload body) {
        return blogService.findAndUpdate(blogId, body);
    }

    @DeleteMapping("/{blogId}")
    public void deleteBlog(@PathVariable int blogId) {
        this.blogService.findAndDelete(blogId);
    }
}
