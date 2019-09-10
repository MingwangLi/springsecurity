package com.szjzht.admin.security;

import com.szjzht.admin.mapper.UserMapper;
import com.szjzht.admin.mapper.UserRoleMapper;
import com.szjzht.admin.model.Role;
import com.szjzht.admin.model.User;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


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

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User user = new User();
        // 获取表单用户名
        String username = (String) authentication.getPrincipal();
        // 获取表单用户填写的密码
        String password = (String) authentication.getCredentials();
        password = myPasswordEncoder.encode(password);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        String password1 = userDetails.getPassword();
        if (!Objects.equals(password,password1)){
            throw new BadCredentialsException("用户名或密码不正确");
        }
        if (user instanceof UserDetails) {
            user.setUserName(username);
            user = userMapper.selectOne(user);
            List<Role> roleList = userRoleMapper.getRoleList(user.getId());
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            if (null != roleList && roleList.size() > 0) {
                for (Role role:roleList) {
                    final String roleKey = role.getRoleKey();
                    GrantedAuthority grantedAuthority = new GrantedAuthority() {
                        @Override
                        public String getAuthority() {
                            return roleKey;
                        }
                    };
                    grantedAuthorities.add(grantedAuthority);
                }
            }
            user.setGrantedAuthorities(grantedAuthorities);
        }
        return new UsernamePasswordAuthenticationToken(user.getUserName(),user.getUserPassword(),user.getGrantedAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }


}
