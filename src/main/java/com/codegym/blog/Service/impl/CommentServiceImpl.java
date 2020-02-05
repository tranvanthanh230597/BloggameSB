package com.codegym.blog.Service.impl;

import com.codegym.blog.Model.Comment;
import com.codegym.blog.Repository.CommentRepository;
import com.codegym.blog.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Override
    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public Iterable<Comment> findAllByBlog_Id(Long id) {
        return commentRepository.findAllByBlog_Id(id);
    }

    @Override
    public Iterable<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id).get();
    }

    @Override
    public void remove(Long id) {
        commentRepository.deleteById(id);
    }
}
