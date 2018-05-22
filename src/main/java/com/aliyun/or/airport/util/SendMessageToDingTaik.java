package com.aliyun.or.airport.util;

import java.util.Properties;

public class SendMessageToDingTaik {

    public static void sendMessageToDingTaik(String URL,String text){
        String message ="{\"msgtype\": \"text\",\"text\": {\"content\": \"" +
                text +
                "\"},\"at\": {\"atMobiles\": [], \"isAtAll\": false}}";
        HttpJsonUtil.doPost(URL,message);
    }
}
