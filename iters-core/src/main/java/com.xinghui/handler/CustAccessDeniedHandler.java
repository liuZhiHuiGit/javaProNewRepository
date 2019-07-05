package com.xinghui.handler;

import com.alibaba.fastjson.JSONObject;
import com.xinghui.enums.HttpCodeEnum;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author chenkuanxing
 * @Date 2019/6/21
 * 权限不足处理类
 */

@Component(value = "custAccessDeniedHandler")
public class CustAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        //返回json形式的错误信息
        response.setContentType("application/json;charset=utf-8");
        JSONObject json = new JSONObject();
        json.put("code", HttpCodeEnum.Forbidden.getValue());
        json.put("message", HttpCodeEnum.Forbidden.getDesc());
        json.put("data", "");
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
        out.close();
    }
}
