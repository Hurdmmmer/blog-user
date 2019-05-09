package com.youjian.blog.user.service.impl;

import com.youjian.blog.user.entity.Blog;
import com.youjian.blog.user.entity.User;
import com.youjian.blog.user.repository.BlogRepository;
import com.youjian.blog.user.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    @Transactional
    public Blog saveBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    @Transactional
    public void removeBlog(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public Blog getBlogById(Long id) {
        Optional<Blog> blog = blogRepository.findById(id);
        return blog.orElse(null);
    }

    @Override
    public Page<Blog> listBlogsByTitleVote(User user, String title, Pageable pageable) {
        title = "%" + title + "%";
        return blogRepository.findByUserAndTitleLike(user, title, pageable);
    }

    @Override
    public Page<Blog> listBlogsByTileVoteAndSort(User user, String title, Pageable pageable) {
        title = "%" + title + "%";
        return blogRepository.findByUserAndTitleLikeOrderByCreateTimeDesc(user, title, pageable);
    }

    @Override
    @Transactional
    public void readingIncrease(Long id) {
        Optional<Blog> blog = blogRepository.findById(id);
        if (blog.isPresent()) {
            Blog db = blog.get();
            db.setReading(db.getReading() + 1);
            blogRepository.save(db);
        }
        throw new IllegalArgumentException("当前博客 id 不存在");
    }
}
