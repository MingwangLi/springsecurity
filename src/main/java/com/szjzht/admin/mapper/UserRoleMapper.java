package com.szjzht.admin.mapper;

import com.szjzht.admin.model.Role;
import com.szjzht.admin.model.UserRole;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;
import java.util.List;

/**
 * @Auther: mayn
 * @Date: 2019/9/9 11:26
 * @Description:
 */
public interface UserRoleMapper extends Mapper<UserRole> {

    @Select("select * from role,user_role where role.id = user_role.role_id and user_role.user_id = #{userId}")
    List<Role> getRoleList(Integer userId);
}


