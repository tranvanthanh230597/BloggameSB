package com.codegym.blog.Model;

public class CountBlog implements ICountBlog {
    private String name;
    private Long count;
    private Long id;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CountBlog(String name, Long count, Long id) {
        this.name = name;
        this.count = count;
        this.id = id;
    }

    public CountBlog() {
    }
}
