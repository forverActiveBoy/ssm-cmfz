package com.baizhi.cmfz.entity;

import java.io.Serializable;

/**
 * author:bobo大人
 * createDate:2018/7/31
 * createTime:16:14
 * description:轮播图的实体类
 */
public class Banner implements Serializable {
    private int id;
    private String image;
    private String name;
    private String status;
    //展示位置
    private int dec;

    public Banner() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getDec() {
        return dec;
    }

    public void setDec(int dec) {
        this.dec = dec;
    }

    @Override
    public String toString() {
        return "Banner{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", dec=" + dec +
                '}';
    }
}
