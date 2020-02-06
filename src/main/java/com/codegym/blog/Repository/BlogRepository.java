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
    @Query(
            value = "SELECT blogs.id as id , blogs.blog_name as blogName, blogs.view as view, blogs.description as description, blogs.date as date, categories.id as categoryId, categories.name as categoryName\n" +
                    "FROM blogs inner join categories on blogs.category_id = categories.id\n" +
                    "ORDER BY blogs.id DESC \n" +
                    "limit 5 \n",
            nativeQuery = true
    )
    Iterable<IHomePageBlog> homePageBlog();

    @Query(
            value = "SELECT blogs.blog_name as name , blogs.id as id, blogs.view as view, count(comments.id) as count \n" +
                    "FROM blogs left JOIN comments on blogs.id = comments.blog_id\n" +
                    "group by blogs.id\n" +
                    "limit 3",
            nativeQuery = true)
    Iterable<LastBlog> lastBlog();
}
