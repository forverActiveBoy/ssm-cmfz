package com.baizhi.cmfz.service.impl;

import com.baizhi.cmfz.dao.ResourcesDAO;
import com.baizhi.cmfz.dao.RoleDAO;
import com.baizhi.cmfz.entity.Resource;
import com.baizhi.cmfz.entity.Role;
import com.baizhi.cmfz.entity.TreeNode;
import com.baizhi.cmfz.service.ResourcesService;
import com.baizhi.cmfz.utils.RoleUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * author:bobo大人
 * createDate:2018/8/3
 * createTime:21:15
 * description:tree的业务类  菜单工具
 */
@Service
public class ResourcesServiceImpl implements ResourcesService {
    @Autowired
    private ResourcesDAO rd;

    @Autowired
    private RoleDAO roleDAO;
    @Override
    public List<TreeNode> getAll() {

        /*List<Resource> resourceList = rd.selectAll();

        List<TreeNode> list = new ArrayList<>();

        for (Resource resource : resourceList) {
            TreeNode tn1 = new TreeNode();
            tn1.setId(resource.getId());
            tn1.setText(resource.getName());
            List<TreeNode> children = tn1.getChildren();
            for (Resource r : resource.getResourceList()) {
                TreeNode tn2 = new TreeNode();
                tn2.setId(r.getId());
                tn2.setText(r.getName());
                Map<String, String> attributes = tn2.getAttributes();
                attributes.put("url",r.getUrl());
                attributes.put("code",r.getCode());
                attributes.put("type",r.getType());
                children.add(tn2);
            }
            list.add(tn1);
        }*/


        Subject subject = SecurityUtils.getSubject();

        String uname = subject.getPrincipal().toString();

        List<Role> roleList = roleDAO.selectAllRoleByAdmin(uname);
        String[] usernames = new String[roleList.size()];
        int i = 0;
        for(Role role : roleList){
            usernames[i] = role.getRolename();
            i++;
        }

        List<Resource> parentResources = roleDAO.selectParentResourcesByRoleName(usernames);

        for(Resource resource : parentResources){
            RoleUtils.getSonResources(resource,roleDAO,usernames);
        }

        List<TreeNode> list = new ArrayList<>();
        RoleUtils.getTree(parentResources,list);

        return list;
    }

    @Override
    public List<TreeNode> getAllResources() {



        List<Resource> resources = rd.selectParentResource();

        for(Resource resource : resources){
            RoleUtils.getSonResource(resource,rd);
        }

        List<TreeNode> list = new ArrayList<>();
        RoleUtils.getTree(resources,list);

        return list;
    }
}
