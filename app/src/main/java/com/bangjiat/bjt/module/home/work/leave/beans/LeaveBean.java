package com.bangjiat.bjt.module.home.work.leave.beans;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/2 0002
 */

public class LeaveBean {
    private String reason;//事由
    private long beginTime;//开始时间
    private long endTime;//结束时间
    private Progress progress;
    private int type;//申请类型：1、事假；2、病假；3、出差；4、其他

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public Progress getProgress() {
        return progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "LeaveBean{" +
                "reason='" + reason + '\'' +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", progress=" + progress +
                ", type=" + type +
                '}';
    }
}
