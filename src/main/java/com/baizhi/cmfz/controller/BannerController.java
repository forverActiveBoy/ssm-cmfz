package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Banner;
import com.baizhi.cmfz.service.BannerService;
import org.apache.ibatis.ognl.DynamicSubscript;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.apache.ibatis.ognl.DynamicSubscript.all;

/**
 * author:bobo大人
 * createDate:2018/8/5
 * createTime:14:16
 * description:
 */
@Controller
@RequestMapping("/banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Map<String,Object> getAll(Integer page,Integer rows){

        Map<String, Object> map = null;
        try {
            map = bannerService.getAll(page, rows);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }


    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Map<String,String> addBanner(Banner banner,MultipartFile uploadFile,HttpServletRequest request){
        int i = 0;
        try {
            String filename = uploadFile.getOriginalFilename();
            String uuid = UUID.randomUUID().toString().replace("-", "");
            String substring = filename.substring(filename.indexOf(".") + 1);
            String image = "/shouye/"+uuid+"."+substring;
            String contextPath = request.getSession().getServletContext().getRealPath("/picture");
            String realPath = contextPath+image;
            File file = new File(realPath);
            uploadFile.transferTo(file);
            banner.setImage("/picture"+image);
            i = bannerService.addBanner(banner);
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
    public @ResponseBody Object updateBanner(Banner banner, MultipartFile uploadFile,HttpServletRequest request){
        System.out.println(banner);
        int i = 0;
        try {
            if(uploadFile == null || uploadFile.getOriginalFilename() == ""){

            }else{
                System.out.println(uploadFile.getOriginalFilename());
                String filename = uploadFile.getOriginalFilename();
                String uuid = UUID.randomUUID().toString();
                String substring = filename.substring(filename.indexOf(".") + 1);
                String newName = uuid+"."+substring;
                String realPath = request.getSession().getServletContext().getRealPath("/picture");
                File file = new File(realPath+"/shouye/"+newName);
                banner.setImage("picture/shouye/"+newName);
                uploadFile.transferTo(file);
            }

            i = bannerService.updateBanner(banner);
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

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public @ResponseBody Object getBanner(@PathVariable("id") Integer id){
        Banner banner = null;
        try {
            banner = bannerService.getBanner(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return banner;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public @ResponseBody Object getBanner(@PathVariable("id") String id,int[] ids){
        int i = bannerService.deleteBanners(ids);
        Map<String,String> map = new HashMap<>();
        if(i!=0){
            map.put("code","200");
        }else{
            map.put("code","500");
        }
        return map;
    }
}
