package com.youjian.blog.user.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 实现 Spring security {@link UserDetails} 接口, 用于支持spring security 权限管理
 */
@Data
@Entity(name = "blog_user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    @Column(nullable = false, unique = true, length = 50)
    private String email;
    @NotEmpty(message = "姓名不能为空")
    @Size(min = 2, max = 20)
    @Column(nullable = false, length = 20)
    private String name;
    @NotEmpty(message = "账号不能为空")
    @Size(min = 3, max = 20)
    @Column(nullable = false, unique = true, length = 20)
    private String username;
    @NotEmpty(message = "密码不能为空")
    @Size(max = 200)
    @Column(length = 100)
    private String password;
    @Column(length = 200)
    private String avatar;


    // 建立用户与权限的关系
    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable(name = "b_user_authority",  // 关系表名称
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    List<Authority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 需要将 List<Authority> 转换成 List<SimpleGrantedAuthority> 否则前端拿不到权限名称
        // 因为 Authority 是对象, 而 SimpleGrantedAuthority 中只有一个 String 属性, 前台更好的获取
        return authorities.stream().map(e -> new SimpleGrantedAuthority(e.getAuthority())).collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
