package com.szjzht.admin.mapper;

import com.szjzht.admin.model.User;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;
import java.util.List;

/**
 * @Auther: mayn
 * @Date: 2019/9/5 14:04
 * @Description:
 */
public interface UserMapper extends Mapper<User> {

    List<User> getUserList();

    @Select("select * from user where user_name = #{userName}")
    User findUserByName(String userName);
}
