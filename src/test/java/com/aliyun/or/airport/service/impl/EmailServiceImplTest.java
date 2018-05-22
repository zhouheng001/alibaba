package com.aliyun.or.airport.service.impl;

import com.aliyun.or.airport.service.EmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailServiceImplTest {

    @Autowired
    private EmailService emailService;

    @Test
    public void sendEmailAttachment() {
        try {
            emailService.sendEmailAttachment("阿里巴巴(中国服务大厦)数据抽样,具体数据见附件","springcloud.docx");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sendEmailText() {
        try {
            emailService.sendEmailText("测试发文本");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}