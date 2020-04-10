package com.szjzht.admin.web.controller;

import com.szjzht.admin.common.constant.Result;
import com.szjzht.admin.mapper.UserMapper;
import com.szjzht.admin.model.User;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
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
    public Result getUserList() {
        List<User> userList = userMapper.selectAll();
        return Result.success(userList);
    }


    @ApiOperation(value = "添加用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userName",value="用户名",dataType="string", paramType = "query", required = true),
            @ApiImplicitParam(name="password",value="用户密码",dataType="string", paramType = "query",required = true),
            @ApiImplicitParam(name="nickName",value="用户昵称",dataType="string", paramType = "query",required = true)
    })
    @PostMapping("/add")
    public Result addUser(String userName, String password, String nickName) {
        User user = new User();
        user.setEnable(1);
        user.setUserName(userName);
        user.setUserPassword(password);
        user.setNickName(nickName);
        String loginUserName = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loginUser = userMapper.findUserByName(loginUserName);
        user.setCreatorId(loginUser.getId());
        userMapper.insert(user);
        return Result.success();
    }
}
