package com.bangjiat.bjt.module.secretary.door.beans;

import com.bangjiat.bjt.module.home.work.leave.beans.Progress;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/19 0019
 */

public class ApprovalServiceInput {
    private String bApprovalId;
    private String remark;
    private int type;//操作类型1表示同意，2表示拒绝，3表示转批
    private Progress progress;

    public Progress getProgress() {
        return progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }

    public ApprovalServiceInput(String bApprovalId, String remark, int type) {//拒绝
        this.bApprovalId = bApprovalId;
        this.remark = remark;
        this.type = type;
    }

    public ApprovalServiceInput(String bApprovalId, int type) {
        this.bApprovalId = bApprovalId;
        this.type = type;
    }

    public String getbApprovalId() {
        return bApprovalId;
    }

    public void setbApprovalId(String bApprovalId) {
        this.bApprovalId = bApprovalId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
