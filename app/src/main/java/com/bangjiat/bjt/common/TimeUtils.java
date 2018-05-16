package com.bangjiat.bjt.common;

import com.orhanobut.logger.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/4/18 0018
 */

public class TimeUtils {
    public static String changeToYMD(Long l) {
        Date date = new Date(l);
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        return format.format(date);
    }

    public static String changeToTime(Long l) {
        Date date = new Date(l);
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd hh:mm");
        return format.format(date);
    }

    public static String changeToHM(Long l) {
        Date date = new Date(l);
        SimpleDateFormat format = new SimpleDateFormat("hh:mm");
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

    public static String getTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd", Locale.CHINA);
        String format1 = format.format(new Date());
        return format1;
    }

    public static String formatDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd", Locale.CHINA);
        String str = format.format(date);
        return str;
    }


    /**
     * 将一个时间转换成提示性时间字符串，如刚刚，1秒前
     *
     * @param timeStamp
     * @return
     */
    public static String convertTimeToFormat(long timeStamp) {
        long curTime = System.currentTimeMillis() / (long) 1000;
        long time = curTime - timeStamp / (long) 1000;

        if (time < 60 && time >= 0) {
            return "刚刚";
        } else if (time >= 60 && time < 3600) {
            return time / 60 + "分钟前";
        } else if (time >= 3600 && time < 3600 * 24) {
            return time / 3600 + "小时前";
        } else if (time >= 3600 * 24 && time < 3600 * 24 * 30) {
            return time / 3600 / 24 + "天前";
        } else if (time >= 3600 * 24 * 30 && time < 3600 * 24 * 30 * 12) {
            return time / 3600 / 24 / 30 + "个月前";
        } else if (time >= 3600 * 24 * 30 * 12) {
            return time / 3600 / 24 / 30 / 12 + "年前";
        } else {
            return "刚刚";
        }
    }

    /**
     * 获取当天0点的毫秒数
     *
     * @return
     */
    public static long getBeginOfDay() {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        Date beginOfDate = calendar1.getTime();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        Logger.d("当天0点时间:" + format.format(beginOfDate));
        return beginOfDate.getTime();
    }

    /**
     * 获取当月0点的毫秒数
     *
     * @return
     */
    public static long getBeginOfMonth() {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), 1, 0, 0, 0);
        Date beginOfDate = calendar1.getTime();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        Logger.d("当月0点时间:" + format.format(beginOfDate));
        return beginOfDate.getTime();
    }

    /**
     * 获取当天日
     */
    public static int getDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }
}
