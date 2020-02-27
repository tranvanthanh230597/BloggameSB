package com.codegym.blog.Controller;


import com.codegym.blog.Model.*;
import com.codegym.blog.Model.Interface.ICountBlog;
import com.codegym.blog.Model.Interface.ICountComment;
import com.codegym.blog.Model.Interface.IHomePageBlog;
import com.codegym.blog.Service.BlogService;
import com.codegym.blog.Service.CategoryService;
import com.codegym.blog.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Iterator;
import java.util.Optional;

@Controller
public class HomePageController {
    @Autowired
    BlogService blogService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    CommentService commentService;
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home(){
        Iterable<IHomePageBlog> blogs = blogService.homePageBlog();
        Iterable<Blog> latestBlog = blogService.latestBlog();
        ModelAndView modelAndView = new ModelAndView("/userPage/homePage");
        modelAndView.addObject("blogList",blogs);
        modelAndView.addObject("latestBlogList",latestBlog);
        modelAndView.addObject("infoContact",new Email());
        return modelAndView;
    }
    @RequestMapping(value = "/blogUser", method = RequestMethod.GET)
    public ModelAndView blog(@RequestParam("s") Optional<String> s, Pageable pageable){
        Page<Blog> blogs;
        Iterable<ICountBlog> iCountBlogs = categoryService.countBlogs();
        if (s.isPresent()) {
            blogs = blogService.findAllByBlogNameContainingOrderByIdDesc(s.get(),pageable);
        } else {
            blogs = blogService.findAllByOrderByIdDesc(pageable);
        }
        Iterable<Category> categories = categoryService.findAll();
        ModelAndView modelAndView = new ModelAndView("/userPage/blog");
        modelAndView.addObject("iCountBlogs", iCountBlogs);
        modelAndView.addObject("blogList",blogs);
        modelAndView.addObject("categoryList",categories);
        return modelAndView;
    }
    @RequestMapping(value = "/blogUser/{id}", method = RequestMethod.GET)
    public ModelAndView blogById(@PathVariable long id,@RequestParam("s") Optional<String> s , Pageable pageable) {
        Page<Blog> blogs;
        if (s.isPresent()) {
            blogs = blogService.findAllByBlogNameContainingOrderByIdDesc(s.get(),pageable);
        } else {
            blogs = blogService.findAllByCategoryId(id,pageable);
        }
        Iterable<ICountBlog> iCountBlogs = categoryService.countBlogs();
        if (blogs != null) {
            Iterable<Category> categories = categoryService.findAll();
            ModelAndView modelAndView = new ModelAndView("/userPage/blog");
            modelAndView.addObject("blogList", blogs);
            modelAndView.addObject("categoryList", categories);
            modelAndView.addObject("iCountBlogs", iCountBlogs);
            return modelAndView;
        } else {
            return new ModelAndView("/error.404");
        }
    }
    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    public ModelAndView post(@PathVariable long id) {
        Iterable<ICountBlog> iCountBlogs = categoryService.countBlogs();
        Iterable<Comment> comments = commentService.findAllByBlog_Id(id);
        Iterable<ICountComment> countComments = blogService.countComment(id);
        Long countOfComment = 0L;
        Iterator<ICountComment> iter = countComments.iterator();
        while(iter.hasNext()){
            countOfComment = iter.next().getCount();
        }

        Blog blog = blogService.findById(id);
        if (blog != null) {
            blog.setView(blog.getView() + 1);
            blogService.save(blog);
            Iterable<Category> categories = categoryService.findAll();
            ModelAndView modelAndView = new ModelAndView("/userPage/post");
            Blog previousBlog = blogService.previousBlog(id);
            Blog nextBlog = blogService.nextBlog(id);

            modelAndView.addObject("blog", blog);
            modelAndView.addObject("countOfComment", countOfComment);
            modelAndView.addObject("commentList", comments);
            modelAndView.addObject("commentInfo",new Comment());
            modelAndView.addObject("previousBlog", previousBlog);
            modelAndView.addObject("nextBlog", nextBlog);
            modelAndView.addObject("categoryList", categories);
            modelAndView.addObject("iCountBlogs", iCountBlogs);
            return modelAndView;
        } else {
            return new ModelAndView("/error.404");
        }
    }
    @RequestMapping(value = "/demo", method = RequestMethod.GET)
    public ModelAndView demo(){
        Iterable<LastBlog> lastBlogs = blogService.lastBlog();
        ModelAndView modelAndView = new ModelAndView("/userPage/demo");
        modelAndView.addObject("lastBlogs", lastBlogs);
        return modelAndView;


    }
}
