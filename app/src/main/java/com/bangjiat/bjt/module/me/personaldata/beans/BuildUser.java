package com.bangjiat.bjt.module.me.personaldata.beans;

import com.orm.SugarRecord;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/19 0019
 */

public class BuildUser extends SugarRecord{
    private int buildUserId;
    private int buildId;
    private String userId;
    private String username;
    private String realname;

    @Override
    public String toString() {
        return "BuildUser{" +
                "buildUserId=" + buildUserId +
                ", buildId=" + buildId +
                ", userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", realname='" + realname + '\'' +
                '}';
    }

    public BuildUser() {
    }

    public int getBuildUserId() {
        return buildUserId;
    }

    public int getBuildId() {
        return buildId;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getRealname() {
        return realname;
    }
}
