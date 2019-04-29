package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Article;
import com.baizhi.cmfz.service.ArticleService;
import com.baizhi.cmfz.service.GuruService;
import com.baizhi.cmfz.utils.LuceneUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * author:bobo大人
 * createDate:2018/8/6
 * createTime:20:17
 * description:
 */
@Controller
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private GuruService guruService;



    //添加文章
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Object addArticle(Article article, MultipartFile uploadFile, HttpServletRequest request){
        String filename = uploadFile.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String substring = filename.substring(filename.indexOf(".") + 1);
        String image = "/si/"+uuid+"."+substring;
        String contextPath = request.getSession().getServletContext().getRealPath("/picture");
        String realPath = contextPath+image;
        File file = new File(realPath);
        int i = 0;
        try {
            uploadFile.transferTo(file);
            article.setImage(image);
            article.setCreateDate(new Date());
            article.setGuru(guruService.getOne(article.getGuru().getId()));
            i = articleService.addAritle(article);
            LuceneUtil.createIndexDB(article);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            LuceneUtil.closeAndCommit();
        }
        Map<String,String> map = new HashMap<>();
        if(i==1){
            map.put("code","200");
        }else{
            map.put("code","500");
        }
        return map;
    }

    //删除文章
    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public @ResponseBody Object delete(String[] ids){
        int i = 0;
        try {
            System.out.println(Arrays.toString(ids));
            i = articleService.deletes(ids);
            LuceneUtil.deleteIndexDB(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            LuceneUtil.closeAndCommit();
        }
        Map<String,String> map = new HashMap<>();
        if(i!=0){
            map.put("code","200");
        }else{
            map.put("code","500");
        }
        return map;
    }

    //更新文章
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public @ResponseBody Object update(Article article,MultipartFile uploadFile,HttpServletRequest request){
        if(uploadFile == null || uploadFile.getOriginalFilename() == ""){

        }else{
            String filename = uploadFile.getOriginalFilename();
            String uuid = UUID.randomUUID().toString().replace("-", "");
            String substring = filename.substring(filename.indexOf(".") + 1);
            String image = "/si/"+uuid+"."+substring;
            String contextPath = request.getSession().getServletContext().getRealPath("/picture");
            String realPath = contextPath+image;
            File file = new File(realPath);
            try {
                uploadFile.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            article.setImage(image);
        }
        int i = 0;
        try {
            article.setGuru(guruService.getOne(article.getGuru().getId()));
            i = articleService.update(article);
            LuceneUtil.updateIndexDB(article);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            LuceneUtil.closeAndCommit();
        }
        Map<String,String> map = new HashMap<>();
        if(i==1){
            map.put("code","200");
        }else{
            map.put("code","500");
        }
        return map;
    }


    //获取所有文章
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Map<String,Object> getAll(Integer page,Integer rows,String keysword){
        Map<String, Object> map = new HashMap<>();
        if(keysword == null || keysword.equals("")){
            map = articleService.getAll(page, rows);
        }else{
            try {
                /*map = lucene.getAll(page, rows, keysword);*/
                map = LuceneUtil.findIndexDB(keysword, page, rows);
            } catch (Exception e) {
                e.printStackTrace();
                map.put("code","500");
                map.put("rows",new ArrayList<>());
            }finally {
                LuceneUtil.closeAndCommit();
            }
        }
        return map;
    }


    //获取所有文章
    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public @ResponseBody Article getOne(@PathVariable("id")String id){
        Article article = articleService.getOne(id);
        return article;
    }

    //删除本地检索库
    @RequestMapping(value = "/deleteLucene",method = RequestMethod.DELETE)
    public @ResponseBody Map<String,String> deleteLucene(){
        Map<String,String> map = new HashMap<>();
        try {
            LuceneUtil.deleteIndexDB();
            map.put("code","200");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code","500");
        }finally {
            LuceneUtil.closeAndCommit();
        }
        return map;
    }

    //添加索引库
    @RequestMapping(value = "/addLucene",method = RequestMethod.POST)
    public @ResponseBody Map<String,String> addLucene(){
        Map<String,String> map = new HashMap<>();
        try {
            List<Article> list = articleService.getAll();
            for (Article article : list) {
                LuceneUtil.createIndexDB(article);
            }
            map.put("code","200");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code","500");
        }finally {
            LuceneUtil.closeAndCommit();
        }
        return map;
    }
}
