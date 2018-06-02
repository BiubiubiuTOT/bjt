package com.bangjiat.bjt.module.secretary.door.beans;

import java.io.Serializable;
import java.util.List;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/4/20 0020
 */

public class ApplyHistoryBean {
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

    public static class RecordsBean implements Serializable{
        /**
         * applyTime : 0  申请时间
         * applyUserId : string  申请人编号
         * applyUserRealname : string  申请人姓名
         * applyUsername : string 申请人账号
         * approvalTime : 0 审批时间
         * approvalUserId : string  审批人编号
         * approvalUserRealname : string  审批人姓名
         * approvalUsername : string  审批人账号
         * buildId : 0  楼宇编号
         * companyId : 0 公司编号
         * companyName : string  公司名称
         * ctime : 0
         * detail : string 申请明细Json字符串
         * guardMainId : 0 门禁申请主表编号
         * opinion : string 拒绝时可能要填写的处理意见
         * remark : string  门禁申请主表编号
         * type : 0 类型:1、待审批，2、已通过，3、未通过
         */

        private long applyTime;
        private String applyUserId;
        private String applyUserRealname;
        private String applyUsername;
        private long approvalTime;
        private String approvalUserId;
        private String approvalUserRealname;
        private String approvalUsername;
        private String buildId;
        private String companyId;
        private String companyName;
        private long ctime;
        private long endTime;
        private String detail;
        private String guardMainId;
        private String opinion;
        private String remark;
        private int type;

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public long getApplyTime() {
            return applyTime;
        }

        public void setApplyTime(long applyTime) {
            this.applyTime = applyTime;
        }

        public String getApplyUserId() {
            return applyUserId;
        }

        public void setApplyUserId(String applyUserId) {
            this.applyUserId = applyUserId;
        }

        public String getApplyUserRealname() {
            return applyUserRealname;
        }

        public void setApplyUserRealname(String applyUserRealname) {
            this.applyUserRealname = applyUserRealname;
        }

        public String getApplyUsername() {
            return applyUsername;
        }

        public void setApplyUsername(String applyUsername) {
            this.applyUsername = applyUsername;
        }

        public long getApprovalTime() {
            return approvalTime;
        }

        public void setApprovalTime(long approvalTime) {
            this.approvalTime = approvalTime;
        }

        public String getApprovalUserId() {
            return approvalUserId;
        }

        public void setApprovalUserId(String approvalUserId) {
            this.approvalUserId = approvalUserId;
        }

        public String getApprovalUserRealname() {
            return approvalUserRealname;
        }

        public void setApprovalUserRealname(String approvalUserRealname) {
            this.approvalUserRealname = approvalUserRealname;
        }

        public String getApprovalUsername() {
            return approvalUsername;
        }

        public void setApprovalUsername(String approvalUsername) {
            this.approvalUsername = approvalUsername;
        }

        public String getBuildId() {
            return buildId;
        }

        public void setBuildId(String buildId) {
            this.buildId = buildId;
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

        public long getCtime() {
            return ctime;
        }

        public void setCtime(long ctime) {
            this.ctime = ctime;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
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
}
