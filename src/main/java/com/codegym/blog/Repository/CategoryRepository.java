package com.codegym.blog.Repository;

import com.codegym.blog.Model.Category;
import com.codegym.blog.Model.Interface.ICountBlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    @Query(
            value = "SELECT categories.id AS id, categories.name AS name , count(blogs.id) AS count \n" +
                    "FROM categories LEFT JOIN blogs ON categories.id = blogs.category_id\n" +
                    "GROUP BY categories.id",
            nativeQuery = true)
    Iterable<ICountBlog> countBlogs();
}
