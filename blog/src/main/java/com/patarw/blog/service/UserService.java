package com.patarw.blog.service;

import com.patarw.blog.entity.User;

public interface UserService {
    User login(String username, String password);
}
