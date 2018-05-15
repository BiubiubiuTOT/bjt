package com.bangjiat.bjt.module.home.work.leave.beans;

import java.io.Serializable;
import java.util.List;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/15 0015
 */

public class CompanyLeaveResult {
    /**
     * current : 0
     * pages : 0
     * records : [{"applyer":"string","approver":"string","beginTime":0,"copyer":"string","copyerId":"string","ctime":0,"endTime":0,"leaveId":0,"longTime":0,"reason":"string","remark":"string","resource":"string","status":0,"type":0,"userId":"string","username":"string"}]
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
         * applyer : string
         * approver : string
         * beginTime : 0
         * copyer : string
         * copyerId : string
         * ctime : 0
         * endTime : 0
         * leaveId : 0
         * longTime : 0
         * reason : string
         * remark : string
         * resource : string
         * status : 0
         * type : 0
         * userId : string
         * username : string
         */

        private String applyer;//申请人姓名
        private String approver;//审批人
        private long beginTime;
        private String copyer;//抄送人
        private String copyerId;//抄送人编号
        private long ctime;
        private long endTime;
        private int leaveId;
        private int longTime;//时长
        private String reason;//事由
        private String remark;
        private String resource;//图片json字符串
        private int status;//申请进度：1、待审批、2、通过、3、未通过
        private int type;//申请类型：1、事假；2、病假；3、出差；4、其他
        private String userId;//申请人编号
        private String username;//申请人账号

        public String getApplyer() {
            return applyer;
        }

        public void setApplyer(String applyer) {
            this.applyer = applyer;
        }

        public String getApprover() {
            return approver;
        }

        public void setApprover(String approver) {
            this.approver = approver;
        }

        public long getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(long beginTime) {
            this.beginTime = beginTime;
        }

        public String getCopyer() {
            return copyer;
        }

        public void setCopyer(String copyer) {
            this.copyer = copyer;
        }

        public String getCopyerId() {
            return copyerId;
        }

        public void setCopyerId(String copyerId) {
            this.copyerId = copyerId;
        }

        public long getCtime() {
            return ctime;
        }

        public void setCtime(long ctime) {
            this.ctime = ctime;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public int getLeaveId() {
            return leaveId;
        }

        public void setLeaveId(int leaveId) {
            this.leaveId = leaveId;
        }

        public int getLongTime() {
            return longTime;
        }

        public void setLongTime(int longTime) {
            this.longTime = longTime;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getResource() {
            return resource;
        }

        public void setResource(String resource) {
            this.resource = resource;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
