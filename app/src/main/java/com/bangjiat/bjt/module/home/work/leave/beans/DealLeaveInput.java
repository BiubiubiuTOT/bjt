package com.bangjiat.bjt.module.home.work.leave.beans;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/15 0015
 */

public class DealLeaveInput {
    private int leaveId;//申请编号
    private int type;//操作类型1表示同意，2表示拒绝，3表示转批
    private Progress progress;

    public int getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(int leaveId) {
        this.leaveId = leaveId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Progress getProgress() {
        return progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }

    @Override
    public String toString() {
        return "DealLeaveInput{" +
                "leaveId=" + leaveId +
                ", type=" + type +
                ", progress=" + progress +
                '}';
    }
}
