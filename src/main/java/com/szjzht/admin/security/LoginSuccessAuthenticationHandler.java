package com.szjzht.admin.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Auther: mayn
 * @Date: 2019/9/9 11:00
 * @Description:
 */
@Component
public class LoginSuccessAuthenticationHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        response.setContentType("application/json;charset=utf-8");
//        PrintWriter writer = response.getWriter();
//        writer.print("登录成功:");
//        writer.flush();
        response.sendRedirect("/static/index.html");
    }
}
