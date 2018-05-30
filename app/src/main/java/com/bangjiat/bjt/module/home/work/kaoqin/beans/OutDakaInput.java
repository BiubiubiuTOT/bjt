package com.bangjiat.bjt.module.home.work.kaoqin.beans;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/8 0008
 */

public class OutDakaInput {
    private String outAddress;
    private String outLongitude;
    private String outLatitude;
    private String outType;
    private String outWay;
    private String leaveRemark;
    private String clockId;

    public String getLeaveRemark() {
        return leaveRemark;
    }

    public void setLeaveRemark(String leaveRemark) {
        this.leaveRemark = leaveRemark;
    }

    public String getOutAddress() {
        return outAddress;
    }

    public void setOutAddress(String outAddress) {
        this.outAddress = outAddress;
    }

    public String getOutLongitude() {
        return outLongitude;
    }

    public void setOutLongitude(String outLongitude) {
        this.outLongitude = outLongitude;
    }

    public String getOutLatitude() {
        return outLatitude;
    }

    public void setOutLatitude(String outLatitude) {
        this.outLatitude = outLatitude;
    }

    public String getOutType() {
        return outType;
    }

    public void setOutType(String outType) {
        this.outType = outType;
    }

    public String getOutWay() {
        return outWay;
    }

    public void setOutWay(String outWay) {
        this.outWay = outWay;
    }

    public String getClockId() {
        return clockId;
    }

    public void setClockId(String clockId) {
        this.clockId = clockId;
    }

    @Override
    public String toString() {
        return "OutDakaInput{" +
                "outAddress='" + outAddress + '\'' +
                ", outLongitude='" + outLongitude + '\'' +
                ", outLatitude='" + outLatitude + '\'' +
                ", outType='" + outType + '\'' +
                ", outWay='" + outWay + '\'' +
                ", leaveRemark='" + leaveRemark + '\'' +
                ", clockId=" + clockId +
                '}';
    }
}
