package com.szjzht.admin.model;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @Auther: mayn
 * @Date: 2019/9/5 19:48
 * @Description:
 */
public class UserRole implements Serializable {

    @Id
    private Integer id;
    private Integer userId;
    private Integer roleId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
