package com.codegym.blog.Repository;

import com.codegym.blog.Model.Blog;
import com.codegym.blog.Model.Interface.ICountComment;
import com.codegym.blog.Model.Interface.IHomePageBlog;
import com.codegym.blog.Model.LastBlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends PagingAndSortingRepository<Blog,Long> {
    void deleteAllByCategory_Id(Long id);
    Page<Blog> findAllByOrderByIdDesc(Pageable pageable);
    Page<Blog> findAllByCategoryIdOrderByIdDesc(Long id,Pageable pageable);
    Page<Blog> findAllByBlogNameContaining(String blogName, Pageable pageable);
    Page<Blog> findAllByBlogNameContainingOrderByIdDesc(String blogName, Pageable pageable);
    @Query(
            value = "SELECT * FROM bloggame.blogs \n" +
                    "WHERE id < ?1\n" +
                    "ORDER BY id DESC\n" +
                    "LIMIT 1",
            nativeQuery = true)
    Blog  previousBlog(Long id);

    @Query(
            value = "SELECT * FROM bloggame.blogs \n" +
                    "WHERE id > ?1\n" +
                    "LIMIT 1\n",
            nativeQuery = true)
    Blog  nextBlog(Long id);


    @Query(
            value = "SELECT count(comments.id) AS count \n" +
                    "FROM blogs LEFT JOIN comments ON blogs.id = comments.blog_id\n" +
                    "WHERE blogs.id = ?1\n",
            nativeQuery = true)
    Iterable<ICountComment> countComment(Long blogId);
    @Query(
            value = "SELECT blogs.id AS id , blogs.blog_name AS blogName, blogs.view AS view, blogs.description AS description, blogs.date AS date, categories.id AS categoryId, categories.name AS categoryName, blogs.image AS image \n" +
                    "FROM blogs INNER JOIN categories ON blogs.category_id = categories.id\n" +
                    "ORDER BY blogs.id DESC \n" +
                    "LIMIT 5 \n",
            nativeQuery = true
    )
    Iterable<IHomePageBlog> homePageBlog();

    @Query(
            value = "SELECT blogs.blog_name AS name , blogs.id AS id, blogs.view AS view, count(comments.id) AS count \n" +
                    "FROM blogs LEFT JOIN comments ON blogs.id = comments.blog_id\n" +
                    "GROUP BY blogs.id\n" +
                    "limit 3",
            nativeQuery = true)
    Iterable<LastBlog> lastBlog();
}
