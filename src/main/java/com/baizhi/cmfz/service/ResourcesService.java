package com.baizhi.cmfz.service;

import com.baizhi.cmfz.annotation.LogAnnotation;
import com.baizhi.cmfz.entity.Resource;
import com.baizhi.cmfz.entity.TreeNode;

import java.util.List;
import java.util.Map;

public interface ResourcesService {
    @LogAnnotation("查询tree")
    public List<TreeNode> getAll();
    @LogAnnotation("查询所有资源")
    public List<TreeNode> getAllResources();
}
