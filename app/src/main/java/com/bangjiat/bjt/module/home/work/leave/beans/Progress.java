package com.bangjiat.bjt.module.home.work.leave.beans;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/15 0015
 */

public class Progress {////下一步审批人审批进度对象userId,userRealname,username
    private String userId;
    private String userRealname;
    private String username;

    public Progress(String userId, String username, String userRealname) {
        this.userId = userId;
        this.userRealname = userRealname;
        this.username = username;
    }

    @Override
    public String toString() {
        return "Progress{" +
                "userId='" + userId + '\'' +
                ", userRealname='" + userRealname + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
