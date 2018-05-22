package com.aliyun.or.airport;

import com.aliyun.or.airport.util.SendMessageToDingTaik;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    public static void main(String[] args) {
       SendMessageToDingTaik.sendMessageToDingTaik("https://oapi.dingtalk.com/robot/send?access_token=6be7a156943799290e9f9ccf2fed8cba3706f9c743ecc049d4849e0b56f9cfac","中国服务大厦云监控平台异常信息：测试格式用例"+"，任务名称：runJobBuildCsv"+"，日期时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

    }
}
