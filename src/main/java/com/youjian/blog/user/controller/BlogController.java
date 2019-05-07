package com.youjian.blog.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * blog 控制器
 */
@Controller
@Slf4j
@RequestMapping("/blogs")
public class BlogController {

    /** 返回 blog 列表
     * @param order 排序规则
     * @param keyword 搜索关键字
     * */
    @GetMapping
    public String listBlog(@RequestParam(value = "order",required = false, defaultValue = "new") String order,
                           @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) {
        log.info("order: {}; keyword: {}", order, keyword);
        return "redirect:index?order=" + order + "&tag=" + keyword;
    }
}
