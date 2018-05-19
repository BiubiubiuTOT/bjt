package com.bangjiat.bjt.module.secretary.service.beans;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/19 0019
 */

public class ApprovalDoorInput {
    private String guardMainId;
    private String opinion;//拒绝时必填的审批意见
    private long endTime;
    private int type;//操作类型：1、拒绝；2、同意

    public ApprovalDoorInput(String guardMainId, String opinion, int type) {//拒绝
        this.guardMainId = guardMainId;
        this.opinion = opinion;
        this.type = type;
    }

    public ApprovalDoorInput(String guardMainId, long endTime, int type) {//同意
        this.guardMainId = guardMainId;
        this.opinion = opinion;
        this.endTime = endTime;
        this.type = type;
    }

    public String getGuardMainId() {
        return guardMainId;
    }

    public void setGuardMainId(String guardMainId) {
        this.guardMainId = guardMainId;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

