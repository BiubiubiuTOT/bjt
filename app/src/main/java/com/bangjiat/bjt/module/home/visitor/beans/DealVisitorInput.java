package com.bangjiat.bjt.module.home.visitor.beans;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/23 0023
 */

public class DealVisitorInput {
    private int visitorId;
    private int type;//操作类型：type为2表示同意、为3表示拒绝

    public DealVisitorInput(int visitorId, int type) {
        this.visitorId = visitorId;
        this.type = type;
    }
}
