package com.bangjiat.bjt.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/4/18 0018
 */

public class TimeUtils {
    public static String changeToYMD(Long l) {
        Date date = new Date(l);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static long changeToLong(String s) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(s);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return System.currentTimeMillis();
    }
}
