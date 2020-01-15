package com.codegym.blog.Service.impl;

import com.codegym.blog.Model.Category;
import com.codegym.blog.Model.ICountBlog;
import com.codegym.blog.Repository.BlogRepository;
import com.codegym.blog.Repository.CategoryRepository;
import com.codegym.blog.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void remove(Long id) {
        categoryRepository.deleteById(id);
        blogRepository.deleteAllByCategory_Id(id);
    }

    @Override
    public Iterable<ICountBlog> countBlogs() {
        return categoryRepository.countBlogs();
    }


}
