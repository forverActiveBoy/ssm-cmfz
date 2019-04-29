package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Audio;
import com.baizhi.cmfz.entity.Listen;
import com.baizhi.cmfz.service.AudioService;
import com.baizhi.cmfz.service.ListenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * author:bobo大人
 * createDate:2018/8/8
 * createTime:22:32
 * description:
 */
@Controller
@RequestMapping("/audio")
public class AudioController {
    @Autowired
    private AudioService audioService;
    @Autowired
    private ListenService listenService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Map<String,Object> getAll(Integer page,Integer rows){
        Map<String, Object> map = audioService.getAll(page, rows);
        return map;
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Map<String,String> addAudio(Audio audio, MultipartFile uploadFile, HttpServletRequest request){

        int i = 0;
        try {
            Listen listen = listenService.getOne(audio.getListen().getId());
            String filename = uploadFile.getOriginalFilename();
            String saveName = audio.getName() + "." + filename.substring(filename.indexOf(".") + 1);
            String contextPath = request.getSession().getServletContext().getRealPath("/picture");
            System.out.println(contextPath);
            contextPath = contextPath.replace("\\picture","");
            System.out.println(contextPath);
            File file = new File(contextPath);
            String parent = file.getParent();
            File audioFile = new File(parent+"/audio/"+listen.getName());
            System.out.println(audioFile.getAbsolutePath());
            if(!audioFile.exists()){
                audioFile.mkdirs();
            }
            File newFile = new File(parent+"/audio/"+listen.getName()+"/"+saveName);

            audio.setUrl("/audio/"+listen.getName()+"/"+saveName);
            uploadFile.transferTo(newFile);

            i = audioService.addAudio(audio);
        } catch (Exception e) {
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

    @RequestMapping(method = RequestMethod.DELETE)
    public @ResponseBody Map<String,String> deletes(String[] ids){
        int i = audioService.deletes(ids);
        Map<String,String> map = new HashMap<>();
        if(i==1){
            map.put("code","200");
        }else{
            map.put("code","500");
        }
        return map;
    }

    @RequestMapping(value = "{id}",method = RequestMethod.POST)
    public @ResponseBody Map<String,String> update(Audio audio,MultipartFile uploadFile,HttpServletRequest request){

        int i = 0;
        try {
            if(uploadFile == null || uploadFile.getOriginalFilename() ==""){

            }else{
                String filename = uploadFile.getOriginalFilename();
                String saveName = audio.getName() + "." + filename.substring(filename.indexOf(".") + 1);
                String contextPath = request.getSession().getServletContext().getRealPath("/picture");
                System.out.println(contextPath);
                contextPath = contextPath.replace("\\picture","");
                System.out.println(contextPath);
                File file = new File(contextPath);
                String parent = file.getParent();
                File audioFile = new File(parent+"/audio");
                System.out.println(audioFile.getAbsolutePath());
                if(!audioFile.exists()){
                    audioFile.mkdir();
                }
                File newFile = new File(parent+"/audio/"+saveName);
                Listen listen = listenService.getOne(audio.getListen().getId());
                audio.setUrl("/audio/"+listen.getName()+"/"+saveName);
                uploadFile.transferTo(newFile);
            }
            i = audioService.update(audio);
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
}
