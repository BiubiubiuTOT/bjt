package com.bangjiat.bjt.module.me.personaldata.beans;

import com.orm.SugarRecord;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/19 0019
 */

public class SpaceUser extends SugarRecord{
    private int spaceId;
    private String spaceName;
    private int spaceUserId;
    private String userId;
    private String username;
    private String realname;

    @Override
    public String toString() {
        return "SpaceUser{" +
                "spaceId=" + spaceId +
                ", spaceName='" + spaceName + '\'' +
                ", spaceUserId=" + spaceUserId +
                ", userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", realname='" + realname + '\'' +
                '}';
    }

    public SpaceUser() {
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public int getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(int spaceId) {
        this.spaceId = spaceId;
    }

    public String getSpaceName() {
        return spaceName;
    }

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }

    public int getSpaceUserId() {
        return spaceUserId;
    }

    public void setSpaceUserId(int spaceUserId) {
        this.spaceUserId = spaceUserId;
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
