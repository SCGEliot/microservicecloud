package com.scg.springcloud.service.impl;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * @author: Eliot
 * @date: 2020/10/29
 **/
@Service
public class LuceneServiceImpl {

    private static String text = "谷歌地图之父 跳槽 facebook";

    //2 索引目录类,指定索引在硬盘中的位置
    private static Directory directory = null;
    //3 创建分词器对象
    private static Analyzer analyzer = null;
    //4 索引写出工具的配置对象
    private static IndexWriterConfig conf = null;
    //5 创建索引的写出工具类。参数：索引的目录和配置信息
    private static IndexWriter indexWriter = null;
    private static String luceneIndexPathName = "F:\\luceneIndex";

    public static void initLuceneUtil() throws IOException{
        if (directory == null) {
            directory = FSDirectory.open(Paths.get(luceneIndexPathName));
        }
        if (indexWriter == null) {
            analyzer = new StandardAnalyzer();
            conf = new IndexWriterConfig(analyzer);
            indexWriter = new IndexWriter(directory, conf);
        }
    }

    // 创建索引
    public static long addIndex() throws IOException{
        try {
            initLuceneUtil();
            Document document = new Document();//1 创建文档对象
            // 创建并添加字段信息。参数：字段的名称、字段的值、是否存储，
            // 这里选Store.YES代表存储到文档列表。Store.NO代表不存储
            document.add(new StringField("id", "1", Field.Store.YES));
            // 这里我们title字段需要用TextField，即创建索引又会被分词。StringField会创建索引，但是不会被分词
            document.add(new TextField("title", text, Field.Store.YES));

            //6 把文档交给IndexWriter,会返回一个序列号
            long sequenceNumber  = indexWriter.addDocument(document);
            indexWriter.commit(); //7 提交
            System.out.println("索引提交成功，序列号：" + sequenceNumber);
            return sequenceNumber;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            destroy();//8 关闭
        }
        return 0;
    }

    private static void destroy() {
        try {
            if (indexWriter != null) {
                indexWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("索引写出器关闭失败！！！" + e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException{
        long sequenceNumber = addIndex();
    }

}
