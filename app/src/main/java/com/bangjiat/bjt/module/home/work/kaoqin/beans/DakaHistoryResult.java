package com.bangjiat.bjt.module.home.work.kaoqin.beans;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/8 0008
 */

public class DakaHistoryResult {

    /**
     * clockId : 0
     * ctime : 0
     * inAddress : string
     * inLatitude : string
     * inLongitude : string
     * inTime : 0
     * inType : 0
     * inWay : 0
     * lateRemark : string
     * leaveRemark : string
     * outAddress : string
     * outLatitude : string
     * outLongitude : string
     * outTime : 0
     * outType : 0
     * outWay : 0
     * remark : string
     * userId : string
     * userRealname : string
     */

    private String clockId;//打卡编号
    private long ctime;
    private String inAddress;//上班打卡的地址
    private String inLatitude;//上班打卡的纬度
    private String inLongitude;//上班打卡的经度
    private long inTime;//上班打卡时间
    private int inType;//上班打卡类型：1、正常；2、迟到；3、外勤打卡
    private int inWay;//上班打卡方式：1、WiFi、2、流量
    private String lateRemark;//迟到备注
    private String leaveRemark;//早退备注
    private String outAddress;//下班打卡的地址
    private String outLatitude;//下班打卡的纬度
    private String outLongitude;//下班打卡的经度
    private long outTime;//下班打卡的时间
    private int outType;//下班打卡的类型：1、正常;2、早退；3、外勤打卡
    private int outWay;//下班打卡方式：1、WiFi、2、流量
    private String remark;
    private String userId;
    private String userRealname;

    public String getClockId() {
        return clockId;
    }

    public void setClockId(String clockId) {
        this.clockId = clockId;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

    public String getInAddress() {
        return inAddress;
    }

    public void setInAddress(String inAddress) {
        this.inAddress = inAddress;
    }

    public String getInLatitude() {
        return inLatitude;
    }

    public void setInLatitude(String inLatitude) {
        this.inLatitude = inLatitude;
    }

    public String getInLongitude() {
        return inLongitude;
    }

    public void setInLongitude(String inLongitude) {
        this.inLongitude = inLongitude;
    }

    public long getInTime() {
        return inTime;
    }

    public void setInTime(long inTime) {
        this.inTime = inTime;
    }

    public int getInType() {
        return inType;
    }

    public void setInType(int inType) {
        this.inType = inType;
    }

    public int getInWay() {
        return inWay;
    }

    public void setInWay(int inWay) {
        this.inWay = inWay;
    }

    public String getLateRemark() {
        return lateRemark;
    }

    public void setLateRemark(String lateRemark) {
        this.lateRemark = lateRemark;
    }

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

    public String getOutLatitude() {
        return outLatitude;
    }

    public void setOutLatitude(String outLatitude) {
        this.outLatitude = outLatitude;
    }

    public String getOutLongitude() {
        return outLongitude;
    }

    public void setOutLongitude(String outLongitude) {
        this.outLongitude = outLongitude;
    }

    public long getOutTime() {
        return outTime;
    }

    public void setOutTime(long outTime) {
        this.outTime = outTime;
    }

    public int getOutType() {
        return outType;
    }

    public void setOutType(int outType) {
        this.outType = outType;
    }

    public int getOutWay() {
        return outWay;
    }

    public void setOutWay(int outWay) {
        this.outWay = outWay;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    @Override
    public String toString() {
        return "DakaHistoryResult{" +
                "clockId=" + clockId +
                ", ctime=" + ctime +
                ", inAddress='" + inAddress + '\'' +
                ", inLatitude='" + inLatitude + '\'' +
                ", inLongitude='" + inLongitude + '\'' +
                ", inTime=" + inTime +
                ", inType=" + inType +
                ", inWay=" + inWay +
                ", lateRemark='" + lateRemark + '\'' +
                ", leaveRemark='" + leaveRemark + '\'' +
                ", outAddress='" + outAddress + '\'' +
                ", outLatitude='" + outLatitude + '\'' +
                ", outLongitude='" + outLongitude + '\'' +
                ", outTime=" + outTime +
                ", outType=" + outType +
                ", outWay=" + outWay +
                ", remark='" + remark + '\'' +
                ", userId='" + userId + '\'' +
                ", userRealname='" + userRealname + '\'' +
                '}';
    }
}
