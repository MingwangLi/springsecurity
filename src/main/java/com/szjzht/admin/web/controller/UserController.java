package com.szjzht.admin.web.controller;

import com.szjzht.admin.common.constant.Result;
import com.szjzht.admin.mapper.UserMapper;
import com.szjzht.admin.model.User;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Auther: mayn
 * @Date: 2019/9/9 14:27
 * @Description:
 */
@Api(value = "/user", description = "用户模块")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;



    @ApiOperation(value = "用户列表")
    @GetMapping("/list")
    //@PreAuthorize("hasRole('ROLE_USER')")
    public Result getUserList() {
        List<User> userList = userMapper.selectAll();
        return Result.success(userList);
    }


    @ApiOperation(value = "添加用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userName",value="用户名",dataType="string", paramType = "write",example="test", required = true),
            @ApiImplicitParam(name="password",value="用户密码",dataType="string", paramType = "write",required = true),
            @ApiImplicitParam(name="nickName",value="用户昵称",dataType="string", paramType = "write",required = true)
    })

    @PostMapping ("/add")
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public Result addUser(String userName, String password, String nickName, HttpServletRequest request) {
        User user = new User();
        user.setEnable(1);
        user.setUserName(userName);
        user.setUserPassword(password);
        user.setNickName(nickName);
        String sessionUserName = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userByName = userMapper.findUserByName(sessionUserName);
        user.setCreatorId(userByName.getId());
        userMapper.insert(user);
        return Result.success();
    }
}
