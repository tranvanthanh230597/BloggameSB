package com.codegym.blog.Service;

import com.codegym.blog.Model.Blog;
import org.springframework.stereotype.Service;

@Service
public interface BlogService {
    Iterable<Blog> findAll();

    Blog findById(Long id);

    void save(Blog blog);

    void remove(Long id);

    void deleteAllByCategory_Id(Long category_id);
    Iterable<Blog> findAllByCategoryId(Long id);
    Blog  previousBlog(Long id);
    Blog  nextBlog(Long id);
    Iterable<Blog> findAllByBlogNameContaining(String blogName);


}
