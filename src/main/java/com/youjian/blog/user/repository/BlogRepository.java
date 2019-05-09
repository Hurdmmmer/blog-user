package com.youjian.blog.user.repository;

import com.youjian.blog.user.entity.Blog;
import com.youjian.blog.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** blog 仓库 */
@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    /** 根据用户名, 博客标题分页查询 */
    Page<Blog> findByUserAndTitleLike(User user, String title, Pageable pageable);

    /**
     * 根据用户名分页查询用户列表
     */
    Page<Blog> findByUserAndTitleLikeOrderByCreateTimeDesc(User user, String title, Pageable pageable);

}
