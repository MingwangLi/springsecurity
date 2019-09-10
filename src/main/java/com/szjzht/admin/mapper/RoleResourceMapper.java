//package com.szjzht.admin.mapper;
//
//import com.szjzht.admin.model.Role;
//import com.szjzht.admin.model.RoleResource;
//import org.apache.ibatis.annotations.Select;
//import tk.mybatis.mapper.common.Mapper;
//
//import java.util.List;
//
///**
// * @Auther: mayn
// * @Date: 2019/9/9 11:20
// * @Description:
// */
//public interface RoleResourceMapper extends Mapper<RoleResource> {
//
//
//    @Select("select * from role,role_resource where role.id = role_resource.role_id and role_resource.resource_id = #{resouceId}")
//    List<Role> getRoleList(Integer resouceId);
//}
