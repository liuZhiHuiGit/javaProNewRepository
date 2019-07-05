package com.xinghui.handler;

import com.alibaba.fastjson.JSONObject;
import com.xinghui.enums.HttpCodeEnum;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * @Author chenkuanxing
 * @Date 2019/6/21
 * 登录失败处理类
 */

@Component(value = "signInFailureHandler")
@Log4j2
public class SignInFailureHandler implements AuthenticationFailureHandler {


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        //返回json形式的错误信息
        response.setContentType("application/json;charset=utf-8");
        JSONObject json = new JSONObject();
        json.put("code", HttpCodeEnum.FAIL.getValue());
        json.put("message", exception.getMessage());
        json.put("data", "");
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
        out.close();

    }
}
