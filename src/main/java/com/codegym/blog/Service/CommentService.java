package com.codegym.blog.Service;

import com.codegym.blog.Model.Comment;
import org.springframework.stereotype.Service;

@Service
public interface CommentService extends BaseService<Comment>{
    Iterable<Comment> findAllByBlog_Id(Long id);
}
