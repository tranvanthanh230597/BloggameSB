package com.codegym.blog.Service;

import com.codegym.blog.Model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService,BaseService<User> {

}
