package com.xinghui.controller;

import com.xinghui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/login", "/"})
    public String loginPage() {
        return "login";
    }

    @RequestMapping("/index")
    public String index() {
        return "page/index";
    }
}