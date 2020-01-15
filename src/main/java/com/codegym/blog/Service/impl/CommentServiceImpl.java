package com.codegym.blog.Service.impl;

import com.codegym.blog.Model.Comment;
import com.codegym.blog.Repository.CommentRepository;
import com.codegym.blog.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
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
}
