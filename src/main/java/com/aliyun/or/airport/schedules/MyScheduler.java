package com.aliyun.or.airport.schedules;

import com.aliyun.or.airport.service.EmailService;
import com.aliyun.or.airport.service.FtpService;
import com.aliyun.or.airport.service.WriterCvsService;
import com.aliyun.or.airport.util.SendMessageToDingTaik;
import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Component
@Async
@PropertySource("classpath:job.properties")
public class MyScheduler {

    @Autowired
    private WriterCvsService writerCvsService;

    @Autowired
    private FtpService ftpService;

    @Autowired
    private EmailService emailService;

    @Value("${dingtalk.url}")
    private String url;

    /**
     * 每隔5分钟执行任务在服务器生成csv文件并上传至运控的终端ftp上
     */
    @Scheduled(fixedDelay = 1000*60*5)
    public void runJobBuildCsv() throws Exception {

        try {
            JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();

            Client client = dcf.createClient("http://10.40.1.79/acdmwebservice/services/queryDataService?wsdl");
            Date date = new Date();

            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            String nowDay=df.format(date);
            String paramxml ="<filters><auth><userCode>ali01</userCode><userPassword>ali01</userPassword></auth><baseFilters><operationDate>"
                    +nowDay+
                    "</operationDate><aord>D</aord></baseFilters></filters>";
            Object[] objects = new Object[0];

            SimpleDateFormat timeformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time=timeformat.format(new Date());
            log.info("任务名称：runJobBuildCsv---任务执行时间："+time);

            objects = client.invoke("queryFlightData", paramxml);
            String xmlresult = (String) objects[0];
            boolean xmlWrite = writerCvsService.writeCvs(xmlresult);

            if(xmlWrite) {
                FileInputStream in = new FileInputStream(new File("/ali/airport/airport.csv"));
                ftpService.ftpUploadfile("airport.csv", "", in);

                //将文件再次发送作为备份文件
                FileInputStream in_log = new FileInputStream(new File("/ali/airport/airport.csv"));
                //根据当前时间设置备份文件名称
                String log_airport = time + "airport_log.csv";
                ftpService.ftpUploadfile(log_airport, "log", in_log);
            }

            int hour = Integer.parseInt(time.substring(8, 10));
            int minute = Integer.parseInt(time.substring(10,12));
            if(hour==1){
                if(minute<20&&minute>0) {
                    //通过钉钉
                    SendMessageToDingTaik.sendMessageToDingTaik(url,"中国服务大厦云监控平台运行正常，" + "，日期：" + new SimpleDateFormat("yyyyMMdd").format(new Date()));
                }
            }

        } catch (Exception e) {
          SendMessageToDingTaik.sendMessageToDingTaik(url,"中国服务大厦云监控平台异常信息："+e.getMessage()+"，任务名称：runJobBuildCsv"+"，日期时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
          log.error(e.getMessage());
          e.printStackTrace();
        }
}

    /**
     * 定时每天的1点,9点,17点对数据抽样，并通过邮件附件给相关人员
     */
    @Scheduled(cron = "${jobs.schedule}")
    public void runJobSampling() throws Exception {
        try {
            SimpleDateFormat timeformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time=timeformat.format(new Date());
            log.info("任务名称：runJobSampling---任务执行时间："+time);
            emailService.sendEmailAttachment("阿里巴巴(中国服务大厦)数据抽样,具体数据见附件","airport.csv");
        } catch (MessagingException e) {
            e.printStackTrace();
            log.error("任务名称：runJobSampling---异常信息"+e.getMessage());
            SendMessageToDingTaik.sendMessageToDingTaik(url,"中国服务大厦云监控平台异常信息："+e.getMessage()+"，任务名称：runJobSampling"+"，日期时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        }
    }

}
