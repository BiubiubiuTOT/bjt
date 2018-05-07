package com.bangjiat.bjt.common;

import com.bangjiat.bjt.module.me.personaldata.beans.CompanyUserBean;

/**
 * Created by Administrator on 2018/4/14 0014.
 * BANGJIATUANDASHUJU
 */

public class Constants {
    public static final String BASE_IP = "http://192.168.0.118:8888/app/";
    public static final String TOKEN_NAME = "j4sc-bjt-token";

    public static boolean isIntoCompany() {
        CompanyUserBean companyUserBean = CompanyUserBean.first(CompanyUserBean.class);
        return companyUserBean != null;
    }
}
