package com.xinghui.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xinghui.ResultDto;
import com.xinghui.common.Constants;
import com.xinghui.entity.User;
import com.xinghui.security.SecurityUtils;
import com.xinghui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/userInfo")
    public ResultDto userInfo() {
        return success(userService.getOne(new QueryWrapper<User>().lambda().eq(User::getId, SecurityUtils.getCurrentUserId()).eq(User::getStatus, Constants.status.TRUE)));
    }
}