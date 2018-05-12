package com.bangjiat.bjt.module.park.pay.beans;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/12 0012
 */

public class PayInput {
    private String paymentId;
    private String money;

    public PayInput(String paymentId, String money) {
        this.paymentId = paymentId;
        this.money = money;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
