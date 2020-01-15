package com.codegym.blog.Controller;

import com.codegym.blog.Model.*;
import com.codegym.blog.Service.BlogService;
import com.codegym.blog.Service.CategoryService;
import com.codegym.blog.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Iterator;

@Controller
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    BlogService blogService;
    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/create-comment", method = RequestMethod.POST)
    public ModelAndView createComment(@ModelAttribute("commentInfo") Comment comment){
        commentService.save(comment);
        Iterable<ICountComment> countComments = blogService.countComment(comment.getBlog().getId());
        Iterable<ICountBlog> iCountBlogs = categoryService.countBlogs();
        Iterable<Comment> comments = commentService.findAllByBlog_Id(comment.getBlog().getId());
        Blog blog = blogService.findById(comment.getBlog().getId());
        Long countOfComment = 0L;
        Iterator<ICountComment> iter = countComments.iterator();
        while(iter.hasNext()){
            countOfComment = iter.next().getCount();
        }

        if (blog != null) {
            Iterable<Category> categories = categoryService.findAll();
            ModelAndView modelAndView = new ModelAndView("/userPage/post");
            Blog previousBlog = blogService.previousBlog(comment.getBlog().getId());
            Blog nextBlog = blogService.nextBlog(comment.getBlog().getId());

            modelAndView.addObject("blog", blog);
            modelAndView.addObject("commentList", comments);
            modelAndView.addObject("countOfComment", countOfComment);
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
}
