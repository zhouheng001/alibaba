package com.aliyun.or.airport.service.impl;

import com.aliyun.or.airport.service.FtpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FtpServiceImplTest {

    @Autowired
    private FtpService ftpService;

    @Test
    public void ftpUploadfile() {
        FileInputStream in = null;
        try {
            in = new FileInputStream(new File("C:/Users/wb-zh388722/Desktop/airport.csv"));
            ftpService.ftpUploadfile("airport.csv","log",in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
}
}