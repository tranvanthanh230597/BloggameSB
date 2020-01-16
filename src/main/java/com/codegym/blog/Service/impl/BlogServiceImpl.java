package com.codegym.blog.Service.impl;

import com.codegym.blog.Model.ICountComment;
import com.codegym.blog.Repository.BlogRepository;
import com.codegym.blog.Service.BlogService;
import com.codegym.blog.Model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository blogRepository;
    @Override
    public Iterable<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public Blog findById(Long id) {
        return blogRepository.findById(id).get();
    }

    @Override
    public void save(Blog blog) {
        blogRepository.save(blog);
    }

    @Override
    public void remove(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public void deleteAllByCategory_Id(Long category_id) {
        blogRepository.deleteAllByCategory_Id(category_id);
    }

    @Override
    public Iterable<Blog> findAllByCategoryId(Long id) {
        return blogRepository.findAllByCategoryId(id);
    }

    @Override
    public Blog previousBlog(Long id) {
        return blogRepository.previousBlog(id);
    }

    @Override
    public Blog nextBlog(Long id) {
        return blogRepository.nextBlog(id);
    }

    @Override
    public Iterable<Blog> findAllByBlogNameContaining(String blogName) {
        return blogRepository.findAllByBlogNameContaining(blogName);
    }

    @Override
    public Iterable<ICountComment> countComment(Long blogId) {
        return blogRepository.countComment(blogId);
    }
}