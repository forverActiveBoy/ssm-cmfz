package com.baizhi.cmfz.service.impl;

import com.baizhi.cmfz.dao.CategoryDAO;
import com.baizhi.cmfz.entity.Category;
import com.baizhi.cmfz.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author:bobo大人
 * createDate:2018/8/9
 * createTime:21:52
 * description:
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDAO categoryDAO;
    @Override
    public int addCategory(Category category) {
        return categoryDAO.insert(category);
    }

    @Override
    public int deletes(String[] ids) {
        return categoryDAO.deletes(ids);
    }

    @Override
    public int update(Category category) {
        return categoryDAO.update(category);
    }

    @Override
    public Map<String, Object> getDefault(Integer page,Integer rows) {
        Integer start = (page - 1)*rows;
        List<Category> categoryList = categoryDAO.selectDefault(start, rows);
        int count = categoryDAO.selectDefaultCount();
        Map<String,Object> map = new HashMap<>();
        map.put("total",count);
        map.put("rows",categoryList);
        return map;
    }
}
