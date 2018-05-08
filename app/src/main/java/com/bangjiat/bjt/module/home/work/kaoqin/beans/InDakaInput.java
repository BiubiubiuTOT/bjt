package com.bangjiat.bjt.module.home.work.kaoqin.beans;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/8 0008
 */

public class InDakaInput {
    private String inAddress;
    private String inLongitude;
    private String inLatitude;
    private String inType;//上班打卡类型：1、正常；2、迟到；3、外勤打卡
    private String inWay;//上班打卡方式：1、WiFi、2、流量

    public String getInAddress() {
        return inAddress;
    }

    public void setInAddress(String inAddress) {
        this.inAddress = inAddress;
    }

    public String getInLongitude() {
        return inLongitude;
    }

    public void setInLongitude(String inLongitude) {
        this.inLongitude = inLongitude;
    }

    public String getInLatitude() {
        return inLatitude;
    }

    public void setInLatitude(String inLatitude) {
        this.inLatitude = inLatitude;
    }

    public String getInType() {
        return inType;
    }

    public void setInType(String inType) {
        this.inType = inType;
    }

    public String getInWay() {
        return inWay;
    }

    public void setInWay(String inWay) {
        this.inWay = inWay;
    }
}
