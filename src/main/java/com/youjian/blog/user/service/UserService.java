package com.youjian.blog.user.service;

import com.youjian.blog.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    User saveOrUpdateUser(User user);

    User registerUser(User user);

    void removeUser(Long id);

    User getUserById(Long id);

    Page<User> listUsersByNameLike(String name, Pageable pageable);
}
