package com.baizhi.cmfz.entity;

import java.io.Serializable;

/**
 * author:bobo大人
 * createDate:2018/7/31
 * createTime:15:46
 * description:修行的类别的实体类
 */
public class Category implements Serializable {
    private String id;
    private String name;
    private User user;

    public Category() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", user=" + user +
                '}';
    }
}
