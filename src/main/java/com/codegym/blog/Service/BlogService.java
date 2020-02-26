package com.codegym.blog.Service;

import com.codegym.blog.Model.Blog;
import com.codegym.blog.Model.Interface.ICountComment;
import com.codegym.blog.Model.Interface.IHomePageBlog;
import com.codegym.blog.Model.LastBlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface BlogService extends BaseService<Blog>{
    Page<Blog> findAll(Pageable pageable);
    void deleteAllByCategory_Id(Long category_id);
    Page<Blog> findAllByCategoryId(Long id,Pageable pageable);
    Blog  previousBlog(Long id);
    Blog  nextBlog(Long id);
    Iterable<Blog> latestBlog();
    Page<Blog> findAllByBlogNameContaining(String blogName, Pageable pageable);
    Iterable<ICountComment> countComment(Long blogId);
    Iterable<IHomePageBlog> homePageBlog();
    Page<Blog> findAllByOrderByIdDesc(Pageable pageable);
    Page<Blog> findAllByBlogNameContainingOrderByIdDesc(String blogName, Pageable pageable);
    Iterable<LastBlog> lastBlog();
    void uploadFile(MultipartFile file) throws IOException;


}
