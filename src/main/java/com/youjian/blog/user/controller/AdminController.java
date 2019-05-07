package com.youjian.blog.user.controller;

import com.youjian.blog.user.entity.Menu;
import com.youjian.blog.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * 后台管理控制器
 */
@Controller
@RequestMapping("/admins")
public class AdminController {
    @Autowired
    private UserRepository userRepository;
    /**
     * 获取后台管理页面
     */
    @GetMapping
    public ModelAndView listUsers(Model model) {
        List<Menu> menuList = new ArrayList<>();
        menuList.add(new Menu("用户管理", "/users"));
        model.addAttribute("list", menuList);
        return new ModelAndView("/admins/index", "menuList", model);
    }
}