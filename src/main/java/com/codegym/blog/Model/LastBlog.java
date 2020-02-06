package com.codegym.blog.Model;

import com.codegym.blog.Model.Interface.ILastBlog;

public class LastBlog implements ILastBlog {
    String name;
    Long id;
    Integer view;
    Integer count;

    public LastBlog() {
    }

    public LastBlog(String name, Long id, Integer view, Integer count) {
        this.name = name;
        this.id = id;
        this.view = view;
        this.count = count;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Integer getView() {
        return view;
    }

    public void setView(Integer view) {
        this.view = view;
    }

    @Override
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
