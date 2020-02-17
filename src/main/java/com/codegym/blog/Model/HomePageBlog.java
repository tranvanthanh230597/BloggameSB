package com.codegym.blog.Model;

import com.codegym.blog.Model.Interface.IHomePageBlog;

public class HomePageBlog implements IHomePageBlog {
    private Long id;
    private String blogName;
    private String description;
    private String date;
    private Long categoryId;
    private String categoryName;
    private Integer view;
    private String image;

    public HomePageBlog(Long id, String blogName, String description, String date, Long categoryId, String categoryName,Integer view,String image) {
        this.id = id;
        this.blogName = blogName;
        this.description = description;
        this.date = date;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.view = view;
        this.image = image;
    }

    public HomePageBlog() {
    }

    @Override
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public Integer getView() {
        return view;
    }

    public void setView(Integer view) {
        this.view = view;
    }
}
