package com.codegym.blog.Service;

import com.codegym.blog.Model.Blog;
import com.codegym.blog.Model.ICountComment;
import com.codegym.blog.Model.IHomePageBlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface BlogService {
    Iterable<Blog> findAll();
    Page<Blog> findAll(Pageable pageable);
    Blog findById(Long id);
    void save(Blog blog);
    void remove(Long id);
    void deleteAllByCategory_Id(Long category_id);
    Page<Blog> findAllByCategoryId(Long id,Pageable pageable);
    Blog  previousBlog(Long id);
    Blog  nextBlog(Long id);
    Page<Blog> findAllByBlogNameContaining(String blogName, Pageable pageable);
    Iterable<ICountComment> countComment(Long blogId);
    Iterable<IHomePageBlog> homePageBlog();

}
