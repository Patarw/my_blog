package com.patarw.blog;

import com.patarw.blog.entity.Blog;
import com.patarw.blog.service.BlogService;
import com.patarw.blog.util.MD5Utils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    private BlogService blogService;
    //生成加密字符串
    @Test
    void contextLoads() {
        String password = MD5Utils.code("admin");
        System.out.println(password);
    }
    @Test
    void contextLoad() {
        Blog blog = blogService.getBlog( new Long(41));
        Calendar calendar = Calendar.getInstance();

    }

}
