package com.bangjiat.bjt.module.park.apply.beans;

import java.io.Serializable;
import java.util.List;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/15 0015
 */

public class ParkApplyHistoryResult implements Serializable {
    /**
     * current : 0
     * pages : 0
     * records : [{"applyId":0,"applyTime":0,"applyUsername":"string","applyer":"string","applyerId":"string","approvalTime":0,"approver":"string","approverId":"string","buildId":0,"buildName":"string","company":"string","companyId":0,"ctime":0,"detail":"string","opinion":"string","remark":"string","spaceId":0,"spaceName":"string","status":0}]
     * size : 0
     * total : 0
     */

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

    public static class RecordsBean implements Serializable {
        /**
         * applyId : 0
         * applyTime : 0
         * applyUsername : string
         * applyer : string
         * applyerId : string
         * approvalTime : 0
         * approver : string
         * approverId : string
         * buildId : 0
         * buildName : string
         * company : string
         * companyId : 0
         * ctime : 0
         * detail : string
         * opinion : string
         * remark : string
         * spaceId : 0
         * spaceName : string
         * status : 0
         */

        private int applyId;//申请ID
        private long applyTime;//申请时间
        private String applyUsername;//申请人账号
        private String applyer;//申请人
        private String applyerId;//申请人编号
        private long approvalTime;//审批时间
        private String approver;//审批人
        private String approverId;//审批人编号
        private int buildId;//楼宇编号
        private String buildName;//楼宇名称
        private String company;//公司名称
        private int companyId;//公司编号
        private long ctime;
        private String detail;//停车申请明细
        private String opinion;//审批意见
        private String remark;
        private int spaceId;//停车场编号
        private String spaceName;//停车场名称
        private int status;//状态:1待审核，2已通过，3未通过

        public int getApplyId() {
            return applyId;
        }

        public void setApplyId(int applyId) {
            this.applyId = applyId;
        }

        public long getApplyTime() {
            return applyTime;
        }

        public void setApplyTime(long applyTime) {
            this.applyTime = applyTime;
        }

        public String getApplyUsername() {
            return applyUsername;
        }

        public void setApplyUsername(String applyUsername) {
            this.applyUsername = applyUsername;
        }

        public String getApplyer() {
            return applyer;
        }

        public void setApplyer(String applyer) {
            this.applyer = applyer;
        }

        public String getApplyerId() {
            return applyerId;
        }

        public void setApplyerId(String applyerId) {
            this.applyerId = applyerId;
        }

        public long getApprovalTime() {
            return approvalTime;
        }

        public void setApprovalTime(long approvalTime) {
            this.approvalTime = approvalTime;
        }

        public String getApprover() {
            return approver;
        }

        public void setApprover(String approver) {
            this.approver = approver;
        }

        public String getApproverId() {
            return approverId;
        }

        public void setApproverId(String approverId) {
            this.approverId = approverId;
        }

        public int getBuildId() {
            return buildId;
        }

        public void setBuildId(int buildId) {
            this.buildId = buildId;
        }

        public String getBuildName() {
            return buildName;
        }

        public void setBuildName(String buildName) {
            this.buildName = buildName;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public int getCompanyId() {
            return companyId;
        }

        public void setCompanyId(int companyId) {
            this.companyId = companyId;
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

        public int getSpaceId() {
            return spaceId;
        }

        public void setSpaceId(int spaceId) {
            this.spaceId = spaceId;
        }

        public String getSpaceName() {
            return spaceName;
        }

        public void setSpaceName(String spaceName) {
            this.spaceName = spaceName;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
