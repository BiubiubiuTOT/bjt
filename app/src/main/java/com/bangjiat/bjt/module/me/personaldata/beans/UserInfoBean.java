package com.bangjiat.bjt.module.me.personaldata.beans;

import com.orm.SugarRecord;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/4/19 0019
 */

public class UserInfoBean {

    /**
     * userInfo : {"userId":"115f6598290846baacd0c827b0070095","username":"17685302679","password":"D02C545E8396A44DFF94144B208355C0","salt":"57397963ffbc40859852f9d9f13ee40d","phone":"17685302679","locked":0,"ctime":1523674617121}
     * count : 1
     * companyUser : {"companyUserId":13,"companyId":12,"userId":"115f6598290846baacd0c827b0070095","type":2,"companyName":"123","phone":"17685302679"}
     */

    private UserInfo userInfo;
    private int count;
    private CompanyUserBean companyUser;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public CompanyUserBean getCompanyUser() {
        return companyUser;
    }

    public void setCompanyUser(CompanyUserBean companyUser) {
        this.companyUser = companyUser;
    }


    public static class CompanyUserBean extends SugarRecord {
        @Override
        public String toString() {
            return "CompanyUserBean{" +
                    "companyUserId=" + companyUserId +
                    ", companyId=" + companyId +
                    ", userId='" + userId + '\'' +
                    ", type=" + type +
                    ", companyName='" + companyName + '\'' +
                    ", phone='" + phone + '\'' +
                    '}';
        }

        /**
         * companyUserId : 13
         * companyId : 12
         * userId : 115f6598290846baacd0c827b0070095
         * type : 2
         * companyName : 123
         * phone : 17685302679
         */

        private String companyUserId;
        private String companyId;
        private String userId;
        private int type;
        private String companyName;
        private String phone;

        public CompanyUserBean() {
        }

        public String getCompanyUserId() {
            return companyUserId;
        }

        public void setCompanyUserId(String companyUserId) {
            this.companyUserId = companyUserId;
        }

        public String getCompanyId() {
            return companyId;
        }

        public void setCompanyId(String companyId) {
            this.companyId = companyId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}
