package com.bangjiat.bjt.module.me.personaldata.beans;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/7 0007
 */


public class CompanyUserBean extends SugarRecord {
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
     * companyId : 12 公司编号
     * userId : 115f6598290846baacd0c827b0070095
     * type : 2  员工类型：1、普通员工；2、工作台管理员；3、公司管理员
     * companyName : 123 公司名称
     * phone : 17685302679
     */

    private String companyUserId;
    @Unique
    private String companyId;
    private String userId;
    private int type;
    private String companyName;
    private String phone;
    private String idNumber;
    private String department;
    private String job;
    private String realname;

    public CompanyUserBean() {
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
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