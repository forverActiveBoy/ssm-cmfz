package com.baizhi.cmfz.service;

import com.baizhi.cmfz.annotation.LogAnnotation;
import com.baizhi.cmfz.entity.Category;

import java.util.Map;

public interface CategoryService {
    @LogAnnotation("添加功课")
    public int addCategory(Category category);
    @LogAnnotation("删除功课")
    public int deletes(String[] ids);
    @LogAnnotation("更新功课")
    public int update(Category category);
    @LogAnnotation("查询默认功课")
    public Map<String,Object> getDefault(Integer page, Integer rows);
}
