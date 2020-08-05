package com.patarw.blog.service.impl;

import com.patarw.blog.dao.UserRepository;
import com.patarw.blog.entity.User;
import com.patarw.blog.service.UserService;
import com.patarw.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User login(String username, String password) {
        User user = userRepository.findUserByUsernameAndPassword(username, MD5Utils.code(password)); //对密码进行加密
        return user;
    }
}
