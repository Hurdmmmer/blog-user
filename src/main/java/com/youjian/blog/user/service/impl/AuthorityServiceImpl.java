package com.youjian.blog.user.service.impl;

import com.youjian.blog.user.entity.Authority;
import com.youjian.blog.user.repository.AuthorityRepository;
import com.youjian.blog.user.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 *  权限服务类
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    private AuthorityRepository authorityRepository;
    @Override
    public Authority getAuthorityById(Long id) {
        Optional<Authority> authority = authorityRepository.findById(id);
        return authority.orElse(null);
    }
}
