package com.szjzht.admin.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @Auther: mayn
 * @Date: 2019/9/9 11:00
 * @Description:
 */
@Component
public class LoginSuccessAuthenticationHandler implements AuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        response.setContentType("application/json;charset=utf-8");
//        PrintWriter writer = response.getWriter();
//        writer.print("登录成功:");
//        writer.flush();
        String userName = (String) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        logger.info(userName+"登陆成功");
        response.sendRedirect("/static/index.html");
    }
}
