package com.bangjiat.bjt.module.me.personaldata.beans;

import java.util.List;

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
    private String park;
    private CompanyUserBean companyUser;
    private List<BuildUser> buildUser;
    private List<SpaceUser> spaceUser;

    public String getPark() {
        return park;
    }

    public void setPark(String park) {
        this.park = park;
    }

    public List<BuildUser> getBuildUser() {
        return buildUser;
    }

    public void setBuildUser(List<BuildUser> buildUser) {
        this.buildUser = buildUser;
    }

    public List<SpaceUser> getSpaceUser() {
        return spaceUser;
    }

    public void setSpaceUser(List<SpaceUser> spaceUser) {
        this.spaceUser = spaceUser;
    }

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

    @Override
    public String toString() {
        return "UserInfoBean{" +
                "userInfo=" + userInfo +
                ", count=" + count +
                ", companyUser=" + companyUser +
                ", buildUser=" + buildUser +
                ", spaceUser=" + spaceUser +
                '}';
    }
}
