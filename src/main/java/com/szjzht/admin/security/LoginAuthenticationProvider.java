package com.szjzht.admin.security;

import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;



/**
 * 登陆认证、加载改用户所有角色集合
 * @Auther: mayn
 * @Date: 2019/9/9 10:16
 * @Description:
 */
@Component
public class LoginAuthenticationProvider implements AuthenticationProvider {


    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private MyPasswordEncoder myPasswordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        password = myPasswordEncoder.encode(password);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String realPassword = userDetails.getPassword();
        if (!realPassword.equals(password)) {
            throw new BadCredentialsException("密码错误");
        }
        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(),userDetails.getPassword(),userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }


}
