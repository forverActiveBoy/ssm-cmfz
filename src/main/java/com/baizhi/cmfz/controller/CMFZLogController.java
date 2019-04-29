package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.service.CMFZLogService;
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
 * createDate:2018/8/9
 * createTime:19:15
 * description:
 */
@Controller
@RequestMapping("/cmfzlog")
public class CMFZLogController {
    @Autowired
    private CMFZLogService cmfzLogService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Map<String,Object> getAll(Integer page, Integer rows){

        Map<String, Object> map = null;
        try {
            map = cmfzLogService.getAll(page, rows);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public @ResponseBody Map<String,String> deletes(int[] ids){
        System.out.println(ids);
        int i = cmfzLogService.deletes(ids);
        Map<String,String> map = new HashMap<>();
        if(i!=0){
            map.put("code","200");
        }else{
            map.put("code","500");
        }
        return map;
    }

    @RequestMapping(value = "/out",method = RequestMethod.GET)
    public void out(){
        int i = cmfzLogService.outCMFZLog();
    }
}
