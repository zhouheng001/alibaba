package com.aliyun.or.airport.service.impl;

import com.aliyun.or.airport.service.FtpService;
import com.aliyun.or.airport.util.FtpUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.InputStream;


@Service
@PropertySource(value = {"file:C:/Users/wb-zh388722/Desktop/gitlab_workspace1/AirportSmartSchedule/src/main/resources/ftp.properties"},ignoreResourceNotFound = true)
public class FtpServiceImpl implements FtpService {

    @Value("${ftp.host}")
    private  String ftpHost;
    @Value("${ftp.port}")
    private  int ftpPort;
    @Value("${ftp.username}")
    private  String ftpUser;
    @Value("${ftp.password}")
    private  String ftpPassword;


    @Override
    public boolean ftpUploadfile(String filename, String ftpFilepath, InputStream input) {
        boolean flag = FtpUtil.uploadFile(ftpHost, ftpPort, ftpUser, ftpPassword, filename, ftpFilepath, input);
        return flag;
    }
}
