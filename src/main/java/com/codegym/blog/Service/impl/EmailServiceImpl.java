package com.codegym.blog.Service.impl;

import com.codegym.blog.Model.Email;
import com.codegym.blog.Repository.EmailRepository;
import com.codegym.blog.Service.EmailService;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailServiceImpl implements EmailService {
    EmailRepository emailRepository;
    @Override
    public Iterable<Email> findAll() {
        return emailRepository.findAll();
    }

    @Override
    public Email findById(Long id) {
        return emailRepository.findById(id).get();
    }

    @Override
    public void save(Email email) {
        emailRepository.save(email);
    }

    @Override
    public void remove(Long id) {
        emailRepository.deleteById(id);
    }
}
