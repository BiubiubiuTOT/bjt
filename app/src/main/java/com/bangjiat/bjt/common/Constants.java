package com.bangjiat.bjt.common;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.view.View;

import com.adorkable.iosdialog.AlertDialog;
import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.home.work.kaoqin.beans.RuleInput;
import com.bangjiat.bjt.module.me.personaldata.beans.BuildUser;
import com.bangjiat.bjt.module.me.personaldata.beans.CompanyUserBean;
import com.bangjiat.bjt.module.me.personaldata.beans.SpaceUser;
import com.bangjiat.bjt.module.me.personaldata.beans.UserInfo;
import com.orm.SugarRecord;

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
    /**
     * 本地服务器地址
     */
//    public static final String BASE_IP = "http://192.168.0.112:80/app/";
//    public static final String UPLOAD_IMAGE_IP = "http://192.168.0.112:8887/";

    /**
     * 外网服务器地址
     */
    public static final String BASE_IP = "http://api.bangjiat.com/app/";
    public static final String UPLOAD_IMAGE_IP = "http://www.bangjiat.com/";

    public static final String TOKEN_NAME = "j4sc-bjt-token";
    public static final String TOKEN_NAME_SPECIAL = "j4sc-b-token";


    public static final int SIZE = 10;

    public static String[] WEEK = {"周一", "周二", "周三",
            "周四", "周五", "周六", "周日"};
    public static int[] WEEK_INDEX = {1, 2, 3, 4, 5, 6, 7};

    public static boolean isIntoCompany() {
        CompanyUserBean companyUserBean = CompanyUserBean.first(CompanyUserBean.class);
        return companyUserBean != null;
    }

    /**
     * 获取签名
     *
     * @param context
     * @return
     */
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

    /**
     * @param time
     * @param startTime
     * @return true;迟到
     */
    public static boolean isLate(String time, String startTime) {
        String[] end = time.split(":");
        int eth = Integer.parseInt(end[0]);//小时
        int etm = Integer.parseInt(end[1]);//秒
        int time1 = eth * 60 + etm;

        //开始时间
        String[] start = startTime.split(":");
        int sth = Integer.parseInt(start[0]);//小时
        int stm = Integer.parseInt(start[1]);//秒
        int start1 = sth * 60 + stm;

        return time1 > start1;
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

    public static void showErrorDialog(Context context, String msg) {
        new AlertDialog(context).builder().setMsg(msg).
                setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }).show();
    }

    public static void showSuccessExitDialog(final Activity context, String msg) {
        new AlertDialog(context).builder().setMsg(msg).setCancelable(false).
                setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        context.finish();
                    }
                }).show();
    }

    public static String getLeaveTypeStr(Context context, int type) {
        Resources res = context.getResources();
        String[] formats = res.getStringArray(R.array.leave_type);
        return formats[type - 1];
    }

    /**
     * +
     * 公司管理员
     *
     * @return
     */
    public static boolean isCompanyAdmin() {
        CompanyUserBean first = CompanyUserBean.first(CompanyUserBean.class);
        if (first == null) return false;

        int type = first.getType();
        return type == 3;
    }

    /**
     * 工作台管理员
     */
    public static boolean isWorkAdmin() {
        CompanyUserBean first = CompanyUserBean.first(CompanyUserBean.class);
        if (first == null) return false;
        int type = first.getType();

        return type == 2;
    }

    /**
     * 楼宇管理员
     *
     * @return
     */
    public static boolean isBuildingAdmin() {
        try {
            BuildUser first = BuildUser.first(BuildUser.class);
            return first != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 停车场管理员
     */
    public static boolean isParkAdmin() {
        try {
            SpaceUser user = SpaceUser.first(SpaceUser.class);
            return user != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 2  * 获取版本号
     * 3  * @return 当前应用的版本号
     * 4
     */
    public static String getVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "获取版本失败";
        }
    }

    public static void deleteDb() {
        SugarRecord.deleteAll(UserInfo.class);
        SugarRecord.deleteAll(CompanyUserBean.class);
        SugarRecord.deleteAll(RuleInput.class);
        SugarRecord.deleteAll(SpaceUser.class);
        SugarRecord.deleteAll(BuildUser.class);
//        SugarRecord.deleteAll(NoticeBean.SysNoticeListBean.class);
    }
}
