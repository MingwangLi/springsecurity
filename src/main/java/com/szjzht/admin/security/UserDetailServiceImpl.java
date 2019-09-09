package com.szjzht.admin.security;

import com.szjzht.admin.mapper.UserMapper;
import com.szjzht.admin.mapper.UserRoleMapper;
import com.szjzht.admin.model.Role;
import com.szjzht.admin.model.User;
import com.szjzht.admin.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Auther: mayn
 * @Date: 2019/9/9 10:14
 * @Description:
 */
@Component
public class UserDetailServiceImpl implements UserDetailsService {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private MyPasswordEncoder myPasswordEncoder;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User sysUser = userMapper.findUserByName(userName);
        if (sysUser == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        List<Role> roleList = userRoleMapper.getRoleList(sysUser.getId());
        String pwd = myPasswordEncoder.encode(sysUser.getPassword());
        return new org.springframework.security.core.userdetails.User(sysUser.getUsername(),pwd,getRoles(roleList));
    }

//    @Bean
//    public PasswordEncoder getPasswordEncoder() {
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        return bCryptPasswordEncoder;
//
//    }

    private Collection<GrantedAuthority> getRoles(List<Role> roles){
        List<GrantedAuthority> list = new ArrayList<>();
        for (Role role : roles){
            SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRoleKey());
            list.add(grantedAuthority);
        }
        return list;
    }
}
