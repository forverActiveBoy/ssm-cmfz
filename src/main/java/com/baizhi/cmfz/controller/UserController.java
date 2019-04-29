package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Guru;
import com.baizhi.cmfz.entity.User;
import com.baizhi.cmfz.service.UserService;
import com.baizhi.cmfz.utils.PoiUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * author:bobo大人
 * createDate:2018/8/10
 * createTime:11:33
 * description:
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PoiUtils poiUtils;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Map<String,Object> getAll(Integer page, Integer rows){
        Map<String, Object> map = userService.getAll(page, rows);
        return map;
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public @ResponseBody Map<String,String> update(HttpServletRequest request, User user, MultipartFile uploadFile){
        int i = 0;
        try {
            if(uploadFile!= null && uploadFile.getOriginalFilename() != ""){
                String realPath = request.getSession().getServletContext().getRealPath("/picture/userpicture");
                String telphone = user.getTelphone();
                String filename = uploadFile.getOriginalFilename();
                String substring = filename.substring(filename.indexOf(".") + 1);
                String saveName = UUID.randomUUID().toString().replace("-","")+"."+substring;
                File file = new File(realPath+"/"+telphone);
                if(!file.exists()){
                    file.mkdirs();
                }
                File newFile = new File(realPath+"/"+telphone+"/"+saveName);
                user.setImageName("/"+telphone+"/"+saveName);
                uploadFile.transferTo(newFile);
            }
            i = userService.update(user);
        } catch (IOException e) {
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
    public @ResponseBody Map<String,String> deletes(int[] ids){
        int i = userService.deletes(ids);
        Map<String,String> map = new HashMap<>();
        if(i!=0){
            map.put("code","200");
        }else{
            map.put("code","500");
        }
        return map;
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Map<String,String> addUser(HttpServletRequest request, User user, MultipartFile uploadFile){
        int i = 0;
        try {
            if(uploadFile== null || uploadFile.getOriginalFilename() == ""){

            }else{
                String realPath = request.getSession().getServletContext().getRealPath("/picture/userpicture");
                String telphone = user.getTelphone();
                String filename = uploadFile.getOriginalFilename();
                String substring = filename.substring(filename.indexOf(".") + 1);
                String saveName = UUID.randomUUID().toString().replace("-","")+"."+substring;
                File file = new File(realPath+"/"+telphone);
                if(!file.exists()){
                    file.mkdirs();
                }
                File newFile = new File(realPath+"/"+telphone+"/"+saveName);
                user.setImageName("/"+telphone+"/"+saveName);
                uploadFile.transferTo(newFile);
            }
            i = userService.addUser(user);
        } catch (IOException e) {
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

    @RequestMapping(value = "/out",method = RequestMethod.GET)
    public void out(int[] ids, HttpServletResponse response){
        List<User> userList = null;
        if(ids != null && ids.length !=0){
            userList = userService.exportUser(ids);
        }else {
            userList = userService.exportUser();
        }
        HSSFWorkbook workbook = poiUtils.exportUser(userList);
        // 1. 设置响应体内容
        response.setContentType("application/octet-stream");
        response.setHeader("content-Disposition","attachament;filename=1.xls");
        ServletOutputStream os = null;
        try {
            os = response.getOutputStream();
            workbook.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(os!=null){
                try {
                    os.flush();
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @RequestMapping(value = "import",method = RequestMethod.POST)
    public void inportUser(MultipartFile fileName){
        List<User> users = poiUtils.importUser(fileName);
        int i = userService.importUser(users);
    }

}
