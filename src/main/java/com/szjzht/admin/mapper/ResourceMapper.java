//package com.szjzht.admin.mapper;
//
//import com.szjzht.admin.model.Resource;
//import org.apache.ibatis.annotations.Select;
//import tk.mybatis.mapper.common.Mapper;
//import java.util.List;
//
///**
// * @Auther: mayn
// * @Date: 2019/9/5 16:54
// * @Description:
// */
//public interface ResourceMapper extends Mapper<Resource> {
//
//    @Select("select r.* from resource r,user u,user_role ur,resource_role rr where u.id = ur.user_id and ur.role_id = rr.role_id and rr.resource_id = r.id and u.user_name = #{userName}")
//    List<Resource> getResourceListByUserName(String userName);
//}
