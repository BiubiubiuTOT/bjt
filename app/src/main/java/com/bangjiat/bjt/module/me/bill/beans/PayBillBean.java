package com.bangjiat.bjt.module.me.bill.beans;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/22 0022
 */

public class PayBillBean {
    private int billId;
    private String money;
    private int payWay;

    public PayBillBean(int billId, String money, int payWay) {
        this.billId = billId;
        this.money = money;
        this.payWay = payWay;
    }

    @Override
    public String toString() {
        return "PayBillBean{" +
                "billId=" + billId +
                ", money='" + money + '\'' +
                ", payWay=" + payWay +
                '}';
    }
}
