package com.aliyun.or.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Create by Administrator on 2018/4/11 0011
 */
public class DayUtil {
    public static Date getNextDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, +1);//+1今天的时间加一天
        date = calendar.getTime();
        return date;
    }
}
