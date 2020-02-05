package com.codegym.blog.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments")
@Data
    public class Comment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String comment;
    private Date date;
    @ManyToOne
    @JoinColumn(name = "blog_id")
    private Blog blog;
}
