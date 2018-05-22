package com.aliyun.or.airport.service;

import java.io.InputStream;

public interface FtpService {

    boolean ftpUploadfile(String filename,String ftpFilepath,InputStream input);
}
