package com.szjzht.admin.model;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

/**
 * @Auther: mayn
 * @Date: 2019/9/5 16:35
 * @Description:
 */
public class Resource implements Serializable {


    @Id
    private Integer id;
    private String name;//资源名称
    private Integer parentId;//父资源
    private String resUrl;//资源链接
    private String resKey;//资源key
    private Integer type;//资源类型   1:菜单    2：按钮
    private Integer sort;//排序

    private List<Role> roles; //可访问角色


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getResUrl() {
        return resUrl;
    }
    public void setResUrl(String resUrl) {
        this.resUrl = resUrl;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSort() {
        return sort;
    }
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public List<Role> getRoles() {
        return roles;
    }
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getResKey() {
        return resKey;
    }
    public void setResKey(String resKey) {
        this.resKey = resKey;
    }

}
