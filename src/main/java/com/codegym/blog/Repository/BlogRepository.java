package com.codegym.blog.Repository;

import com.codegym.blog.Model.Blog;
import com.codegym.blog.Model.ICountComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BlogRepository extends JpaRepository<Blog,Long> {
    void deleteAllByCategory_Id(Long id);
    Iterable<Blog> findAllByCategoryId(Long id);
    Iterable<Blog> findAllByBlogNameContaining(String blogName);
    @Query(
            value = "SELECT * FROM bloggame.blogs \n" +
                    "where id < ?1\n" +
                    "order by id desc\n" +
                    "limit 1",
            nativeQuery = true)
    Blog  previousBlog(Long id);

    @Query(
            value = "SELECT * FROM bloggame.blogs \n" +
                    "where id > ?1\n" +
                    "limit 1\n",
            nativeQuery = true)
    Blog  nextBlog(Long id);


    @Query(
            value = "SELECT count(comments.id) as count \n" +
                    "FROM blogs left join comments on blogs.id = comments.blog_id\n" +
                    "where blogs.id = ?1\n",
            nativeQuery = true)
    Iterable<ICountComment> countComment(Long blogId);
}
