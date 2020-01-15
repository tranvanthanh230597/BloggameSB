package com.codegym.blog.Controller;


import com.codegym.blog.Model.Category;
import com.codegym.blog.Model.ICountBlog;
import com.codegym.blog.Service.BlogService;
import com.codegym.blog.Model.Blog;
import com.codegym.blog.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

@Controller
public class HomePageController {
    @Autowired
    BlogService blogService;
    @Autowired
    CategoryService categoryService;
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home(){
        Iterable<Blog> blogs = blogService.findAll();
        ModelAndView modelAndView = new ModelAndView("/userPage/homePage");
        modelAndView.addObject("blogList",blogs);
        return modelAndView;
    }
    @RequestMapping(value = "/blogUser", method = RequestMethod.GET)
    public ModelAndView blog(@RequestParam("s") Optional<String> s){
        Iterable<Blog> blogs;
        if (s.isPresent()) {
            blogs = blogService.findAllByBlogNameContaining(s.get());
        } else {
            blogs = blogService.findAll();
        }
        Iterable<Category> categories = categoryService.findAll();
        ModelAndView modelAndView = new ModelAndView("/userPage/blog");
        modelAndView.addObject("blogList",blogs);
        modelAndView.addObject("categoryList",categories);
        return modelAndView;
    }
    @RequestMapping(value = "/blogUser/{id}", method = RequestMethod.GET)
    public ModelAndView blogById(@PathVariable long id,@RequestParam("s") Optional<String> s) {
        Iterable<Blog> blogs;
        if (s.isPresent()) {
            blogs = blogService.findAllByBlogNameContaining(s.get());
        } else {
            blogs = blogService.findAllByCategoryId(id);
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
        Blog blog = blogService.findById(id);
        if (blog != null) {
            Iterable<Category> categories = categoryService.findAll();
            ModelAndView modelAndView = new ModelAndView("/userPage/post");
            Blog previousBlog = blogService.previousBlog(id);
            Blog nextBlog = blogService.nextBlog(id);

            modelAndView.addObject("blog", blog);
            modelAndView.addObject("previousBlog", previousBlog);
            modelAndView.addObject("nextBlog", nextBlog);
            modelAndView.addObject("categoryList", categories);
            modelAndView.addObject("iCountBlogs", iCountBlogs);
            return modelAndView;
        } else {
            return new ModelAndView("/error.404");
        }
    }
}
