package com.bangjiat.bjt.module.secretary.service.beans;

import java.io.Serializable;
import java.util.List;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/9 0009
 */

public class ServiceApplyHistoryResult {
    /**
     * current : 0
     * pages : 0
     * records : [{"application":"string","approvalUser":"string","bApprovalId":0,"buildId":0,"companyId":0,"companyName":"string","content":"string","ctime":0,"remark":"string","sources":"string","status":0,"userId":"string","userRealname":"string","username":"string","utime":0}]
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

    public static class RecordsBean implements Serializable{
        /**
         * application : string  申请事项
         * approvalUser : string  审批人信息（Json字符串）
         * bApprovalId : 0  申请编号
         * buildId : 0  审批管理方
         * companyId : 0  审批申请方
         * companyName : string  公司名称
         * content : string 具体内容
         * ctime : 0
         * remark : string
         * sources : string  附件JSON字符串
         * status : 0  状态:1、待审批；2、已通过；3、拒绝
         * userId : string  审批发起人
         * userRealname : string 申请人姓名
         * username : string 申请人用户名
         * utime : 0 完成时间
         */

        private String application;
        private String approvalUser;
        private String bApprovalId;
        private String buildId;
        private String companyId;
        private String companyName;
        private String content;
        private String ctime;
        private String remark;
        private String sources;
        private int status;
        private String userId;
        private String userRealname;
        private String username;
        private String utime;

        public String getApplication() {
            return application;
        }

        public void setApplication(String application) {
            this.application = application;
        }

        public String getApprovalUser() {
            return approvalUser;
        }

        public void setApprovalUser(String approvalUser) {
            this.approvalUser = approvalUser;
        }

        public String getBApprovalId() {
            return bApprovalId;
        }

        public void setBApprovalId(String bApprovalId) {
            this.bApprovalId = bApprovalId;
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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getSources() {
            return sources;
        }

        public void setSources(String sources) {
            this.sources = sources;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserRealname() {
            return userRealname;
        }

        public void setUserRealname(String userRealname) {
            this.userRealname = userRealname;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUtime() {
            return utime;
        }

        public void setUtime(String utime) {
            this.utime = utime;
        }

        @Override
        public String toString() {
            return "RecordsBean{" +
                    "application='" + application + '\'' +
                    ", approvalUser='" + approvalUser + '\'' +
                    ", bApprovalId=" + bApprovalId +
                    ", buildId=" + buildId +
                    ", companyId=" + companyId +
                    ", companyName='" + companyName + '\'' +
                    ", content='" + content + '\'' +
                    ", ctime=" + ctime +
                    ", remark='" + remark + '\'' +
                    ", sources='" + sources + '\'' +
                    ", status=" + status +
                    ", userId='" + userId + '\'' +
                    ", userRealname='" + userRealname + '\'' +
                    ", username='" + username + '\'' +
                    ", utime=" + utime +
                    '}';
        }
    }
}
