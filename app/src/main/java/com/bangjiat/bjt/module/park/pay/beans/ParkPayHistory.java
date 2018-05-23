package com.bangjiat.bjt.module.park.pay.beans;

import java.io.Serializable;
import java.util.List;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/23 0023
 */

public class ParkPayHistory {

    /**
     * current : 0
     * pages : 0
     * records : [{"beginTime":0,"carId":0,"ctime":0,"cutOffTime":0,"endTime":0,"fee":0,"number":0,"payWay":0,"paymentId":0,"plateNumber":"string","realname":"string","remark":"string","spaceId":0,"spaceName":"string","status":0,"totalFee":0,"type":0,"userId":"string","username":"string"}]
     * size : 0
     * total : 0
     */

    private int current;
    private int pages;
    private int size;
    private int total;
    private List<RecordsBean> records;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    public static class RecordsBean implements Serializable {
        /**
         * beginTime : 0
         * carId : 0
         * ctime : 0
         * cutOffTime : 0
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
         * type : 0
         * userId : string
         * username : string
         */

        private long beginTime;//生效日期
        private int carId;
        private long ctime;
        private long cutOffTime;//支付截止时间
        private long endTime;//截止日期
        private String fee;//每月/每年 单价费用
        private int number;//缴纳月数/年数
        private int payWay;//缴费方式(1是支付宝，2是微信，3是银行卡)
        private int paymentId;//缴费编号
        private String plateNumber;//车牌号
        private String realname;//用户姓名
        private String remark;
        private int spaceId;//停车场编号
        private String spaceName;
        private int status;//状态：1、待缴费；2、缴费成功；3；缴费失败
        private String totalFee;//合计金额
        private int type;//缴费类型：1、按月缴费；2、按年缴费
        private String userId;
        private String username;
        private String resource;

        public String getResource() {
            return resource;
        }

        public void setResource(String resource) {
            this.resource = resource;
        }

        public long getBeginTime() {
            return beginTime;
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

        public long getCutOffTime() {
            return cutOffTime;
        }

        public void setCutOffTime(long cutOffTime) {
            this.cutOffTime = cutOffTime;
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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
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

        @Override
        public String toString() {
            return "RecordsBean{" +
                    "beginTime=" + beginTime +
                    ", carId=" + carId +
                    ", ctime=" + ctime +
                    ", cutOffTime=" + cutOffTime +
                    ", endTime=" + endTime +
                    ", fee='" + fee + '\'' +
                    ", number=" + number +
                    ", payWay=" + payWay +
                    ", paymentId=" + paymentId +
                    ", plateNumber='" + plateNumber + '\'' +
                    ", realname='" + realname + '\'' +
                    ", remark='" + remark + '\'' +
                    ", spaceId=" + spaceId +
                    ", spaceName='" + spaceName + '\'' +
                    ", status=" + status +
                    ", totalFee='" + totalFee + '\'' +
                    ", type=" + type +
                    ", userId='" + userId + '\'' +
                    ", username='" + username + '\'' +
                    ", resource='" + resource + '\'' +
                    '}';
        }
    }
}
