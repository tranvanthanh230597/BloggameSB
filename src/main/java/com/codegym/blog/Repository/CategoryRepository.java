package com.codegym.blog.Repository;

import com.codegym.blog.Model.Category;
import com.codegym.blog.Model.ICountBlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    @Query(
            value = "SELECT categories.id as id, categories.name as name , count(blogs.id) as count \n" +
                    "FROM categories left join blogs on categories.id = blogs.category_id\n" +
                    "group by categories.id",
            nativeQuery = true)
    Iterable<ICountBlog> countBlogs();
}