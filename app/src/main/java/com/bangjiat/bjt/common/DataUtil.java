package com.bangjiat.bjt.common;

import android.content.Context;

import com.bangjiat.bjt.module.main.account.beans.Account;

/**
 * Created by Administrator on 2018/4/14 0014.
 */

public class DataUtil {

    public static String getIcon(Context context) {
        SPUtil util = new SPUtil(context, SPUtil.AccountSettings.ACCOUNT_SETTINGS);
        String icon = util.getString(SPUtil.AccountSettings.ICON, "");
        return icon;
    }

    public static void setIcon(Context context, String s) {
        SPUtil util = new SPUtil(context, SPUtil.AccountSettings.ACCOUNT_SETTINGS);
        util.putString(SPUtil.AccountSettings.ICON, s);
    }

    /**
     * 设置token
     *
     * @param token
     */
    public static void setToken(Context context, String token) {
        SPUtil spUtil = new SPUtil(context, SPUtil.AccountSettings.ACCOUNT_SETTINGS);
        spUtil.putString(SPUtil.AccountSettings.TOKEN, token);
    }

    /**
     * 获取token
     *
     * @return
     */
    public static String getToken(Context context) {
        SPUtil spUtil = new SPUtil(context, SPUtil.AccountSettings.ACCOUNT_SETTINGS);
        String token = spUtil.getString(SPUtil.AccountSettings.TOKEN, "");

        return token;
    }

    public static Account getAccount(Context context) {
        SPUtil spUtil = new SPUtil(context, SPUtil.AccountSettings.ACCOUNT_SETTINGS);
        String phone = spUtil.getString(SPUtil.AccountSettings.USERNAME, "");
        String password = spUtil.getString(SPUtil.AccountSettings.PASSWORD, "");

        return new Account(phone, password);
    }

    public static void setAccount(Context context, String phone, String password) {
        SPUtil spUtil = new SPUtil(context, SPUtil.AccountSettings.ACCOUNT_SETTINGS);
        spUtil.putString(SPUtil.AccountSettings.USERNAME, phone);
        spUtil.putString(SPUtil.AccountSettings.PASSWORD, password);
    }

    public static boolean isLogin(Context context) {
        SPUtil spUtil = new SPUtil(context, SPUtil.AccountSettings.IS_LOGIN);
        return spUtil.getBoolean(SPUtil.AccountSettings.IS_LOGIN, false);
    }

    public static void setLogin(Context context, boolean isLogin) {
        SPUtil spUtil = new SPUtil(context, SPUtil.AccountSettings.IS_LOGIN);
        spUtil.putBoolean(SPUtil.AccountSettings.IS_LOGIN, isLogin);
    }

    public static void setReceiveNotification(Context context, boolean isReceive) {
        SPUtil spUtil = new SPUtil(context, SPUtil.OtherSetting.OTHER_SETTING);
        spUtil.putBoolean(SPUtil.OtherSetting.IS_RECEIVE_NOTIFICATION, isReceive);
    }

    public static boolean isReceiveNotification(Context context) {
        SPUtil spUtil = new SPUtil(context, SPUtil.OtherSetting.OTHER_SETTING);
        return spUtil.getBoolean(SPUtil.OtherSetting.IS_RECEIVE_NOTIFICATION, true);
    }

    public static void setPhone(Context context, String phone) {
        SPUtil spUtil = new SPUtil(context, SPUtil.AccountSettings.ACCOUNT_SETTINGS);
        spUtil.putString(SPUtil.AccountSettings.PHONE, phone);
    }

    public static String getPhone(Context context) {
        SPUtil spUtil = new SPUtil(context, SPUtil.AccountSettings.ACCOUNT_SETTINGS);
        return spUtil.getString(SPUtil.AccountSettings.PHONE, "");
    }

    public static String getUserId(Context context) {
        SPUtil spUtil = new SPUtil(context, SPUtil.AccountSettings.ACCOUNT_SETTINGS);
        return spUtil.getString(SPUtil.AccountSettings.USER_ID, "");
    }

    public static void setUserId(Context context, String userId) {
        SPUtil spUtil = new SPUtil(context, SPUtil.AccountSettings.ACCOUNT_SETTINGS);
        spUtil.putString(SPUtil.AccountSettings.USER_ID, userId);
    }

    public static void setIntoBuilding(Context context) {
        SPUtil spUtil = new SPUtil(context, SPUtil.OtherSetting.OTHER_SETTING);
        spUtil.putBoolean(SPUtil.OtherSetting.IS_INTO_BUILDING, true);
    }

    public static boolean isIntoBuilding(Context context) {
        SPUtil spUtil = new SPUtil(context, SPUtil.OtherSetting.OTHER_SETTING);
        return spUtil.getBoolean(SPUtil.OtherSetting.IS_INTO_BUILDING, false);
    }

    public static void clearOtherSetting(Context context) {
        SPUtil spUtil = new SPUtil(context, SPUtil.OtherSetting.OTHER_SETTING);
        spUtil.clear();
    }
}
