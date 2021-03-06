package com.codegym.blog.Repository;

import com.codegym.blog.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    Iterable<Comment> findAllByBlog_Id(Long id);
}
