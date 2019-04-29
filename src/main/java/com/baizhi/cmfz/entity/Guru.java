package com.baizhi.cmfz.entity;

import java.io.Serializable;

/**
 * author:bobo大人
 * createDate:2018/7/31
 * createTime:15:21
 * description:上师实体类
 */
public class Guru implements Serializable {
    private String id;
    private String name;
    private String image;
    private String nikename;

    public Guru() {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNikename() {
        return nikename;
    }

    public void setNikename(String nikename) {
        this.nikename = nikename;
    }

    @Override
    public String toString() {
        return "Guru{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", nikename='" + nikename + '\'' +
                '}';
    }
}
