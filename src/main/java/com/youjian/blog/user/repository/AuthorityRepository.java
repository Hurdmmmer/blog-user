package com.youjian.blog.user.repository;

import com.youjian.blog.user.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// 权限仓库
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
