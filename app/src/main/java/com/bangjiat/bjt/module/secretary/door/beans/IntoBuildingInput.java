package com.bangjiat.bjt.module.secretary.door.beans;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/4/21 0021
 */

public class IntoBuildingInput {
    private String companyId;
    private String realname;
    private String code;

    public IntoBuildingInput(String companyId, String realname, String code) {
        this.companyId = companyId;
        this.realname = realname;
        this.code = code;
    }

    public IntoBuildingInput(String code) {
        this.code = code;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "IntoBuildingInput{" +
                "companyId='" + companyId + '\'' +
                ", realname='" + realname + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

}
