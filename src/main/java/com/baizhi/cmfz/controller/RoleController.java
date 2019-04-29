package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Role;
import com.baizhi.cmfz.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author:bobo大人
 * createDate:2018/8/14
 * createTime:19:17
 * description:
 */
@Controller
@RequestMapping("role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Map<String,Object> getAll(Integer page,Integer rows){
        Map<String, Object> map = roleService.getAll(page, rows);
        return map;
    }

    @RequestMapping(value = "getAll",method = RequestMethod.GET)
    public @ResponseBody List<Role> getAllRole(){
        List<Role> roleList = roleService.getAllRole();
        return roleList;
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Map<String,String> addRole(int[] ids,String rolename){
        int i = 0;
        try {
            i = roleService.addRole(ids, rolename);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String,String> map = new HashMap<>();
        if(i!=0){
            map.put("code","200");
        }else{
            map.put("code","500");
        }
        return map;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public @ResponseBody Map<String,String> deletes(int[]ids){
        int i = 0;
        try {
            i = roleService.deletes(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String,String> map = new HashMap<>();
        if(i!=0){
            map.put("code","200");
        }else{
            map.put("code","500");
        }
        return map;
    }

    @RequestMapping(value = "update",method = RequestMethod.POST)
    public @ResponseBody Map<String,String> update(int[] ids, Role role,String newRoleName){
        int i = 0;
        try {
            i = roleService.update(ids, role,newRoleName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String,String> map = new HashMap<>();
        if(i!=0){
            map.put("code","200");
        }else{
            map.put("code","500");
        }
        return map;
    }
}
