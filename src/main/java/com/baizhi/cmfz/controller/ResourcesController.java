package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.TreeNode;
import com.baizhi.cmfz.service.ResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * author:bobo大人
 * createDate:2018/8/4
 * createTime:11:22
 * description:菜单控制器
 */
@Controller
@RequestMapping("/resource")
public class ResourcesController {
    @Autowired
    private ResourcesService rs;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<TreeNode> getTree(){
        List<TreeNode> all = rs.getAll();
        return all;
    }

    @RequestMapping(value = "getAll",method = RequestMethod.GET)
    public @ResponseBody List<TreeNode> getAllResources(){
        List<TreeNode> all = rs.getAllResources();
        return all;
    }
}
