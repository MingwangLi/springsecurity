package com.szjzht.admin.model;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @Auther: mayn
 * @Date: 2019/9/5 19:46
 * @Description:
 */
public class RoleResource implements Serializable {

    @Id
    private Integer id;
    private Integer roleId;
    private Integer resourceId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }
}
