package com.codegym.blog.Model;

public class CountComment implements ICountComment {
    private Long count;

    public CountComment() {
    }

    public CountComment(Long count) {
        this.count = count;
    }

    @Override
    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
