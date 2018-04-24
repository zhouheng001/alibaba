package com.aliyun.or.util;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;


public class SendEmailBySpringApi {
    /**
     *
     * @param toEmails 所有收件人
     * @param suject 邮件主题
     * @param text 邮件内容
     * @return
     * @throws Exception
     */
        public static String sendEmailBySpringApi(String[] toEmails,String suject,String text) throws Exception{
            JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
            String msg="";
            String emails="";
            // 设定mail server
            // 企业邮箱时使用  senderImpl.setHost("smtp.qiye.163.com");
            senderImpl.setHost("smtp.163.com");  // 个人邮箱

            // 建立邮件消息
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            // 设置收件人，寄件人 用数组发送多个邮件
            mailMessage.setTo(toEmails);
            mailMessage.setFrom("15101516445@163.com");
            mailMessage.setSubject(suject);
            mailMessage.setText(text);

            senderImpl.setUsername("15101516445@163.com"); // 根据自己的情况,设置username
            senderImpl.setPassword("15101516445zh"); // 根据自己的情况, 设置password

            Properties prop = new Properties();
            prop.put("mail.smtp.auth", "true"); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
            prop.put("mail.smtp.timeout", "25000");
            senderImpl.setJavaMailProperties(prop);
            // 发送邮件
            senderImpl.send(mailMessage);
            for (String toEmail : toEmails) {
               emails = emails+","+toEmail;
            }
            emails = emails.substring(emails.indexOf(",")+1,emails.length());
            msg="成功发送内容:"+text+"群发到邮箱"+emails;
            return msg;
        }

    /**
     *
     * @param toEmail 收件人
     * @param suject 邮件主题
     * @param text 邮件内容
     * @return
     * @throws Exception
     */
    public static String sendEmailBySpringApi(String toEmail,String suject,String text) throws Exception{
        JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
        String msg="";
        // 设定mail server
        // 企业邮箱时使用  senderImpl.setHost("smtp.qiye.163.com");
        senderImpl.setHost("smtp.163.com");  // 个人邮箱

        // 建立邮件消息
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        // 设置收件人，寄件人 用数组发送多个邮件
        mailMessage.setTo(toEmail);
        mailMessage.setFrom("15101516445@163.com");
        mailMessage.setSubject(suject);
        mailMessage.setText(text);

        senderImpl.setUsername("15101516445@163.com"); // 根据自己的情况,设置username
        senderImpl.setPassword("15101516445zh"); // 根据自己的情况, 设置password

        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true"); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
        prop.put("mail.smtp.timeout", "25000");
        senderImpl.setJavaMailProperties(prop);
        // 发送邮件
        senderImpl.send(mailMessage);
        msg="成功发送内容:"+text+"到邮箱"+toEmail;
        return msg;
    }

        public static void main(String[] args) throws Exception{
        String[] array = new String[] {"1020886351@qq.com","3367413791@qq.com"};
        String information = sendEmailBySpringApi(array, "这是群发", "简易发送邮件jar包1.0.0.RELEASE");
        System.out.println(information);
    }
}
