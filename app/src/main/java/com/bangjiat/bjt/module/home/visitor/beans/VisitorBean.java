package com.bangjiat.bjt.module.home.visitor.beans;

import java.util.List;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/4/16 0016
 */

public class VisitorBean {
    /**
     * current : 0
     * pages : 0
     * records : [{"buildId":0,"ctime":0,"idcardBack":"string","idcardFront":"string","interviewName":"string","interviewPhone":"string","interviewUserId":"string","remark":"string","status":0,"type":0,"useCount":0,"visitMatter":"string","visitTime":0,"visitorFront":"string","visitorId":0,"visitorName":"string","visitorPhone":"string"}]
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

    public static class RecordsBean {
        /**
         * buildId : 0
         * ctime : 0
         * idcardBack : string
         * idcardFront : string
         * interviewName : string
         * interviewPhone : string
         * interviewUserId : string
         * remark : string
         * status : 0
         * type : 0
         * useCount : 0
         * visitMatter : string
         * visitTime : 0
         * visitorFront : string
         * visitorId : 0
         * visitorName : string
         * visitorPhone : string
         */

        private int buildId;//楼宇编号
        private long ctime;
        private String idcardBack;//身份证反面照path
        private String idcardFront;//身份证正面照path
        private String interviewName;//被访人姓名
        private String interviewPhone;//被访人手机号
        private String interviewUserId;//被访人编号
        private String remark;
        private int status;//状态:1、待处理；2、已通过；3、未通过
        private int type;//类型：1、访客记录；2、邀请记录
        private int useCount;//使用次数
        private String visitMatter;//访问事宜
        private int visitTime;//访问时间
        private String visitorFront;//访客正面照
        private int visitorId;//访客编号
        private String visitorName;//访客姓名
        private String visitorPhone;//访客手机号

        public int getBuildId() {
            return buildId;
        }

        public void setBuildId(int buildId) {
            this.buildId = buildId;
        }

        public long getCtime() {
            return ctime;
        }

        public void setCtime(long ctime) {
            this.ctime = ctime;
        }

        public String getIdcardBack() {
            return idcardBack;
        }

        public void setIdcardBack(String idcardBack) {
            this.idcardBack = idcardBack;
        }

        public String getIdcardFront() {
            return idcardFront;
        }

        public void setIdcardFront(String idcardFront) {
            this.idcardFront = idcardFront;
        }

        public String getInterviewName() {
            return interviewName;
        }

        public void setInterviewName(String interviewName) {
            this.interviewName = interviewName;
        }

        public String getInterviewPhone() {
            return interviewPhone;
        }

        public void setInterviewPhone(String interviewPhone) {
            this.interviewPhone = interviewPhone;
        }

        public String getInterviewUserId() {
            return interviewUserId;
        }

        public void setInterviewUserId(String interviewUserId) {
            this.interviewUserId = interviewUserId;
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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getUseCount() {
            return useCount;
        }

        public void setUseCount(int useCount) {
            this.useCount = useCount;
        }

        public String getVisitMatter() {
            return visitMatter;
        }

        public void setVisitMatter(String visitMatter) {
            this.visitMatter = visitMatter;
        }

        public int getVisitTime() {
            return visitTime;
        }

        public void setVisitTime(int visitTime) {
            this.visitTime = visitTime;
        }

        public String getVisitorFront() {
            return visitorFront;
        }

        public void setVisitorFront(String visitorFront) {
            this.visitorFront = visitorFront;
        }

        public int getVisitorId() {
            return visitorId;
        }

        public void setVisitorId(int visitorId) {
            this.visitorId = visitorId;
        }

        public String getVisitorName() {
            return visitorName;
        }

        public void setVisitorName(String visitorName) {
            this.visitorName = visitorName;
        }

        public String getVisitorPhone() {
            return visitorPhone;
        }

        public void setVisitorPhone(String visitorPhone) {
            this.visitorPhone = visitorPhone;
        }
    }
}
