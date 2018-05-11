package com.bangjiat.bjt.module.home.company.beans;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/11 0011
 */

public class CompanyDetailResult {
    /**
     * address : string  公司具体地址
     * buildId : 0 楼宇编号
     * buildName : string 楼宇名称
     * code : string 邀请码
     * companyId : 0 公司编号
     * contact : string 联系人
     * contactWay : string 联系方式
     * ctime : 0
     * doorPlate : string 门牌号
     * industry : string 在行业
     * name : string 公司名称
     * remark : string
     * simple : string 公司简称
     * utime : 0
     */

    private String address;
    private String buildId;
    private String buildName;
    private String code;
    private String companyId;
    private String contact;
    private String contactWay;
    private long ctime;
    private String doorPlate;
    private String industry;
    private String name;
    private String remark;
    private String simple;
    private long utime;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContactWay() {
        return contactWay;
    }

    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(int ctime) {
        this.ctime = ctime;
    }

    public String getDoorPlate() {
        return doorPlate;
    }

    public void setDoorPlate(String doorPlate) {
        this.doorPlate = doorPlate;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSimple() {
        return simple;
    }

    public void setSimple(String simple) {
        this.simple = simple;
    }

    public long getUtime() {
        return utime;
    }

    public void setUtime(int utime) {
        this.utime = utime;
    }
}
