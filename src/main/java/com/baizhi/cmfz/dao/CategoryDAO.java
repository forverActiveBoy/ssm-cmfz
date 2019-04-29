package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.Category;

import java.util.List;

public interface CategoryDAO {
    public int insert(Category category);
    public int deletes(String[] ids);
    public List<Category> selectAll(int id);
    public List<Category> selectDefault(Integer start,Integer rows);
    public int selectDefaultCount();
    public int update(Category category);
}
