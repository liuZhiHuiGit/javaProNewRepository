package com.xinghui.Config;

import com.xinghui.handler.CustAccessDeniedHandler;
import com.xinghui.handler.SignInFailureHandler;
import com.xinghui.handler.SignInSuccessHandler;
import com.xinghui.handler.SignOutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author chenkuanxing
 * @Date 2019/6/21
 * spring security 配置类
 */

@EnableWebSecurity
public class AdminWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AdminUserDetailsService custUserDetailsService;

    @Autowired
    private SignInSuccessHandler signInSuccessHandler;

    @Autowired
    private SignInFailureHandler signInFailureHandler;

    @Autowired
    private CustAccessDeniedHandler custAccessDeniedHandler;

    @Autowired
    private SignOutSuccessHandler signOutSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login", "/").permitAll()
                .antMatchers("/statics/**", "/templates/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/signIn")
                .successHandler(signInSuccessHandler) //登录成功处理返回json
                .failureHandler(signInFailureHandler)  //登录失败处理返回json
                .permitAll()
                .and()
                .logout().logoutUrl("/signOut")
                .logoutSuccessHandler(signOutSuccessHandler)  //处理登出成功返回json
                .permitAll()
                .and()
                .headers().frameOptions().disable()
                .and()
                .csrf().disable()
                .exceptionHandling()
                .accessDeniedHandler(custAccessDeniedHandler); //权限不足处理
    }

    /**
     * 密码加密方式
     *
     * @return
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(custUserDetailsService).passwordEncoder(passwordEncoder());
    }
}
