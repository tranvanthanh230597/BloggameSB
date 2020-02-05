package com.codegym.blog.Repository;

import com.codegym.blog.Model.Blog;
import com.codegym.blog.Model.ICountComment;
import com.codegym.blog.Model.IHomePageBlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends PagingAndSortingRepository<Blog,Long> {
    void deleteAllByCategory_Id(Long id);
    Page<Blog> findAllByCategoryId(Long id,Pageable pageable);
    Page<Blog> findAllByBlogNameContaining(String blogName, Pageable pageable);
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
}
