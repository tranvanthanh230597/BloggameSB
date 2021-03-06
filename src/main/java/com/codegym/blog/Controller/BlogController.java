package com.codegym.blog.Controller;


import com.codegym.blog.Model.BlogUpload;
import com.codegym.blog.Service.BlogService;
import com.codegym.blog.Service.CategoryService;
import com.codegym.blog.Model.Blog;
import com.codegym.blog.Model.Category;
import com.codegym.blog.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CommentService commentService;
    @ModelAttribute("category")
    public Iterable<Category> categories(){
        return categoryService.findAll();
    }

    @RequestMapping(value = "/blog", method = RequestMethod.GET)
    public ModelAndView listBlog(){
        Iterable<Blog> blogs = blogService.findAll();
        ModelAndView modelAndView = new ModelAndView("/blog/list");
        modelAndView.addObject("blogList", blogs);
        return modelAndView;
    }
    @RequestMapping(value = "/create-blog",method = RequestMethod.GET)
    public ModelAndView showCreateForm(@ModelAttribute("blogUpload")BlogUpload blogUpload){
        ModelAndView modelAndView = new ModelAndView("/blog/create");
        modelAndView.addObject("blog",new Blog());
        modelAndView.addObject("blogUpload", new BlogUpload());
        return modelAndView;
    }
    @RequestMapping(value = "/create-blog", method = RequestMethod.POST)
    public ModelAndView createCategory(@ModelAttribute("blogUpload")BlogUpload blogUpload) throws IOException {
        ModelAndView modelAndView = new ModelAndView("/blog/create");
        MultipartFile file = blogUpload.getFile();
        blogService.uploadFile(file);
        String imageName = file.getOriginalFilename();
        Blog blog = new Blog();
        blog.setBlogName(blogUpload.getBlogName());
        blog.setView(0);
        blog.setComments(blogUpload.getComments());
        blog.setCategory(blogUpload.getCategory());
        blog.setContent(blogUpload.getContent());
        blog.setDate(blogUpload.getDate());
        blog.setDescription(blogUpload.getDescription());
        blog.setImage(imageName);
        blogService.save(blog);
        modelAndView.addObject("blogUpload",new BlogUpload());
        modelAndView.addObject("message", "New blog created successfully");
        return modelAndView;
    }
    @RequestMapping(value = "/edit-blog/{id}",method = RequestMethod.GET)
    public ModelAndView showEditForm(@PathVariable long id){
        Blog blog = blogService.findById(id);
        if (blog != null){
            ModelAndView modelAndView = new ModelAndView("/blog/edit");
            modelAndView.addObject("blog", blog);
            return modelAndView;
        }else{
            return new ModelAndView("/error.404");
        }
    }
    @RequestMapping(value = "edit-blog",method = RequestMethod.POST)
    public ModelAndView editBlog(@ModelAttribute("blog") Blog blog){
        blogService.save(blog);

        ModelAndView modelAndView = new ModelAndView("/blog/edit");
        modelAndView.addObject("blog", blog);
        modelAndView.addObject("message", "Blog updated successfully");
        return modelAndView;
    }
    @RequestMapping(value = "delete-blog/{id}", method = RequestMethod.GET)
    public ModelAndView ShowDeleteForm(@PathVariable long id){
        Blog blog = blogService.findById(id);
        if(blog != null){
            ModelAndView modelAndView = new ModelAndView("/blog/delete");
            modelAndView.addObject("blog", blog);
            return modelAndView;
        }
        else{
            return new ModelAndView("/error.404");
        }
    }
    @RequestMapping(value = "delete-blog", method = RequestMethod.POST)
    public String  deleteProvince(@ModelAttribute("blog") Blog blog){
        blogService.remove(blog.getId());
        return "redirect:blog";
    }
}
