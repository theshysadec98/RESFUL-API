package Controllers;


import Models.Blog;
import Services.IBlogServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/home/Blogs")
public class HomeControllers {
    @Autowired
    IBlogServices blogServices;

    @GetMapping
    public ResponseEntity<Iterable<Blog>> home(){
        List<Blog> list = (List<Blog>) blogServices.findAll();

        if(list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> findBlog(@PathVariable Long id){
        Optional<Blog> blog = blogServices.findById(id);
        if(!blog.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(blog.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Blog> createBlog(@RequestBody Blog blog){
        return new ResponseEntity<>(blogServices.save(blog), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Blog> editBlog(@PathVariable Long id, @RequestBody Blog blog){
        Optional<Blog> list = blogServices.findById(id);
        if(!list.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        blog.setId(list.get().getId());
        return new ResponseEntity<>(blogServices.save(blog), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Blog> deleteBlog(@PathVariable Long id){
        Optional<Blog> blog =  blogServices.findById(id);
        if(!blog.isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        blogServices.remove(id);
        return new ResponseEntity<>( blog.get(), HttpStatus.OK);
    }
}
