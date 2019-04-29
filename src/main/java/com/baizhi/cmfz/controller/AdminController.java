package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Admin;
import com.baizhi.cmfz.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * author:bobo大人
 * createDate:2018/8/13
 * createTime:21:18
 * description:
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/login")
    public @ResponseBody Map<String,String> login(String username, String password){
        Map<String,String> map = new HashMap<>();
        if(adminService.login(username,password)){
            map.put("code","200");
            return map;
        }else{
            map.put("code","500");
            return map;
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Map<String,Object> getAll(Integer page,Integer rows){
        Map<String, Object> all = adminService.getAll(page, rows);
        return all;
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Map<String,String> addAdmin(Admin admin,int[] ids){
        int i = adminService.insert(admin,ids);
        Map<String,String> map = new HashMap<>();
        if(i==1){
            map.put("code","200");
        }else{
            map.put("code","500");
        }
        return map;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public @ResponseBody Map<String,String> deletes(String[] ids){
        int i = adminService.deletes(ids);
        Map<String,String> map = new HashMap<>();
        if(i!=0){
            map.put("code","200");
        }else{
            map.put("code","500");
        }
        return map;
    }

    @RequestMapping(value = "update",method = RequestMethod.POST)
    public @ResponseBody Map<String,String> update(Admin admin,int[] ids){
        int i = adminService.update(admin,ids);
        Map<String,String> map = new HashMap<>();
        if(i==1){
            map.put("code","200");
        }else{
            map.put("code","500");
        }
        return map;
    }
}
