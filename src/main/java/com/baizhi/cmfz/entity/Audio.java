package com.baizhi.cmfz.entity;

import java.io.Serializable;

/**
 * author:bobo大人
 * createDate:2018/7/31
 * createTime:15:38
 * description:专辑中音频的实体类
 */
public class Audio implements Serializable {
    private String id;
    private String name;
    private Listen listen;
    private String url;
    private String size;
    private int count;

    public Audio() {
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

    public Listen getListen() {
        return listen;
    }

    public void setListen(Listen listen) {
        this.listen = listen;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Audio{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", listen=" + listen +
                ", url='" + url + '\'' +
                ", size='" + size + '\'' +
                ", count=" + count +
                '}';
    }
}
