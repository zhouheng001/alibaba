package com.aliyun.or.start;

import com.aliyun.or.airport.QueryDataService;
import com.aliyun.or.airport.QueryDataServiceService;
import com.aliyun.or.util.FtpUploadFile;
import com.aliyun.or.util.SendEmailBySpringApi;
import com.aliyun.or.util.XmlWriteCvs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Create by Administrator on 2018/4/18 0018
 */
public class QueryDataServiceClient {
    public static void main(String[] args){

        try{
            //获取服务的代理类并通过代理类实现业务
            QueryDataServiceService queryDataServiceService = new QueryDataServiceService();
            QueryDataService queryDataServicePort = queryDataServiceService.getQueryDataServicePort();

            //获得系统当前时间
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            String nowDay=df.format(date);
            System.out.println(nowDay);

            SimpleDateFormat timeformat = new SimpleDateFormat("yyyyMMddHHmmss");
            String time=timeformat.format(date);
            System.out.println(time);

            //请求xml参数
            String xmlString="<filters><auth><userCode>ali01</userCode><userPassword>ali01</userPassword></auth><baseFilters><operationDate>"
                    +nowDay+
                    "</operationDate><aord>D</aord></baseFilters></filters>";

            //定义cvs文件的表头
            String topTitle="aFlgtId,airlineCode,aFlgtNo,aFlgttypeCode,aLarptCode,aDOrI,aStndCode,aVipRank,aCnrsStatus,sta,eta,ata,aTrmlCode,dFlgtId,dFlgtNo,dFlgttypeCode,dLarptCode,dDOrI,dStndCode,dVipRank,dCnrsStatus,std,etd,atd,dTrmlCode,acsortCode,actypeCode,regCode";

            //通过代理类调用服务方法
            String xmlresult = queryDataServicePort.queryFlightData(xmlString);

            //在Linux下生成csv文件
            boolean xmlWrite = XmlWriteCvs.write(xmlresult, topTitle,nowDay);//传入查询结果xmlresult,及cvs文件topTitle头标签

            //判断csv文件是否生成成功
            if (xmlWrite){
                try {
                    //将文件发送到一线服务windows下ftp服务器
                    FileInputStream in = new FileInputStream(new File("C:\\temp\\bcia_Gate\\airport.csv"));
                    boolean flag = FtpUploadFile.ftpUploadfile("airport.csv","",in);

                    //将文件再次发送作为备份文件
                    FileInputStream in_log = new FileInputStream(new File("C:\\temp\\bcia_Gate\\airport.csv"));
                    //根据当前时间设置备份文件名称
                    String log_airport = time+"airport_log.csv";
                    boolean logflag = FtpUploadFile.ftpUploadfile(log_airport,"log" ,in_log);

                    //判断文件是否上传成功
                    if(flag){
                        System.out.println("airport.csv  has been uploaded successfully!"+"DateTime:"+time);

                        //判断此次备份文件是否上传成功
                        if(logflag){
                            System.out.println(log_airport+" has been uploaded successfully!"+"DateTime:"+time);
                        }else{
                            System.out.println(log_airport+" has been uploaded failed!"+"DateTime:"+time);
                        }
                    }else{
                        System.out.println("airport.csv has been uploaded failed!"+"DateTime:"+time);
                    }
                } catch (FileNotFoundException e) {
                    try {
                        SendEmailBySpringApi.sendEmailBySpringApi("1020886351@qq.com", "中国服务大厦微服务", e.getMessage());
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }catch (Exception e){
            try {
                SendEmailBySpringApi.sendEmailBySpringApi("1020886351@qq.com", "中国服务大厦微服务", e.getMessage());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}
