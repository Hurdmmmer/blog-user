package com.youjian.blog.user.config;

import com.youjian.blog.user.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 安全配置类
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)  // 设置方法基本的安全设置
public class BlogSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceImpl userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/js/**", "/css/**", "/fonts/**", "/images/**", "/index")
                .permitAll()
                .antMatchers("h2-console/**").permitAll()
                .antMatchers("/admins/**").hasRole("ADMIN")
                .and()
                .formLogin()
                .loginPage("/login").failureUrl("/login-error")
                .defaultSuccessUrl("/") // 登录成功跳转
                .and()
                .logout().permitAll().logoutSuccessUrl("/login")
                .and()
                .rememberMe()
                .key("remember-me")
                .and()
                .exceptionHandling().accessDeniedPage("/403");  //无权访问重定向到403

        http.csrf().ignoringAntMatchers("/h2-console/**");
        http.headers().frameOptions().sameOrigin();  // 允许同源访问 h2 控制台

    }

    /**
     *  认证管理器设置
     * */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 设置用户详情服务, 根据用户名查询用户角色
        auth.userDetailsService(userService);
        // 设置鉴权提供者
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
//        使用 dao 认证提供者
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
