package com.baizhi.cmfz.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * author:bobo大人
 * createDate:2018/8/9
 * createTime:14:26
 * description:
 */
public class CMFZLog implements Serializable {
    private int id;
    private String methodname;
    @JSONField(format = "yyyy/MM/dd HH:mm:ss")
    private Date createdate;
    private String consumetime;
    private String username;
    private String result;

    public CMFZLog() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMethodname() {
        return methodname;
    }

    public void setMethodname(String methodname) {
        this.methodname = methodname;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getConsumetime() {
        return consumetime;
    }

    public void setConsumetime(String consumetime) {
        this.consumetime = consumetime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CMFZLog cmfzLog = (CMFZLog) o;
        return id == cmfzLog.id &&
                Objects.equals(methodname, cmfzLog.methodname) &&
                Objects.equals(createdate, cmfzLog.createdate) &&
                Objects.equals(consumetime, cmfzLog.consumetime) &&
                Objects.equals(username, cmfzLog.username) &&
                Objects.equals(result, cmfzLog.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, methodname, createdate, consumetime, username, result);
    }

    @Override
    public String toString() {
        return "CMFZLog{" +
                "id=" + id +
                ", methodname='" + methodname + '\'' +
                ", createdate=" + createdate +
                ", consumetime='" + consumetime + '\'' +
                ", username='" + username + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
