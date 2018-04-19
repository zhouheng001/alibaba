package com.aliyun.or.util;

import java.io.InputStream;
import java.util.Properties;

/**
 * Create by Administrator on 2018/4/9 0009
 */
public class FtpUploadFile {

    private static String ftpHost="";
    private static int ftpPort=21; //设置默认端口21
    private static String ftpUser="";
    private static String ftpPassword="";
    static{
        try {
            Properties properties = new Properties();
            properties.load(FtpUploadFile.class.getClassLoader().getResourceAsStream("FtpUtil.properties"));
            String type=properties.getProperty("type");
            ftpHost = properties.getProperty(type+"host");
            ftpPort = Integer.parseInt(properties.getProperty(type+"port"));
            ftpUser = properties.getProperty(type+"user");
            ftpPassword = properties.getProperty(type+"password");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Description: 向FTP服务器上传文件
     * @param filename 上传到FTP服务器上的文件名
     * @param input 输入流
     * @return 成功返回true，否则返回false
     */
    public static boolean ftpUploadfile(String filename,String ftpFilepath,InputStream input){
        boolean flag = FtpUtil.uploadFile(ftpHost, ftpPort, ftpUser, ftpPassword,filename,ftpFilepath,input);
        return flag;
    }
}
