package com.baizhi.cmfz.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * author:bobo大人
 * createDate:2018/7/31
 * createTime:15:12
 * description:用户信息的实体类
 */
public class User implements Serializable {
    private int id;
    private String telphone;
    private String password;
    private String imageName;
    private String nikename;
    private String name;
    private String sex;
    private String autograph;
    private String userSheng;
    private String userShi;
    private Guru guru;

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getNikename() {
        return nikename;
    }

    public void setNikename(String nikename) {
        this.nikename = nikename;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAutograph() {
        return autograph;
    }

    public void setAutograph(String autograph) {
        this.autograph = autograph;
    }

    public String getUserSheng() {
        return userSheng;
    }

    public void setUserSheng(String userSheng) {
        this.userSheng = userSheng;
    }

    public String getUserShi() {
        return userShi;
    }

    public void setUserShi(String userShi) {
        this.userShi = userShi;
    }

    public Guru getGuru() {
        return guru;
    }

    public void setGuru(Guru guru) {
        this.guru = guru;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(telphone, user.telphone) &&
                Objects.equals(password, user.password) &&
                Objects.equals(imageName, user.imageName) &&
                Objects.equals(nikename, user.nikename) &&
                Objects.equals(name, user.name) &&
                Objects.equals(sex, user.sex) &&
                Objects.equals(autograph, user.autograph) &&
                Objects.equals(userSheng, user.userSheng) &&
                Objects.equals(userShi, user.userShi) &&
                Objects.equals(guru, user.guru);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, telphone, password, imageName, nikename, name, sex, autograph, userSheng, userShi, guru);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", telphone='" + telphone + '\'' +
                ", password='" + password + '\'' +
                ", imageName='" + imageName + '\'' +
                ", nikename='" + nikename + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", autograph='" + autograph + '\'' +
                ", userSheng='" + userSheng + '\'' +
                ", userShi='" + userShi + '\'' +
                ", guru=" + guru +
                '}';
    }
}
