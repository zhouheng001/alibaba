package com.aliyun.or.airport.service.impl;

import com.aliyun.or.airport.service.WriterCvsService;
import com.aliyun.or.airport.util.XmlWriteCvs;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@PropertySource(value = {"file:C:/Users/wb-zh388722/Desktop/gitlab_workspace1/AirportSmartSchedule/src/main/resources/writecvs.properties"},encoding = "gbk",ignoreResourceNotFound = true)
public class WriterCvsServiceImpl implements WriterCvsService {

    @Value("${cvs.title}")
    private String topTitle;
    @Value("${cvs.filepath}")
    private String filepath;
    @Value("${cvs.filename}")
    private String filename;

    @Override
    public boolean writeCvs(String xml) throws IOException, DocumentException {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String nowDay=df.format(date);
        boolean flag = XmlWriteCvs.write(xml, topTitle, nowDay, filepath, filename);
        return flag;
    }
}
