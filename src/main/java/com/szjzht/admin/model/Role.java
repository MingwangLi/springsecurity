package com.szjzht.admin.model;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

/**
 * @Auther: mayn
 * @Date: 2019/9/5 16:36
 * @Description:
 */
public class Role implements Serializable {

    @Id
    private Integer id;
    private String roleKey; //角色key
    private String roleDesc;//角色名称

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getRoleKey() {
        return roleKey;
    }
    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }
    public String getRoleDesc() {
        return roleDesc;
    }
    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }



}
