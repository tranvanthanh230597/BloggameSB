package com.codegym.blog.Service;

import com.codegym.blog.Model.Category;
import com.codegym.blog.Model.Interface.ICountBlog;
import org.springframework.stereotype.Service;


@Service
public interface CategoryService extends BaseService<Category>{
    Iterable<ICountBlog> countBlogs();
}
