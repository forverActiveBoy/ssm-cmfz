package com.baizhi.cmfz.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * author:bobo大人
 * createDate:2018/8/5
 * createTime:10:04
 * description:专辑
 */
public class Listen implements Serializable {
    private String id;
    private String name;
    private String author;
    private String teller;
    //专辑集数
    private int episodes;
    @JSONField(format = "yyyy/MM/dd HH:mm:ss")
    private Date createDate;
    private String content;
    private String image;
    //专辑热度
    private int star;

    public Listen() {
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTeller() {
        return teller;
    }

    public void setTeller(String teller) {
        this.teller = teller;
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Listen listen = (Listen) o;
        return episodes == listen.episodes &&
                star == listen.star &&
                Objects.equals(id, listen.id) &&
                Objects.equals(name, listen.name) &&
                Objects.equals(author, listen.author) &&
                Objects.equals(teller, listen.teller) &&
                Objects.equals(createDate, listen.createDate) &&
                Objects.equals(content, listen.content) &&
                Objects.equals(image, listen.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, author, teller, episodes, createDate, content, image, star);
    }

    @Override
    public String toString() {
        return "Listen{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", teller='" + teller + '\'' +
                ", episodes=" + episodes +
                ", createDate=" + createDate +
                ", content='" + content + '\'' +
                ", image='" + image + '\'' +
                ", star=" + star +
                '}';
    }
}
