package com.codegym.blog.Controller;

import com.codegym.blog.Model.User;
import com.codegym.blog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView listUser(){
        Iterable<User> userList = userService.findAll();
        ModelAndView modelAndView = new ModelAndView("/userAdmin/list");
        modelAndView.addObject("userList",userList);
        return modelAndView;
    }
    @RequestMapping(value = "/create-user",method = RequestMethod.GET)
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/userAdmin/create");
        modelAndView.addObject("user",new User());
        return modelAndView;
    }
    @RequestMapping(value = "/create-user", method = RequestMethod.POST)
    public ModelAndView createUser(@ModelAttribute("user") User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        ModelAndView modelAndView = new ModelAndView("/userAdmin/create");
        modelAndView.addObject("user",new User());
        modelAndView.addObject("message", "New User created successfully");
        return modelAndView;
    }
    @RequestMapping(value = "/edit-user/{id}",method = RequestMethod.GET)
    public ModelAndView showEditForm(@PathVariable long id){
        User user = userService.findById(id);
        if (user != null){
            ModelAndView modelAndView = new ModelAndView("/userAdmin/edit");
            modelAndView.addObject("user", user);
            return modelAndView;
        }else{
            return new ModelAndView("/error.404");
        }
    }
    @RequestMapping(value = "edit-user",method = RequestMethod.POST)
    public ModelAndView editCategory(@ModelAttribute("user") User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        ModelAndView modelAndView = new ModelAndView("/userAdmin/edit");
        modelAndView.addObject("user", user);
        modelAndView.addObject("message", "user updated successfully");
        return modelAndView;
    }
    @RequestMapping(value = "delete-user/{id}", method = RequestMethod.GET)
    public ModelAndView ShowDeleteForm(@PathVariable long id){
        User user = userService.findById(id);
        if(user != null){
            ModelAndView modelAndView = new ModelAndView("/userAdmin/delete");
            modelAndView.addObject("user", user);
            return modelAndView;
        }
        else{
            return new ModelAndView("/error.404");
        }
    }
    @RequestMapping(value = "delete-user", method = RequestMethod.POST)
    public String  deleteProvince(@ModelAttribute("user") User user){
        userService.remove(user.getId());
        return "redirect:user";
    }
}
