package com.bangjiat.bjt.module.secretary.workers.beans;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/16 0016
 */

public class ApproverBean {
    private long ctime;
    private long lastTime;
    private String lastUserId;
    private String lastUserRealname;
    private int leaveId;
    private int progressId;
    private int status;//0待审批,1表示同意，2表示拒绝，3表示转批
    private String userId;
    private String userRealname;
    private String username;

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

    public int getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(int leaveId) {
        this.leaveId = leaveId;
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

    @Override
    public String toString() {
        return "ApproverBean{" +
                "ctime=" + ctime +
                ", lastTime=" + lastTime +
                ", lastUserId='" + lastUserId + '\'' +
                ", lastUserRealname='" + lastUserRealname + '\'' +
                ", leaveId=" + leaveId +
                ", progressId=" + progressId +
                ", status=" + status +
                ", userId='" + userId + '\'' +
                ", userRealname='" + userRealname + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
