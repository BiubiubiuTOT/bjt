package com.bangjiat.bjt.module.me.bill.beans;

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

    public static class RecordsBean {
        /**
         * billId : 0 账单编号
         * ctime : 0 创建时间
         * cutOffTime : 0 截止时间
         * money : 0 缴费金额
         * payTime : 0 缴纳时间
         * payType : 0 支付方式（0支付宝、1微信、3银行卡）
         * remark : string 备注
         * status : 0 状态（0逾期、1已缴纳、2未缴纳等）
         * type : 0 账单类型（1租金、2停车、3物业费等）
         * userId : string
         */

        private int billId;
        private int ctime;
        private int cutOffTime;
        private int money;
        private int payTime;
        private int payType;
        private String remark;
        private int status;
        private int type;
        private String userId;

        public int getBillId() {
            return billId;
        }

        public void setBillId(int billId) {
            this.billId = billId;
        }

        public int getCtime() {
            return ctime;
        }

        public void setCtime(int ctime) {
            this.ctime = ctime;
        }

        public int getCutOffTime() {
            return cutOffTime;
        }

        public void setCutOffTime(int cutOffTime) {
            this.cutOffTime = cutOffTime;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public int getPayTime() {
            return payTime;
        }

        public void setPayTime(int payTime) {
            this.payTime = payTime;
        }

        public int getPayType() {
            return payType;
        }

        public void setPayType(int payType) {
            this.payType = payType;
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
    }
}
