package com.bangjiat.bjt.module.secretary.door.beans;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/9 0009
 */

public class IsIntoBuildingResult {
    /**
     * applyTime : 0
     * applyUserId : string
     * applyer : string
     * approvalTime : 0
     * approvalUserId : string
     * buildId : 0
     * buildName : string
     * code : string
     * companyId : 0
     * companyName : string
     * ctime : 0
     * opinion : string
     * remark : string
     * status : 0
     */

    private String applyTime;
    private String applyUserId;
    private String applyer;
    private String approvalTime;
    private String approvalUserId;
    private String buildId;
    private String buildName;
    private String code;
    private String companyId;
    private String companyName;
    private String ctime;
    private String opinion;
    private String remark;
    private int status;//状态：1、待审批；2、已通过、3未通过

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(String applyUserId) {
        this.applyUserId = applyUserId;
    }

    public String getApplyer() {
        return applyer;
    }

    public void setApplyer(String applyer) {
        this.applyer = applyer;
    }

    public String getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(String approvalTime) {
        this.approvalTime = approvalTime;
    }

    public String getApprovalUserId() {
        return approvalUserId;
    }

    public void setApprovalUserId(String approvalUserId) {
        this.approvalUserId = approvalUserId;
    }

    public String getBuildId() {
        return buildId;
    }

    public void setBuildId(String buildId) {
        this.buildId = buildId;
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
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

    @Override
    public String toString() {
        return "IsStringoBuildingResult{" +
                "applyTime=" + applyTime +
                ", applyUserId='" + applyUserId + '\'' +
                ", applyer='" + applyer + '\'' +
                ", approvalTime=" + approvalTime +
                ", approvalUserId='" + approvalUserId + '\'' +
                ", buildId=" + buildId +
                ", buildName='" + buildName + '\'' +
                ", code='" + code + '\'' +
                ", companyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                ", ctime=" + ctime +
                ", opinion='" + opinion + '\'' +
                ", remark='" + remark + '\'' +
                ", status=" + status +
                '}';
    }
}
