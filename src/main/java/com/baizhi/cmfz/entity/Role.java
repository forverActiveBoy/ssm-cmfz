package com.baizhi.cmfz.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * author:bobo大人
 * createDate:2018/8/14
 * createTime:15:15
 * description:
 */
public class Role implements Serializable {
    private int id;
    private String rolename;
    private List<Resource> resourceList;

    public Role() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public List<Resource> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<Resource> resourceList) {
        this.resourceList = resourceList;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", rolename='" + rolename + '\'' +
                ", resourceList=" + resourceList +
                '}';
    }
}
