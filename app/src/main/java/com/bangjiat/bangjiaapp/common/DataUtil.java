package com.bangjiat.bangjiaapp.common;

import android.content.Context;

import com.bangjiat.bangjiaapp.module.main.account.beans.Account;
import com.orhanobut.logger.Logger;

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
        return spUtil.getBoolean(SPUtil.AccountSettings.IS_LOGIN, true);
    }

    public static void setLogin(Context context, boolean isLogin) {
        SPUtil spUtil = new SPUtil(context, SPUtil.AccountSettings.IS_LOGIN);
        spUtil.putBoolean(SPUtil.AccountSettings.IS_LOGIN, isLogin);
    }
}
