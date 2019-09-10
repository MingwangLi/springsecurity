package com.szjzht.admin.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: mayn
 * @Date: 2019/9/9 11:01
 * @Description:
 */
@Component
public class LoginFailureAuthenticationHandler implements AuthenticationFailureHandler {



    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//        response.setContentType("application/json;charset=utf-8");
//        PrintWriter writer = response.getWriter();
//
//        writer.print("登录失败");
//        writer.flush();
//        writer.close();
        response.sendRedirect("/static/login.html");
    }
}
