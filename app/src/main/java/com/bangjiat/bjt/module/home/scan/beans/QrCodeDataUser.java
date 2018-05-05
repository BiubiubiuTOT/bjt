package com.bangjiat.bjt.module.home.scan.beans;

import java.io.Serializable;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/5 0005
 */

public class QrCodeDataUser implements Serializable {
    private String userId;
    private String nickname;
    private String idNumber;
    private String avatar;
    private String username;
    private int qrCodeType;

    public QrCodeDataUser(String userId, String nickname, String username, String avatar, int qrCodeType) {
        this.userId = userId;
        this.nickname = nickname;
        this.username = username;
        this.avatar = avatar;
        this.qrCodeType = qrCodeType;
    }

    @Override
    public String toString() {
        return "QrCodeDataUser{" +
                "userId='" + userId + '\'' +
                ", nickname='" + nickname + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getQrCodeType() {
        return qrCodeType;
    }

    public void setQrCodeType(int qrCodeType) {
        this.qrCodeType = qrCodeType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
