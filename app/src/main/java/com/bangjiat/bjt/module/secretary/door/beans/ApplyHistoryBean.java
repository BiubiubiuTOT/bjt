package com.bangjiat.bjt.module.secretary.door.beans;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/4/20 0020
 */

public class ApplyHistoryBean {
    private String companyName;
    private int status;//1.待审核 2.已通过 3.未通过
    private String statusDes;
    private HandleHistory handleHistory;

    private ApplyPeople applyPeople;

    public static class ApplyPeople {
        private String name;
        private String phoneNumber;
        private String IdNumber;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getIdNumber() {
            return IdNumber;
        }

        public void setIdNumber(String idNumber) {
            IdNumber = idNumber;
        }

        public ApplyPeople(String name, String phoneNumber, String idNumber) {
            this.name = name;
            this.phoneNumber = phoneNumber;
            IdNumber = idNumber;
        }
    }

    public static class HandleHistory {
        private String name;
        private String time;
        private String statusDes;
        private int status;

        public HandleHistory(String name, String time, String statusDes, int status) {
            this.name = name;
            this.time = time;
            this.statusDes = statusDes;
            this.status = status;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getStatusDes() {
            return statusDes;
        }

        public void setStatusDes(String statusDes) {
            this.statusDes = statusDes;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusDes() {
        return statusDes;
    }

    public void setStatusDes(String statusDes) {
        this.statusDes = statusDes;
    }

    public HandleHistory getHandleHistory() {
        return handleHistory;
    }

    public void setHandleHistory(HandleHistory handleHistory) {
        this.handleHistory = handleHistory;
    }

    public ApplyPeople getApplyPeople() {
        return applyPeople;
    }

    public void setApplyPeople(ApplyPeople applyPeople) {
        this.applyPeople = applyPeople;
    }
}
