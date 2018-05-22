package com.aliyun.or.airport.service.impl;

import com.alibaba.sendemail.springeamil.SendEmailBySpringApi;
import com.aliyun.or.airport.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
@PropertySource(value = "classpath:email.properties",encoding = "gbk")
public class EmailServiceImpl implements EmailService {

    @Value("${email.host}")
    String host;
    @Value("${email.subject}")
    String subject;
    @Value("${email.username}")
    String username;
    @Value("${email.password}")
    String password;
    @Value("${email.toemail}")
    String toEmails;
    @Value("${email.filepath}")
    String filepath;

    @Override
    public void sendEmailAttachment(String text, String filename) throws MessagingException {
        SendEmailBySpringApi sendEmailBySpringApi=new SendEmailBySpringApi(username,password,host);
        sendEmailBySpringApi.sendEmailWithAttachment(toEmails.split(","),subject,text,filename,filepath);
    }

    @Override
    public void sendEmailText(String text) throws Exception {
        SendEmailBySpringApi sendEmailBySpringApi=new SendEmailBySpringApi(username,password,host);
        sendEmailBySpringApi.sendEmailBySpringApi(toEmails.split(","),subject,text);
    }
}
