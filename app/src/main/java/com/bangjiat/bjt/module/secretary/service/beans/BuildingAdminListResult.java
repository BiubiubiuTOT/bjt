package com.bangjiat.bjt.module.secretary.service.beans;

import java.io.Serializable;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/9 0009
 */

public class BuildingAdminListResult implements Serializable{
    /**
     * buildId : 0
     * buildUserId : 0
     * realname : string
     * userId : string
     * username : string
     */

    private int buildId;
    private int buildUserId;
    private String realname;
    private String userId;
    private String username;

    public int getBuildId() {
        return buildId;
    }

    public void setBuildId(int buildId) {
        this.buildId = buildId;
    }

    public int getBuildUserId() {
        return buildUserId;
    }

    public void setBuildUserId(int buildUserId) {
        this.buildUserId = buildUserId;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
