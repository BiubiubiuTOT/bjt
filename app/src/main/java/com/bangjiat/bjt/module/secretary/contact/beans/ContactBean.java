package com.bangjiat.bjt.module.secretary.contact.beans;

import java.io.Serializable;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/4 0004
 */

public class ContactBean implements Serializable {
    /**
     * addressListId : 0
     * avatar : string
     * ctime : 0
     * remark : string
     * slaveNickname : string
     * slaveUserId : string
     * slaveUsername : string
     * status : 0
     * userId : string
     */
    private String letter;//首字母索引
    private String addressListId;
    private String avatar;
    private String ctime;
    private String remark;
    private String slaveNickname;
    private String slaveUserId;
    private String slaveUsername;
    private String status;
    private String userId;

    public String getAddressListId() {
        return addressListId;
    }

    public void setAddressListId(String addressListId) {
        this.addressListId = addressListId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSlaveNickname() {
        return slaveNickname;
    }

    public void setSlaveNickname(String slaveNickname) {
        this.slaveNickname = slaveNickname;
    }

    public String getSlaveUserId() {
        return slaveUserId;
    }

    public void setSlaveUserId(String slaveUserId) {
        this.slaveUserId = slaveUserId;
    }

    public String getSlaveUsername() {
        return slaveUsername;
    }

    public void setSlaveUsername(String slaveUsername) {
        this.slaveUsername = slaveUsername;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }
}
