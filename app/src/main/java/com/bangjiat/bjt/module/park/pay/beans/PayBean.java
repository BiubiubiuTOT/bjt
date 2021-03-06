package com.bangjiat.bjt.module.park.pay.beans;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/12 0012
 */

public class PayBean {
    /**
     * beginTime : 0
     * carId : 0
     * ctime : 0
     * endTime : 0
     * fee : 0
     * number : 0
     * payWay : 0
     * paymentId : 0
     * plateNumber : string
     * realname : string
     * remark : string
     * spaceId : 0
     * spaceName : string
     * status : 0
     * totalFee : 0
     * userId : string
     * username : string
     */

    private long beginTime;
    private int carId;
    private long ctime;
    private long endTime;
    private String fee;//每月费用
    private int number;
    private int payWay;//(1表示支付宝，2表示微信)
    private int paymentId;
    private String plateNumber;
    private String realname;
    private String remark;
    private int spaceId;
    private String spaceName;
    private int status;
    private String totalFee;
    private String userId;
    private String username;
    private int type;//缴费类型：1、按月缴费；2、按年缴费

    public long getBeginTime() {
        return beginTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPayWay() {
        return payWay;
    }

    public void setPayWay(int payWay) {
        this.payWay = payWay;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
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
