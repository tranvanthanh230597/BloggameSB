package com.codegym.blog.Model;

public interface IHomePageBlog {
    Long getId();
    String getBlogName();
    String getDescription();
    String getDate();
    Long getCategoryId();
    String getCategoryName();
    Integer getView();
}
