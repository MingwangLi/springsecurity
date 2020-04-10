package com.szjzht.admin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @Auther: mayn
 * @Date: 2019/9/9 10:22
 * @Description:
 */
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Component
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private UrlAccessDecisionManager decisionManager;

    @Autowired
    private UrlPathFilterInvocationSecurityMetadataSource urlPathFilterInvocationSecurityMetadataSource;

    @Autowired
    private LoginSuccessAuthenticationHandler successAuthenticationHandler;

    @Autowired
    private LoginFailureAuthenticationHandler failureAuthenticationHandler;

    /*虽然mvc设置了静态文件 这里一定需要重新设定 请求先到mvc 再到security*/
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/static/**","/swagger-ui.html","/swagger-resources/**","/v2/**","/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                //.antMatchers("/swagger-ui.html").permitAll()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setAccessDecisionManager(decisionManager);
                        o.setSecurityMetadataSource(urlPathFilterInvocationSecurityMetadataSource);
                        return o;
                    }
                })
                .anyRequest()
                .authenticated()// 其他 url 需要身份认证
                .and()
                .formLogin()  //开启登录
                .loginPage("/static/login.html")
                .loginProcessingUrl("/spring_security_check")
                .successHandler(successAuthenticationHandler)
                .failureHandler(failureAuthenticationHandler)
                .permitAll()

                .and()
                .logout()
                .logoutUrl("/logout")
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }
}
