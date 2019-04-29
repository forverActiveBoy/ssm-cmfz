package com.baizhi.cmfz.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * author:bobo大人
 * createDate:2018/8/13
 * createTime:20:01
 * description:
 */
public class Admin implements Serializable {
    private String id;
    private String username;
    private String password;
    private String beforepassword;
    private String passwordsalt;
    private List<Role> roleList;

    public Admin() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBeforepassword() {
        return beforepassword;
    }

    public void setBeforepassword(String beforepassword) {
        this.beforepassword = beforepassword;
    }

    public String getPasswordsalt() {
        return passwordsalt;
    }

    public void setPasswordsalt(String passwordsalt) {
        this.passwordsalt = passwordsalt;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", beforepassword='" + beforepassword + '\'' +
                ", passwordsalt='" + passwordsalt + '\'' +
                ", roleList=" + roleList +
                '}';
    }
}
