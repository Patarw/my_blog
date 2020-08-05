package com.patarw.blog.dao;

import com.patarw.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByUsernameAndPassword(String username,String password);
}
