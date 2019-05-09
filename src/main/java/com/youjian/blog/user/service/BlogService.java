package com.youjian.blog.user.service;

import com.youjian.blog.user.entity.Blog;
import com.youjian.blog.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *  博客服务接口
 */
public interface BlogService {
    Blog saveBlog(Blog blog);

    void removeBlog(Long id);

    Blog getBlogById(Long id);

    /**
     * 根据用户, 博客名称分页模糊查询 (最新)
     */
    Page<Blog> listBlogsByTitleVote(User user, String title, Pageable pageable);

    /**
     * 根据用户, 博客名称分页模糊查询 (最热)
     */
    Page<Blog> listBlogsByTileVoteAndSort(User user, String title, Pageable pageable);

    /** 阅读量递增 */
    void readingIncrease(Long id);
}
