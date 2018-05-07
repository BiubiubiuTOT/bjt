package com.bangjiat.bjt.module.me.personaldata.beans;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/4/19 0019
 */

public class UserInfoBean {

    /**
     * userInfo : {"userId":"115f6598290846baacd0c827b0070095","username":"17685302679","password":"D02C545E8396A44DFF94144B208355C0","salt":"57397963ffbc40859852f9d9f13ee40d","phone":"17685302679","locked":0,"ctime":1523674617121}
     * count : 1
     * companyUser : {"companyUserId":13,"companyId":12,"userId":"115f6598290846baacd0c827b0070095","type":2,"companyName":"123","phone":"17685302679"}
     */

    private UserInfo userInfo;
    private int count;
    private CompanyUserBean companyUser;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public CompanyUserBean getCompanyUser() {
        return companyUser;
    }

    public void setCompanyUser(CompanyUserBean companyUser) {
        this.companyUser = companyUser;
    }

}
