package com.codegym.blog.Controller;

import com.codegym.blog.Model.Blog;
import com.codegym.blog.Model.Comment;
import com.codegym.blog.Model.Email;
import com.codegym.blog.Model.Interface.IHomePageBlog;
import com.codegym.blog.Service.BlogService;
import com.codegym.blog.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmailComtroller {
    @Autowired
    EmailService emailService;
    @Autowired
    BlogService blogService;

    @PostMapping("/create-email")
    public ModelAndView createEmail(@ModelAttribute("infoContact") Email email){
        emailService.save(email);
        System.out.println("oke");
        Iterable<IHomePageBlog> blogs = blogService.homePageBlog();
        Iterable<Blog> latestBlog = blogService.latestBlog();
        ModelAndView modelAndView = new ModelAndView("/userPage/homePage");
        modelAndView.addObject("blogList",blogs);
        modelAndView.addObject("latestBlogList",latestBlog);
        modelAndView.addObject("InfoContact",new Email());
        return modelAndView;
    }
}
