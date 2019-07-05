package com.xinghui.Config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xinghui.common.Constants;
import com.xinghui.entity.User;
import com.xinghui.exception.CustException;
import com.xinghui.security.CustUserDetails;
import com.xinghui.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


/**
 * @Author chenkuanxing
 * @Date 2019/6/21
 * @desc: 自定义UserDetailsService 接口
 */
@Component
public class AdminUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User sysUser = userService.getOne(new QueryWrapper<User>().lambda().eq(User::getUserCode, username).eq(User::getStatus, Constants.status.TRUE));
        if (sysUser != null) {
            CustUserDetails userDtails = new CustUserDetails();
            BeanUtils.copyProperties(sysUser, userDtails);
            return userDtails;
        } else {
            throw new CustException("用户名或密码错误");
        }
    }
}
