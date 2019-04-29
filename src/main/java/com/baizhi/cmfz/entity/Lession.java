package com.baizhi.cmfz.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * author:bobo大人
 * createDate:2018/7/31
 * createTime:15:49
 * description:修行的课程的实体类
 */
public class Lession implements Serializable {
    private String id;
    private String name;
    @JSONField(format = "yyyy/MM/dd HH:mm:ss")
    private Date overtime;
    private Category category;
    private User user;
    private int count;

    public Lession() {
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

    public Date getOvertime() {
        return overtime;
    }

    public void setOvertime(Date overtime) {
        this.overtime = overtime;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Lession{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", overtime=" + overtime +
                ", category=" + category +
                ", user=" + user +
                ", count=" + count +
                '}';
    }
}
