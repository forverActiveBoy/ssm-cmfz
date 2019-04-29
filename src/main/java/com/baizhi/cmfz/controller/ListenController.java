package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Listen;
import com.baizhi.cmfz.service.ListenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * author:bobo大人
 * createDate:2018/8/7
 * createTime:15:00
 * description:
 */
@Controller
@RequestMapping("/listen")
public class ListenController {
    @Autowired
    private ListenService listenService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Map<String,Object> getAll(Integer page, Integer rows){
        Map<String, Object> map = null;
        try {
            map = listenService.getAll(page, rows);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Map<String,String> addListen(Listen listen, MultipartFile uploadFile, HttpServletRequest request){
        int i = 0;
        try {
            String filename = uploadFile.getOriginalFilename();
            String suffix = filename.substring(filename.indexOf(".") + 1);
            String realName = UUID.randomUUID().toString()+"."+suffix;
            String contextPath = request.getSession().getServletContext().getRealPath("/picture");
            listen.setImage("/audioCollection/"+realName);
            uploadFile.transferTo(new File(contextPath+"/audioCollection/"+realName));
            i = listenService.addListen(listen);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String,String> map = new HashMap<>();
        if(i==1){
            map.put("code","200");
        }else{
            map.put("code","500");
        }
        return map;
    }

    @RequestMapping(value = "{id}",method = RequestMethod.POST)
    public @ResponseBody Map<String,String> update(Listen listen,MultipartFile uploadFile,HttpServletRequest request){
        if(uploadFile == null || uploadFile.getOriginalFilename() == ""){

        }else{
            try {
                String filename = uploadFile.getOriginalFilename();
                String suffix = filename.substring(filename.indexOf(".") + 1);
                String realName = UUID.randomUUID().toString()+"."+suffix;
                String contextPath = request.getSession().getServletContext().getRealPath("/picture");
                listen.setImage("/audioCollection/"+realName);
                uploadFile.transferTo(new File(contextPath+"/audioCollection/"+realName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        int i = listenService.update(listen);
        Map<String,String> map = new HashMap<>();
        if(i==1){
            map.put("code","200");
        }else{
            map.put("code","500");
        }
        return map;
    }

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public @ResponseBody Map<String,String> delete(@PathVariable("id") String id,String[] ids){
        int i = listenService.deletes(ids);
        Map<String,String> map = new HashMap<>();
        if(i==1){
            map.put("code","200");
        }else{
            map.put("code","500");
        }
        return map;
    }

    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public @ResponseBody List<Listen> getAll(){
        List<Listen> listenList = null;
        try {
            listenList = listenService.getAllAlbum();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listenList;
    }
}
