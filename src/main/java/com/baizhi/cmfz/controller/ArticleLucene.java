package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Article;
import com.baizhi.cmfz.entity.Guru;
import com.baizhi.cmfz.service.ArticleService;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * author:bobo大人
 * createDate:2018/8/6
 * createTime:20:56
 * description:
 */
@Component
public class ArticleLucene {

    @Autowired
    private ArticleService articleService;

    public Map<String,Object> getAll(Integer page,Integer rows,String keysword){
        System.out.println(page+"\t"+rows);
        Map<String,Object> map = null;
        try {
            Integer start = (page - 1) * rows;
            Integer end = start + rows;


            //1.指定索引库位置
            Directory open = FSDirectory.open(new File("G://indexDB"));

            //根据关键字查询
            /**
             * 创建查询器,完成数据检索
             */
            IndexReader ir = IndexReader.open(open);
            IndexSearcher indexSearcher = new IndexSearcher(ir);
            /**
             * 参数一:lucene版本
             * 参数二: field  doc的属性名  where  name = ?   || id = ?  || autor = ?
             * 参数三: 分词器  必须与添加时使用的分词器一致
             * 指定查询属性 / 列
             */
            String[] fields = {"author","title","content","creatDate"};
            QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_44,fields,new StandardAnalyzer(Version.LUCENE_44));
            // QueryParser queryParser = new QueryParser(Version.LUCENE_44,"author",new StandardAnalyzer(Version.LUCENE_44));
            // 封装关键字
            Query query = queryParser.parse(keysword);
            /**
             * Query : 查询对象,将关键字封装至Query
             * int :  最终返回的结果条数, 如果不满足,以实际条数返回.
             */
            TopDocs topDocs = indexSearcher.search(query, end);
            /**
             * TopDocs  包含了返回的索引标号数组
             *         ScoreDocs[]  索引标号数组
             *         totalHits    int   关键字命中的数据总条数
             */
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            int totalHits = topDocs.totalHits;
            System.out.println("关键字命中的总条数:"+totalHits);
            //  高亮实现
            // 自定义样式
            Formatter formatter = new SimpleHTMLFormatter("<font color='red'>","</font>");
            // 处理关键字
            Scorer scorer = new QueryScorer(query);
            // 将样式与关键字 交于容器 加工
            Highlighter highlighter = new Highlighter(formatter,scorer);


            List<Article> articleList = new ArrayList<>();
            map = new HashMap<>();

            end = Math.min(end, scoreDocs.length);
            // 遍历索引数组
            for (int i = start;i<end;i++) {
                System.out.println("索引ID:  "+scoreDocs[i].doc);
                // 根据索引id  获取对应的Document对象
                Document doc = indexSearcher.doc(scoreDocs[i].doc);
                // 取加工后的数据
                /**
                 * Analyzer analyzer   分词器  一定要全程保持一致
                 * String fieldName,   doc的属性名
                 * String text         doc的属性值
                 *
                 * keysword  刘   <font>刘</font>
                 *
                 */
                System.out.println(doc);
                String title = highlighter.getBestFragment(new StandardAnalyzer(Version.LUCENE_44),"title",doc.get("title"));
                String image = highlighter.getBestFragment(new StandardAnalyzer(Version.LUCENE_44),"image",doc.get("image"));
                String content = highlighter.getBestFragment(new StandardAnalyzer(Version.LUCENE_44),"content",doc.get("content"));
                String author = highlighter.getBestFragment(new StandardAnalyzer(Version.LUCENE_44),"author",doc.get("author"));
                // 将doc转为 自定义对象
                if(title==null){
                    title = doc.get("title");
                }
                if(image==null){
                    image = doc.get("image");
                }
                if(content==null){
                    content = doc.get("content");
                }
                if(author==null){
                    author = doc.get("author");
                }

                String id = doc.get("id");
                String count = doc.get("count");
                int icount = Integer.parseInt(count);
                String creatDate = doc.get("creatDate");
                Date d  = null;
                if(creatDate != null){
                    Long aLong = Long.parseLong(creatDate);
                    d = new Date(aLong);
                }

                Article article = new Article();
                article.setId(id);
                article.setName(title);
                article.setImage(image);
                article.setContent(content);
                Guru guru = new Guru();
                guru.setName(author);
                article.setGuru(guru);
                article.setCreateDate(d);
                article.setCount(icount);



                articleList.add(article);

            }
            map.put("total",totalHits);
            map.put("rows",articleList);
            map.put("code","200");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code","500");
        }

        return map;
    }

    //创建本地索引库
    public Object addLucene(){
        Map<String,String> map = new HashMap<>();
        IndexWriter indexWriter = null;
        try {
            //1.指定索引库位置
            Directory open = FSDirectory.open(new File("G://indexDB"));
            //2.创建索引库   数据输出流
            //2.1指定索引库的版本
            Version version = Version.LUCENE_44;
            //2.2创建分词器对象
            Analyzer analyzer = new StandardAnalyzer(version);
            //2.3准备配置对象   ？
            IndexWriterConfig conf = new IndexWriterConfig(version,analyzer);
            //  创建索引库
            indexWriter = new IndexWriter(open,conf);

            //3往索引库中添加数据，  准备数据
            List<Article> articleList = articleService.getAll();
            // 将数据写入索引库
            /**
             * Document
             *
             *  lucene 不支持 自定义对象 的添加, 需要将 自定义对象转换为 Document对象 完成添加
             *
             *  一个doc对象对应一个自定义对象
             */
            for (Article article: articleList) {
                Document doc = new Document();
                /**
                 * StringField  用来处理字符类型数据, 不做分词处理
                 * TextField 用来处理字符类型数据,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    做分词处理
                 * LongField  用来处理日期格式
                 * DoubleField 用来处理浮点数
                 * floatField  用来处理浮点数
                 * IntField    用来处理整数类型
                 * 1. name : 代表着当前doc的属性名
                 * 2. value:代表着当前doc的属性值
                 * 3. store: yes: 存入索引库   no:不存入索引库
                 */
                //                    name           value             store
                doc.add(new StringField("id",article.getId(), Field.Store.YES));
                doc.add(new TextField("title",article.getName(), Field.Store.YES));
                doc.add(new TextField("image",article.getImage(), Field.Store.YES));
                doc.add(new TextField("content",article.getContent(), Field.Store.YES));
                doc.add(new TextField("author",article.getGuru().getName(), Field.Store.YES));
                System.out.println(article.getCreateDate().getTime());
                doc.add(new LongField("creatDate",article.getCreateDate().getTime(), Field.Store.YES));
                doc.add(new IntField("count",article.getCount(), Field.Store.YES));

                indexWriter.addDocument(doc);
            }
            indexWriter.commit();
            map.put("code","200");
        } catch (IOException e) {
            e.printStackTrace();
            map.put("code","500");
        }finally {
            try {
                indexWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
                map.put("code","500");
            }
        }

        return map;
    }


    public Map<String,String> deleteLucene(){



        return null;
    }

}
