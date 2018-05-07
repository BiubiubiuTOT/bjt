package com.bangjiat.bjt.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.bangjiat.bjt.module.me.personaldata.beans.CompanyUserBean;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

/**
 * Created by Administrator on 2018/4/14 0014.
 * BANGJIATUANDASHUJU
 */

public class Constants {
    public static final String BASE_IP = "http://192.168.0.118:8888/app/";
    public static final String TOKEN_NAME = "j4sc-bjt-token";

    public static boolean isIntoCompany() {
        CompanyUserBean companyUserBean = CompanyUserBean.first(CompanyUserBean.class);
        return companyUserBean!=null;
    }

    public static String
    sHA1(Context context) {
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
}
