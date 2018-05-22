package com.aliyun.or.airport.service;

import javax.mail.MessagingException;

public interface EmailService {

     void sendEmailAttachment(String text,String filename) throws MessagingException;

     void sendEmailText(String text) throws Exception;
}
