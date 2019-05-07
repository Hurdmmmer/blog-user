package com.youjian.blog.user.controller;

import com.youjian.blog.user.utils.BlogKit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/** 用户主页接口 */
@Controller
@Slf4j
@RequestMapping("/u")
public class UserSpaceController {
    /**
     * 根据用户名称返回用户页面
     * @param username 用户名称
     * */
    @GetMapping("/{username}")
    public String userSpace(@PathVariable("username")String username) {
        log.info("username: {}", username);
        return "/u";
    }

    /**
     * 根据用户返回当前用户的博客
     * @param order 排序规则
     * @param keyword 搜索关键字
     * @param category 博客分类
     * @param username 当前用户
     * */
    @GetMapping("/{username}/blogs")
    public String listBlogsByOrder(@PathVariable("username") String username,
                                   @RequestParam(value = "order", required = false, defaultValue = "new") String order,
                                   @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
                                   @RequestParam(value = "category", required = false) Long category) {
        if (category != null) {
            log.info("category: {}", category);
            log.info("selflink: redirect:/u/{}/blogs?category={}", username, category);
            return "/u";
        } else if (!BlogKit.isEmpty(keyword)) {
            log.info("keyword: {}", keyword);
            log.info("selflink: redirect:/u/{}/blogs?keyword={}", username, keyword);
            return "/u";
        }
        log.info("order: {}", order);
        log.info("selflink: redirect:/u/{}/blogs?order={}", username, order);
        return "/u";
    }

    /***
     * 根据博客id查询博客
     * @param id 博客 id
     */
    @GetMapping("/{username}/blogs/{id}")
    public String listBlogsByOrder(@PathVariable("id") Long id) {

        log.info("blogId: {}", id);
        return "/blog";
    }

    /**
     *  返回博客编辑页面
     */
    @GetMapping("/{username}/blogs/edit")
    public String blogEdit() {

        return "/blogedit";
    }
}
