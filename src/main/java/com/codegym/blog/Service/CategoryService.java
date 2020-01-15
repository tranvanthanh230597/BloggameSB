package com.codegym.blog.Service;

import com.codegym.blog.Model.Category;
import com.codegym.blog.Model.ICountBlog;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface CategoryService {
    Iterable<Category> findAll();

    Category findById(Long id);

    void save(Category category);

    void remove(Long id);
    Iterable<ICountBlog> countBlogs();
}
