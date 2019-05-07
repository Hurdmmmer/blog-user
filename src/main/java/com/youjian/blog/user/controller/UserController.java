package com.youjian.blog.user.controller;

import com.youjian.blog.user.entity.User;
import com.youjian.blog.user.service.UserService;
import com.youjian.blog.user.utils.BlogKit;
import com.youjian.blog.user.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    /** 获取用户列表, 返回用户列表的 HTML 视图 */
    @GetMapping
    public ModelAndView list(@RequestParam(value = "async", required = false) boolean async,
                             @RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex,
                             @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                             @RequestParam(value = "name", required = false, defaultValue = "") String name,
                             Model model) {

        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<User> page = userService.listUsersByNameLike(name, pageable);
        List<User> list = page.getContent();    // 当前所在页面数据列表

        model.addAttribute("page", page);
        model.addAttribute("userList", list);
        return new ModelAndView(async ? "users/list :: #mainContainerRepleace" : "users/list", "userModel", model);
    }

    @GetMapping("/add")
    public ModelAndView addView(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return new ModelAndView("users/add", "userModel", model);
    }

    @PostMapping
    public ResponseResult addUser(@Valid User user, BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
//            List<ObjectError> allErrors = bindingResult.getAllErrors();
            throw new BindException(bindingResult);
        }
        if (BlogKit.isEmpty(user.getAvatar())){
            user.setAvatar("https://waylau.com/images/waylau_181_181.jpg");
        }
        User db = userService.saveOrUpdateUser(user);
        if (db != null) {
            return ResponseResult.success(null);
        }
        return ResponseResult.fail("添加或修改用户失败. 请稍后重试...");
    }

    /**
     * 根据 id 查询用户
     */
    @GetMapping("/{id}")
    public ModelAndView view(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("title", "查看用户");
        return new ModelAndView("/users/view", "userModel", model);
    }


    @PostMapping("/register")
    public ModelAndView saveOrUpdate(User user) {
        if (BlogKit.isEmpty(user.getAvatar())){
            user.setAvatar("https://waylau.com/images/waylau_181_181.jpg");
        }
        User dbUser = userService.registerUser(user);
        if (dbUser != null) {
            return new ModelAndView("redirect:/login");
        }
        return new ModelAndView("redirect:/register");
    }

    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable("id") Long id, Model model) {
        userService.removeUser(id);
        return ResponseResult.success(null);
    }

    /**
     * 根据 id 获取修改用户
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/edit/{id}")
    public ModelAndView modify(@PathVariable("id") Long id, Model model) {

        User dbUser = userService.getUserById(id);
        model.addAttribute("user", dbUser);

        return new ModelAndView("users/edit", "userModel", model);
    }


}