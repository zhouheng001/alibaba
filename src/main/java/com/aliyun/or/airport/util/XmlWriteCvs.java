package com.aliyun.or.airport.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.jumpmind.symmetric.csv.CsvWriter;

import java.io.*;
import java.util.Iterator;

/**
 * Create by Administrator on 2018/4/3 0003
 */
public class XmlWriteCvs {

//    static Logger logger = Logger.getLogger(XmlWriteCvs.class);
    /**
     * 把xml文件写入到cvs文件中
     * @param xml
     * @param topTitle
     */
    public static boolean write(String xml,String topTitle,String nowDay,String filepath,String filename) throws IOException, DocumentException {
        boolean result = false;
        Document doc = null;
        File outFile = new File(filepath+"/"+filename);//输出的CSV文
        OutputStreamWriter out = null;
        String[] titles = topTitle.split(",");
        xml = xml.substring(xml.indexOf("<results>"), xml.indexOf("</results>")+"</results>".length());

        doc = DocumentHelper.parseText(xml); // 将字符串转为XML
        Element rootElt = doc.getRootElement(); // 获取根节点
        Iterator iter = rootElt.elementIterator("flgt"); // 获取根节点下的子节点head

        out = new OutputStreamWriter( new FileOutputStream(outFile ), "utf-8" );
        BufferedWriter writer = new BufferedWriter(out);
        CsvWriter cwriter = new CsvWriter(writer,',');

        String topTitleCsv = "operationDate,"+topTitle;
        cwriter.writeRecord(topTitleCsv.split(","), true);

        while (iter.hasNext()) {
            Element recordEle = (Element) iter.next();
            String str="";
            str = nowDay+",";

            for (String title : titles) {
                String aFlgtNo = recordEle.elementTextTrim(title);
                str = str+aFlgtNo+",";
            }
            cwriter.writeRecord(str.split(","), true);
            cwriter.flush();//刷新数据
        }
        System.out.println(filepath+"/"+filename);
        cwriter.close();
        result = true;
        return result;
    }
}
