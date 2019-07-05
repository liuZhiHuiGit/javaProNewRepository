package com.xinghui.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinghui.entity.User;
import com.xinghui.mapper.UserMapper;
import com.xinghui.security.CustUserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public CustUserDetails queryUserPass(String username) {
        return baseMapper.queryUserPass(username);
    }

}
