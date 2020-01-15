package com.codegym.blog.Controller;

import com.codegym.blog.Service.BlogService;
import com.codegym.blog.Service.CategoryService;
import com.codegym.blog.Model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BlogService blogService;

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public ModelAndView listCategory(){
        Iterable<Category> categoryList = categoryService.findAll();
        ModelAndView modelAndView = new ModelAndView("/category/list");
        modelAndView.addObject("categoryList",categoryList);
        return modelAndView;
    }
    @RequestMapping(value = "/create-category",method = RequestMethod.GET)
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/category/create");
        modelAndView.addObject("category",new Category());
        return modelAndView;
    }
    @RequestMapping(value = "/create-category", method = RequestMethod.POST)
    public ModelAndView createCategory(@ModelAttribute("category") Category category){
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("/category/create");
        modelAndView.addObject("category",new Category());
        modelAndView.addObject("message", "New category created successfully");
        return modelAndView;
    }
    @RequestMapping(value = "/edit-category/{id}",method = RequestMethod.GET)
    public ModelAndView showEditForm(@PathVariable long id){
        Category category = categoryService.findById(id);
        if (category != null){
            ModelAndView modelAndView = new ModelAndView("/category/edit");
            modelAndView.addObject("category", category);
            return modelAndView;
        }else{
            return new ModelAndView("/error.404");
        }
    }
    @RequestMapping(value = "edit-category",method = RequestMethod.POST)
    public ModelAndView editCategory(@ModelAttribute("category") Category category){
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("/category/edit");
        modelAndView.addObject("category", category);
        modelAndView.addObject("message", "Category updated successfully");
        return modelAndView;
    }
    @RequestMapping(value = "delete-category/{id}", method = RequestMethod.GET)
    public ModelAndView ShowDeleteForm(@PathVariable long id){
        Category category = categoryService.findById(id);
        if(category != null){
            ModelAndView modelAndView = new ModelAndView("/category/delete");
            modelAndView.addObject("category", category);
            return modelAndView;
        }
        else{
            return new ModelAndView("/error.404");
        }
    }
    @RequestMapping(value = "delete-category", method = RequestMethod.POST)
    public String  deleteProvince(@ModelAttribute("category") Category category){
        categoryService.remove(category.getId());
        return "redirect:category";
    }
}
