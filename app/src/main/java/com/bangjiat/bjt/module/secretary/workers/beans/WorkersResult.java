package com.bangjiat.bjt.module.secretary.workers.beans;

import java.io.Serializable;
import java.util.List;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/8 0008
 */

public class WorkersResult {
    /**
     * current : 0
     * pages : 0
     * records : [{"companyId":0,"companyName":"string","companyUserId":0,"department":"string","idNumber":"string","job":"string","phone":"string","realname":"string","type":0,"userId":"string"}]
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
         * companyId : 0 公司编号
         * companyName : string
         * companyUserId : 0
         * department : string 部门
         * idNumber : string
         * job : string 职务
         * phone : string
         * realname : string
         * type : 0 员工类型：1、普通员工；2、工作台管理员；3、公司管理员
         * userId : string
         */

        private int companyId;
        private String companyName;
        private int companyUserId;
        private String department;
        private String idNumber;
        private String job;
        private String phone;
        private String realname;
        private int type;
        private String userId;

        public int getCompanyId() {
            return companyId;
        }

        public void setCompanyId(int companyId) {
            this.companyId = companyId;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public int getCompanyUserId() {
            return companyUserId;
        }

        public void setCompanyUserId(int companyUserId) {
            this.companyUserId = companyUserId;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public String getIdNumber() {
            return idNumber;
        }

        public void setIdNumber(String idNumber) {
            this.idNumber = idNumber;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
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

        @Override
        public String toString() {
            return "RecordsBean{" +
                    "companyId=" + companyId +
                    ", companyName='" + companyName + '\'' +
                    ", companyUserId=" + companyUserId +
                    ", department='" + department + '\'' +
                    ", idNumber='" + idNumber + '\'' +
                    ", job='" + job + '\'' +
                    ", phone='" + phone + '\'' +
                    ", realname='" + realname + '\'' +
                    ", type=" + type +
                    ", userId='" + userId + '\'' +
                    '}';
        }

    }


    @Override
    public String toString() {
        return "WorkersResult{" +
                "current=" + current +
                ", pages=" + pages +
                ", size=" + size +
                ", total=" + total +
                ", records=" + records +
                '}';
    }
}
