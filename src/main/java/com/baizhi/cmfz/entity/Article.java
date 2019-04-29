package com.baizhi.cmfz.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * author:bobo大人
 * createDate:2018/7/31
 * createTime:15:27
 * description:上师文章的实体类
 */
public class Article implements Serializable {
    private String id;
    private String name;
    private String image;
    private String content;
    private Guru guru;
    @JSONField(format = "yyyy/MM/dd HH:mm:ss")
    private Date createDate;
    private int count;

    public Article() {
        super();
    }

    public Article(String id, String name, String image, String content, Guru guru, Date createDate, int count) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.content = content;
        this.guru = guru;
        this.createDate = createDate;
        this.count = count;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Guru getGuru() {
        return guru;
    }

    public void setGuru(Guru guru) {
        this.guru = guru;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", content='" + content + '\'' +
                ", guru=" + guru +
                ", createDate=" + createDate +
                ", count=" + count +
                '}';
    }
}
