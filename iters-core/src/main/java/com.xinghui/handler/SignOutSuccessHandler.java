package com.xinghui.handler;

import com.alibaba.fastjson.JSONObject;
import com.xinghui.enums.HttpCodeEnum;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * @Author chenkuanxing
 * @Date 2019/6/21
 * 登录成功处理类
 */

@Component(value = "signOutSuccessHandler")
public class SignOutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //返回json
        //返回json形式的错误信息
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        JSONObject json = new JSONObject();
        json.put("code", HttpCodeEnum.sucess.getValue());
        json.put("message", HttpCodeEnum.sucess.getDesc());
        json.put("data", "");
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
        out.close();


    }
}
