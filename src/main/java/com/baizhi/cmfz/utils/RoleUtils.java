package com.baizhi.cmfz.utils;

import com.baizhi.cmfz.dao.ResourcesDAO;
import com.baizhi.cmfz.dao.RoleDAO;
import com.baizhi.cmfz.entity.Resource;
import com.baizhi.cmfz.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * author:bobo大人
 * createDate:2018/8/14
 * createTime:17:34
 * description:
 */
public class RoleUtils {
    public static void getSonResource(Resource parentR, ResourcesDAO resourcesDAO){
        // 根据父类id查询子类
        List<Resource> sonList = resourcesDAO.selectSonResource(parentR.getId());
        // 判断当前节点集合是否为null,如果不为null 继续查询
        if(sonList!=null||sonList.size()>0){
            // 将子级节点集合放入父级集合属性中
            parentR.setResourceList(sonList);
            for (Resource r:sonList) {
                getSonResource(r,resourcesDAO);
            }
        }
    }

    public static void getSonResource(Resource parentR, RoleDAO roleDAO, String roleName){
        // 根据父类id查询子类
        List<Resource> sonList = roleDAO.selectSonResource(parentR.getId(),roleName);
        // 判断当前节点集合是否为null,如果不为null 继续查询
        if(sonList!=null||sonList.size()>0){
            // 将子级节点集合放入父级集合属性中
            parentR.setResourceList(sonList);
            for (Resource r:sonList) {
                getSonResource(r,roleDAO,roleName);
            }
        }
    }

    public static void getSonResources(Resource parentR, RoleDAO roleDAO, String[] roleNames){
        // 根据父类id查询子类
        List<Resource> sonList = roleDAO.selectSonResources(parentR.getId(),roleNames);
        // 判断当前节点集合是否为null,如果不为null 继续查询
        if(sonList!=null||sonList.size()>0){
            // 将子级节点集合放入父级集合属性中
            parentR.setResourceList(sonList);
            for (Resource r:sonList) {
                getSonResources(r,roleDAO,roleNames);
            }
        }
    }


    public static List<TreeNode> getTree(List<Resource> resources,List<TreeNode> list){
        if(resources!= null && resources.size() != 0 ){
            for(Resource resource : resources){
                TreeNode tn1 = new TreeNode();
                tn1.setId(resource.getId());
                tn1.setText(resource.getName());
                Map<String, String> attributes = tn1.getAttributes();
                attributes.put("url",resource.getUrl());
                attributes.put("code",resource.getCode());
                attributes.put("type",resource.getType());
                list.add(tn1);
                List<TreeNode> children = tn1.getChildren();
                if(resource.getResourceList()!= null && resource.getResourceList().size() != 0 ){
                    getTree(resource.getResourceList(),children);
                }
            }
        }

        return list;
    }
}
