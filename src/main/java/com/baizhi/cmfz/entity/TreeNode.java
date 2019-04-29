package com.baizhi.cmfz.entity;

import java.io.Serializable;
import java.util.*;

/**
 * author:bobo大人
 * createDate:2018/8/3
 * createTime:21:18
 * description:菜单  tree
 */
public class TreeNode implements Serializable {
    private int id;
    private String text;
    private Map<String,String> attributes = new HashMap<String, String>();
    private String state = "open";
    private List<TreeNode> children = new ArrayList<TreeNode>();

    public TreeNode() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreeNode treeNode = (TreeNode) o;
        return id == treeNode.id &&
                Objects.equals(text, treeNode.text) &&
                Objects.equals(attributes, treeNode.attributes) &&
                Objects.equals(state, treeNode.state) &&
                Objects.equals(children, treeNode.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, attributes, state, children);
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", attributes=" + attributes +
                ", state='" + state + '\'' +
                ", children=" + children +
                '}';
    }
}
