package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Category;
import com.baizhi.cmfz.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * author:bobo大人
 * createDate:2018/8/9
 * createTime:22:56
 * description:
 */
@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Map<String,Object> getAll(Integer page,Integer rows){
        Map<String, Object> map = categoryService.getDefault(page, rows);
        return map;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public @ResponseBody Map<String,String> deletes(String[] ids){
        int i = categoryService.deletes(ids);
        Map<String,String> map = new HashMap<>();
        if(i!=0){
            map.put("code","200");
        }else{
            map.put("code","500");
        }
        return map;
    }

    @RequestMapping(value = "update",method = RequestMethod.POST)
    public @ResponseBody Map<String,String> update(Category category){
        int i = categoryService.update(category);
        Map<String,String> map = new HashMap<>();
        if(i!=0){
            map.put("code","200");
        }else{
            map.put("code","500");
        }
        return map;
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Map<String,String> addCateogry(Category category){
        Map<String,String> map = null;
        try {
            int i = categoryService.addCategory(category);
            map = new HashMap<>();
            if(i!=0){
                map.put("code","200");
            }else{
                map.put("code","500");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
