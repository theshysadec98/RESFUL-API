package Services;

import Models.Blog;
import Repository.IBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class BlogServices implements IBlogServices{
    @Autowired
    IBlogRepository iBlogRepository;
    @Override
    public Iterable<Blog> findAll() {
        return iBlogRepository.findAll();
    }

    @Override
    public Optional<Blog> findById(Long id) {
        return iBlogRepository.findById(id);
    }

    @Override
    public Blog save(Blog blog) {
        return iBlogRepository.save(blog);
    }

    @Override
    public void remove(Long id) {
        iBlogRepository.deleteById(id);
    }
}
