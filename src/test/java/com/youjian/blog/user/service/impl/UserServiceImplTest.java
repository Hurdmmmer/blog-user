package com.youjian.blog.user.service.impl;

import com.youjian.blog.user.entity.User;
import com.youjian.blog.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void saveOrUpdateUser() {
        User user = new User();
        user.setAvatar("http://hao123.com");
        user.setEmail("xxx@qq.com");
        user.setName("test");
        user.setPassword("123456");
        user.setUsername("hao123");

        userService.saveOrUpdateUser(user);
    }
}