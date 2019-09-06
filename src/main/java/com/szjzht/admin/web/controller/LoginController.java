package com.szjzht.admin.web.controller;

import com.szjzht.admin.common.constant.Result;
import com.szjzht.admin.mapper.UserMapper;
import com.szjzht.admin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @Auther: mayn
 * @Date: 2019/9/5 11:39
 * @Description:
 */
@Controller
@RequestMapping("/user")
public class LoginController {


    @Autowired
    private UserMapper userMapper;

    @GetMapping
    @ResponseBody
    public Result getUserList() {
        //List<User> userList = userMapper.getUserList();
        List<User> userList = userMapper.selectAll();
        return Result.success(userList);
    }


    @GetMapping("/success")
    public String success() {
        //@RestController必须返回ModelAndView @Controller可以直接返回String  没有@ResponseBody返回视图 有@ResponseBody返回数据
        //return new ModelAndView("test");
        return "test";
    }

    @GetMapping("/{userId}")
    @ResponseBody
    public Result getUser(@PathVariable("userId") Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        return Result.success(user);
    }



}
