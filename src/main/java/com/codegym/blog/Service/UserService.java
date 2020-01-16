package com.codegym.blog.Service;

import com.codegym.blog.Model.Category;
import com.codegym.blog.Model.ICountBlog;
import com.codegym.blog.Model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {
    Iterable<User> findAll();

    User findById(Long id);

    void save(User user);

    void remove(Long id);
}
