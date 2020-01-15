package com.codegym.blog.Service;

import com.codegym.blog.Model.Comment;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    void save(Comment comment);
    Iterable<Comment> findAllByBlog_Id(Long id);

}
