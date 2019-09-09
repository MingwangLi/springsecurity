package com.szjzht.admin.web.controller;

import com.szjzht.admin.common.constant.Result;
import com.szjzht.admin.mapper.UserMapper;
import com.szjzht.admin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Auther: mayn
 * @Date: 2019/9/9 14:27
 * @Description:
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    // TODO: 2019/9/9 这里的权限既可以通过数据库RoleResource配置 也可以通过@PreAuthorize("hasRole('ROLE_ROOT')")配置 如果都配置 我猜应该先校验数据库配置 再校验注解 推荐使用注解 role resouce role_resouce  需要配置url 并且role名字固定ROLE_* 侵入性太强
    @GetMapping("/list")
    @ResponseBody
    //@PreAuthorize("hasRole('ROLE_ROOT')")
    public List<User> getUserList() {
        List<User> userList = userMapper.selectAll();
        return userList;
    }

    @GetMapping ("/add")
    @ResponseBody
    public Result addUser(String userName, String password, String nickName, HttpServletRequest request) {
        User user = new User();
        user.setEnable(1);
        user.setUserName(userName);
        user.setUserPassword(password);
        user.setNickName(nickName);
        // TODO: 2019/9/9 SpirngSecurity应该有登陆成功之后的session管理
        //request.getSession().getAttribute()
        userMapper.insert(user);
        return Result.success();
    }
}
