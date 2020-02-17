package com.codegym.blog.Model;

import org.springframework.web.multipart.MultipartFile;

public class BlogUpload extends Blog {
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
