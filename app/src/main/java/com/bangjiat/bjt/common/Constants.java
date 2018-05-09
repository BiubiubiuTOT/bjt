package com.bangjiat.bjt.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.bangjiat.bjt.module.me.personaldata.beans.CompanyUserBean;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2018/4/14 0014.
 * BANGJIATUANDASHUJU
 */

public class Constants {
    public static final String BASE_IP = "http://192.168.0.112:80/app/";
    public static final String UPLOAD_IMAGE_IP = "http://192.168.0.112:8887/";
    public static final String TOKEN_NAME = "j4sc-bjt-token";
    public static String[] WEEK = {"周一", "周二", "周三",
            "周四", "周五", "周六", "周日"};
    public static int[] WEEK_INDEX = {1, 2, 3, 4, 5, 6, 7};

    public static boolean isIntoCompany() {
        CompanyUserBean companyUserBean = CompanyUserBean.first(CompanyUserBean.class);
        return companyUserBean != null;
    }

    public static String sHA1(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), PackageManager.GET_SIGNATURES);
            byte[] cert = info.signatures[0].toByteArray();
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] publicKey = md.digest(cert);
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < publicKey.length; i++) {
                String appendString = Integer.toHexString(0xFF & publicKey[i])
                        .toUpperCase(Locale.US);
                if (appendString.length() == 1)
                    hexString.append("0");
                hexString.append(appendString);
                hexString.append(":");
            }
            String result = hexString.toString();
            return result.substring(0, result.length() - 1);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getTime() {
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        String time = df.format(day);
        return time;
    }

    /**
     * 当前时间处于(当天)时间段内:时分秒
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static boolean isInDay(String startTime, String endTime) {
        Calendar cal = Calendar.getInstance();// 当前日期
        int hour = cal.get(Calendar.HOUR_OF_DAY);// 获取小时
        int minute = cal.get(Calendar.MINUTE);// 获取分钟
        int minuteOfDay = hour * 60 + minute;

        //开始时间
        String[] start = startTime.split(":");
        int sth = Integer.parseInt(start[0]);//小时
        int stm = Integer.parseInt(start[1]);//秒
        int start1 = sth * 60 + stm;

        //结束时间
        String[] end = endTime.split(":");
        int eth = Integer.parseInt(end[0]);//小时
        int etm = Integer.parseInt(end[1]);//秒
        int end1 = eth * 60 + etm;


        if (end1 < start1) {//21:00-07:00
            return minuteOfDay >= start1 || minuteOfDay <= end1;
        } else {//20:00-22:00
            return minuteOfDay >= start1 && minuteOfDay <= end1;
        }
    }

    public static String DateToWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayIndex = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayIndex < 1 || dayIndex > 7) {
            return null;
        }
        return WEEK[dayIndex - 1];
    }

    /**
     * 当天处于周几
     *
     * @return
     */
    public static int dayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        int dayIndex = calendar.get(Calendar.DAY_OF_WEEK);
        return dayIndex;
    }
}
