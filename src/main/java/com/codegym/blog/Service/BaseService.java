package com.codegym.blog.Service;

public interface BaseService<T> {
    Iterable<T> findAll();
    T findById(Long id);
    void save(T t);
    void remove(Long id);
}
