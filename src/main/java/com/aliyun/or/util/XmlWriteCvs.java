package com.aliyun.or.util;

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
    public static boolean write(String xml,String topTitle,String nowDay) {
        boolean result = false;
        Document doc = null;
        File outFile = new File("C:\\temp\\bcia_Gate\\airport.csv");//输出的CSV文
        OutputStreamWriter out = null;
        String[] titles = topTitle.split(",");
        try{
            xml = xml.substring(xml.indexOf("<results>"), xml.indexOf("</results>")+"</results>".length());
        }catch (Exception e){
//            System.out.println("查询结果是空!");
            e.printStackTrace();
        }
        try {
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

                //如当天时间超过该时间则安排第二天航班
                //if(time>1130){
                //      str = nextDay+",";
                //       }else {
                //      str = nowDay+",";
                //}

                str = nowDay+",";

                for (String title : titles) {
                    String aFlgtNo = recordEle.elementTextTrim(title);
                    str = str+aFlgtNo+",";
                }
                cwriter.writeRecord(str.split(","), true);
                cwriter.flush();//刷新数据
            }
//            System.out.println("//ali//airport//airport.csv success");
            System.out.println("C:\\temp\\bcia_Gate\\airport.csv success");
            cwriter.close();
            result = true;
            return result;
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
