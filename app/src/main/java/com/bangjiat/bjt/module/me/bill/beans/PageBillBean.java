package com.bangjiat.bjt.module.me.bill.beans;

import java.io.Serializable;
import java.util.List;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/4 0004
 */

public class PageBillBean {
    /**
     * current : 0
     * pages : 0
     * records : [{"billId":0,"ctime":0,"cutOffTime":0,"money":0,"payTime":0,"payType":0,"remark":"string","status":0,"type":0,"userId":"string"}]
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

    public static class RecordsBean implements Serializable{
        /**
         * billId : 0 账单编号
         * ctime : 0 创建时间
         * cutOffTime : 0 截止时间
         * money : 0 缴费金额
         * payTime : 0 缴纳时间
         * payType : 0 支付方式（0支付宝、1微信、3银行卡）
         * remark : string 备注
         * status : 0 状态（0逾期、1已缴纳、2未缴纳等）
         * type : 0 账单类型（1房租、2物业费）
         * userId : string
         */

        private int billId;
        private long ctime;
        private long cutOffTime;
        private String money;
        private long payTime;
        private int payWay;
        private String remark;
        private int status;
        private int type;
        private String userId;
        private String username;
        private String houseNumber;//门牌号
        private String companyName;//公司名称
        private int companyId;//公司编号
        private int buildId;//楼宇编号

        public int getBillId() {
            return billId;
        }

        public void setBillId(int billId) {
            this.billId = billId;
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

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public long getPayTime() {
            return payTime;
        }

        public void setPayTime(long payTime) {
            this.payTime = payTime;
        }

        public int getPayWay() {
            return payWay;
        }

        public void setPayWay(int payWay) {
            this.payWay = payWay;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
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

        public String getHouseNumber() {
            return houseNumber;
        }

        public void setHouseNumber(String houseNumber) {
            this.houseNumber = houseNumber;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public int getCompanyId() {
            return companyId;
        }

        public void setCompanyId(int companyId) {
            this.companyId = companyId;
        }

        public int getBuildId() {
            return buildId;
        }

        public void setBuildId(int buildId) {
            this.buildId = buildId;
        }

        @Override
        public String toString() {
            return "RecordsBean{" +
                    "billId=" + billId +
                    ", ctime=" + ctime +
                    ", cutOffTime=" + cutOffTime +
                    ", money='" + money + '\'' +
                    ", payTime=" + payTime +
                    ", payWay=" + payWay +
                    ", remark='" + remark + '\'' +
                    ", status=" + status +
                    ", type=" + type +
                    ", userId='" + userId + '\'' +
                    ", username='" + username + '\'' +
                    ", houseNumber='" + houseNumber + '\'' +
                    ", companyName='" + companyName + '\'' +
                    ", companyId=" + companyId +
                    ", buildId=" + buildId +
                    '}';
        }
    }
}
