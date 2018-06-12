package com.bangjiat.bjt.module.home.visitor.beans;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/6/8 0008
 */

public class DeleteHistory {
    private int type;//1表示访客记录，2表示邀请记录
    private int who;//1表示邀请者，2表示被邀请者
    private String[] list;//访客记录编号数组

    public DeleteHistory(int type, int who, String[] list) {
        this.type = type;
        this.who = who;
        this.list = list;
    }
}
