package com.szjzht.admin.web.controller;

import com.szjzht.admin.common.constant.Result;
import com.szjzht.admin.mapper.UserMapper;
import com.szjzht.admin.model.User;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
@Api(value = "/user", description = "用户模块")
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;



    // TODO: 2019/9/9 这里的权限既可以通过数据库RoleResource配置 也可以通过@PreAuthorize("hasRole('ROLE_ROOT')")配置 如果都配置 我猜应该先校验数据库配置 再校验注解 推荐使用注解 role resouce role_resouce  需要配置url 并且role名字固定ROLE_* 侵入性太强
    @ApiOperation(value = "用户列表",tags = "用户列表接口")
    @GetMapping("/list")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER')")
    public Result getUserList() {
        List<User> userList = userMapper.selectAll();
        return Result.success(userList);
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name="userName",value="用户名",dataType="string", paramType = "write",example="test", required = true),
            @ApiImplicitParam(name="password",value="用户密码",dataType="string", paramType = "write",required = true),
            @ApiImplicitParam(name="nickName",value="用户昵称",dataType="string", paramType = "write",required = true)
    })
    @ApiOperation(value = "添加用户",tags = "用户添加接口")
    @GetMapping ("/add")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public Result addUser(String userName, String password, String nickName, HttpServletRequest request) {
        User user = new User();
        user.setEnable(1);
        user.setUserName(userName);
        user.setUserPassword(password);
        user.setNickName(nickName);
        // TODO: 2019/9/9 SpirngSecurity应该有登陆成功之后的session管理
        //request.getSession().getAttribute()
        String sessionUserName = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userByName = userMapper.findUserByName(sessionUserName);
        user.setCreatorId(userByName.getId());
        userMapper.insert(user);
        return Result.success();
    }
}
