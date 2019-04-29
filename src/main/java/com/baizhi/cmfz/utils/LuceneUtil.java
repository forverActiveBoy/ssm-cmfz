package com.baizhi.cmfz.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baizhi.cmfz.entity.Article;
import com.baizhi.cmfz.entity.Guru;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.Scorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class LuceneUtil {
	private static Version version;
	private static IndexWriterConfig indexConfig;
	private static Analyzer analyzer;
	private static Directory directory;
	private static ThreadLocal<IndexWriter> t = new ThreadLocal<IndexWriter>();
	static {
		try {
			directory = FSDirectory.open(new File("G://indexDB"));
			version = Version.LUCENE_44;
			analyzer = new IKAnalyzer();
			indexConfig = new IndexWriterConfig(version, analyzer);
		} catch (Exception e) {
			System.out.println("静态代码块出错");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 创建索引库
	 * @param article
	 * @throws Exception
	 */
	public static void createIndexDB(Article article) throws Exception{
		// 获取indexWriter 输出流
		IndexWriter indexWriter = getIndexWriter();
		// 将自定义对象转换为doc
		Document doc = getDocument(article);
		// 将doc 添加至 索引库
		indexWriter.addDocument(doc);

	}
	/*
	* 索引库的检索  分页
	* */
	public static Map<String,Object> findIndexDB(String keysword,Integer page,Integer rows) throws Exception{
		IndexReader ir = IndexReader.open(directory);
		IndexSearcher indexSearcher = new IndexSearcher(ir);
		String[] fields = {"guru","name","content"};
		QueryParser queryParser = new MultiFieldQueryParser(version, fields, analyzer);
		Query query = queryParser.parse(keysword);
		Formatter formatter = new SimpleHTMLFormatter("<font color='red'><b>", "</b></font>");
		Scorer scorer = new QueryScorer(query);
		Highlighter highlighter = new Highlighter(formatter, scorer);
		TopDocs search = indexSearcher.search(query, page*rows);
		List<Article> guruWenList = new ArrayList<Article>();
		ScoreDoc[] scoreDocs = search.scoreDocs;
		int start = (page-1)*rows ;
		int end =  Math.min(scoreDocs.length,page*rows);
		for (int i = start; i < end; i++) {
			int num = scoreDocs[i].doc;
			Document doc = indexSearcher.doc(num);
			highlighter.getBestFragment(analyzer, "name", doc.get("name"));
			String wz_id = doc.get("id");
			String guruid = doc.get("guruid");
			String wz_name = highlighter.getBestFragment(analyzer, "name", doc.get("name"));
			if(wz_name==null){
				wz_name = doc.get("name");
			}
			String wz_image = doc.get("image");
			String wz_content = highlighter.getBestFragment(analyzer, "content", doc.get("content"));
			if(wz_content==null){
				wz_content = doc.get("content");
			}
			String guruName = highlighter.getBestFragment(analyzer, "guru", doc.get("guru"));
			if(guruName==null){
				guruName = doc.get("guru");
			}
			String wz_date = doc.get("date");
			Long valueOf = Long.valueOf(wz_date);
			Date date = new Date(valueOf);
			String wz_count = doc.get("count");
			Article guruWen = new Article();
			Guru guru = new Guru();
			guru.setNikename(guruName);
			guru.setId(guruid);
			guruWen.setGuru(guru);
			guruWen.setContent(wz_content);
			guruWen.setCount(Integer.valueOf(wz_count));
			guruWen.setCreateDate(date);
			guruWen.setId(wz_id);
			guruWen.setImage(wz_image);
			guruWen.setName(wz_name);
			guruWenList.add(guruWen);
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", guruWenList);
		map.put("total", search.totalHits);
		ir.close();
		return map;
	}

	/**
	 * 索引库的更新
	 * @param article
	 * @throws Exception
	 */
	public static void updateIndexDB(Article article) throws Exception{
		IndexWriter indexWriter = getIndexWriter();
		Document doc = getDocument(article);
		indexWriter.updateDocument(new Term("id",article.getId()), doc);

	}

	/**
	 * 索引库的 批量删除
	 * @param ids
	 * @throws Exception
	 */
	public static void deleteIndexDB(String[] ids) throws Exception{

		IndexWriter indexWriter = getIndexWriter();

		Term[] terms = new Term[ids.length];

		for (int i=0;i<ids.length;i++) {
			Term term = new Term("id",ids[i]);
			terms[i] = term;
		}

		indexWriter.deleteDocuments(terms);

	}

	/**
	 * 删除所有
	 * @throws Exception
	 */
	public static void deleteIndexDB() throws Exception{
		IndexWriter indexWriter = getIndexWriter();
		indexWriter.deleteAll();
		indexWriter.commit();
	}

	/**
	 * JavaBean 转换为 Doc
	 * @param article
	 * @return
	 */
	public static Document getDocument(Article article){
		Document doc = new Document();
		doc.add(new StringField("id",article.getId(),Store.YES));
		doc.add(new StringField("guruid",article.getGuru().getId(),Store.YES));
		doc.add(new TextField("name",article.getName(),Store.YES));
		doc.add(new StringField("image",article.getImage(),Store.YES));
		doc.add(new StringField("content",article.getContent(),Store.YES));
		doc.add(new TextField("guru",article.getGuru().getNikename(),Store.YES));
		doc.add(new LongField("date",article.getCreateDate().getTime(),Store.YES));
		doc.add(new IntField("count",article.getCount(),Store.YES));
		return doc;
	}

	/**
	 * 获取indexWriter
	 * @return
	 * @throws Exception
	 */
	private static IndexWriter getIndexWriter()throws Exception{
		IndexWriter indexWriter = t.get();
		if(indexWriter==null){
			indexWriter = new IndexWriter(directory, indexConfig);
			t.set(indexWriter);
		}
		return indexWriter;
	}

	/**
	 * 关闭资源
	 */
	public static void closeAndCommit(){
		IndexWriter indexWriter = t.get();
		if(indexWriter!=null){
			try {
				indexWriter.commit();
				indexWriter.close();
				t.remove();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	 
}
