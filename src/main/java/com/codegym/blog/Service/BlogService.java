package com.codegym.blog.Service;

import com.codegym.blog.Model.Blog;
import com.codegym.blog.Model.ICountComment;
import org.aspectj.bridge.ICommand;
import org.springframework.stereotype.Service;
import org.thymeleaf.model.IComment;

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
    Iterable<ICountComment> countComment(Long blogId);


}
