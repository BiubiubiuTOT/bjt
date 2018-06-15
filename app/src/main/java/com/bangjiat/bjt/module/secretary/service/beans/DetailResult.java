package com.bangjiat.bjt.module.secretary.service.beans;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/6/14 0014
 */

public class DetailResult {
    private int bApprovalId;
    private long ctime;
    private long lastTime;
    private String lastUserId;
    private String lastUserRealname;
    private int progressId;
    private int status;
    private String userId;
    private String userRealname;
    private String username;
    private long utime;

    public int getbApprovalId() {
        return bApprovalId;
    }

    public void setbApprovalId(int bApprovalId) {
        this.bApprovalId = bApprovalId;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

    public long getLastTime() {
        return lastTime;
    }

    public void setLastTime(long lastTime) {
        this.lastTime = lastTime;
    }

    public String getLastUserId() {
        return lastUserId;
    }

    public void setLastUserId(String lastUserId) {
        this.lastUserId = lastUserId;
    }

    public String getLastUserRealname() {
        return lastUserRealname;
    }

    public void setLastUserRealname(String lastUserRealname) {
        this.lastUserRealname = lastUserRealname;
    }

    public int getProgressId() {
        return progressId;
    }

    public void setProgressId(int progressId) {
        this.progressId = progressId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserRealname() {
        return userRealname;
    }

    public void setUserRealname(String userRealname) {
        this.userRealname = userRealname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getUtime() {
        return utime;
    }

    public void setUtime(long utime) {
        this.utime = utime;
    }
}
