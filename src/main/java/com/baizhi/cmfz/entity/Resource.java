package com.baizhi.cmfz.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * author:bobo大人
 * createDate:2018/8/3
 * createTime:19:38
 * description:资源类型实体类
 */
public class Resource implements Serializable {

    private int id;
    // 资源名字
    private String name;
    // 资源路径
    private String url;
    // 资源类型
    private String type;
    // 资源标识
    private String code;
    private List<Resource> resourceList;

    public Resource() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Resource> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<Resource> resourceList) {
        this.resourceList = resourceList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resource resource = (Resource) o;
        return id == resource.id &&
                Objects.equals(name, resource.name) &&
                Objects.equals(url, resource.url) &&
                Objects.equals(type, resource.type) &&
                Objects.equals(code, resource.code) &&
                Objects.equals(resourceList, resource.resourceList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, url, type, code, resourceList);
    }

    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", type='" + type + '\'' +
                ", code='" + code + '\'' +
                ", resourceList=" + resourceList +
                '}';
    }
}
