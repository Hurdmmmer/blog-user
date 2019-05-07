package com.youjian.blog.user.repository;

import com.youjian.blog.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /** 根据名称模糊查询 */
    Page<User> findByNameLike(String name, Pageable pageable);

    /** 根据账号查询 */
    User findByUsername(String username);
}
