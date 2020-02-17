package com.codegym.blog.Service.impl;

import com.codegym.blog.Model.Interface.ICountComment;
import com.codegym.blog.Model.Interface.IHomePageBlog;
import com.codegym.blog.Model.LastBlog;
import com.codegym.blog.Repository.BlogRepository;
import com.codegym.blog.Service.BlogService;
import com.codegym.blog.Model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Configuration
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository blogRepository;
    @Override
    public Iterable<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return blogRepository.findAll(pageable);
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
    public Page<Blog> findAllByCategoryId(Long id,Pageable pageable) {
        return blogRepository.findAllByCategoryIdOrderByIdDesc(id,pageable);
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
    public Page<Blog> findAllByBlogNameContaining(String blogName, Pageable pageable) {
        return blogRepository.findAllByBlogNameContaining(blogName,pageable);
    }

    @Override
    public Iterable<ICountComment> countComment(Long blogId) {
        return blogRepository.countComment(blogId);
    }

    @Override
    public Iterable<IHomePageBlog> homePageBlog() {
        return blogRepository.homePageBlog();
    }

    @Override
    public Page<Blog> findAllByOrderByIdDesc(Pageable pageable) {
        return blogRepository.findAllByOrderByIdDesc(pageable);
    }

    @Override
    public Page<Blog> findAllByBlogNameContainingOrderByIdDesc(String blogName, Pageable pageable) {
        return blogRepository.findAllByBlogNameContainingOrderByIdDesc(blogName,pageable);
    }

    @Override
    public Iterable<LastBlog> lastBlog() {
        return blogRepository.lastBlog();
    }

    @Override
    public void uploadFile(MultipartFile file) throws IOException {
        file.transferTo(new File("C:\\Users\\tranv\\Desktop\\Module02_Spring\\BloggameSB\\src\\main\\resources\\static\\source\\images\\ " + file.getOriginalFilename()));
    }
}