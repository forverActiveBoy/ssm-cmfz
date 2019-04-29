package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Guru;
import com.baizhi.cmfz.service.GuruService;
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
 * createDate:2018/8/6
 * createTime:15:32
 * description:
 */
@Controller
@RequestMapping("/guru")
public class GuruController {
    @Autowired
    private GuruService guruService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Map<String,Object> getAll(Integer page, Integer rows){

        Map<String, Object> map = guruService.getAll(page, rows);
        return map;
    }

    @RequestMapping(value = "/addArticle",method = RequestMethod.GET)
    public @ResponseBody List<Guru> getAllToAddAriticle(){
        List<Guru> guruList = guruService.getAllToAddAriticle();
        return guruList;
    }

    @RequestMapping(value = "{id}",method = RequestMethod.POST)
    public @ResponseBody Object update(Guru guru, MultipartFile uploadFile, HttpServletRequest request){
        int i = 0;
        try {
            if(uploadFile == null || uploadFile.getOriginalFilename() == ""){

            }else{
                String filename = uploadFile.getOriginalFilename();
                String substring = filename.substring(filename.indexOf(".") + 1);
                String uuid = UUID.randomUUID().toString();
                uuid = uuid.replace("-", "");
                String path = request.getSession().getServletContext().getRealPath("/picture")+"/shangshi/"+uuid+"."+substring;

                File file = new File(path);
                uploadFile.transferTo(file);
                guru.setImage("/shangshi/"+uuid+"."+substring);
            }
            System.out.println(guru.getImage());
            i = guruService.update(guru);
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

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public @ResponseBody Object delete(@PathVariable("id")String id,String[] ids){
        int i = guruService.deletes(ids);
        Map<String,String> map = new HashMap<>();
        if(i==1){
            map.put("code","200");
        }else{
            map.put("code","500");
        }
        return map;
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Object addGuru(Guru guru,MultipartFile uploadFile,HttpServletRequest request){

        int i = 0;
        try {
            if(uploadFile == null || uploadFile.getOriginalFilename() == ""){

            }else{
                String filename = uploadFile.getOriginalFilename();
                String substring = filename.substring(filename.indexOf(".") + 1);
                String uuid = UUID.randomUUID().toString();
                uuid = uuid.replace("-", "");
                String path = request.getSession().getServletContext().getRealPath("/picture")+"/shangshi/"+uuid+"."+substring;

                File file = new File(path);
                uploadFile.transferTo(file);
                guru.setImage("/shangshi/"+uuid+"."+substring);
            }
            i = guruService.addGuru(guru);
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
