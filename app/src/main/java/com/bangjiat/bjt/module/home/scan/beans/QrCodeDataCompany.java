package com.bangjiat.bjt.module.home.scan.beans;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/5 0005
 */

public class QrCodeDataCompany {
    private String companyName;
    private String companyId;
    private String industry;
    private int qrCodeType;

    public QrCodeDataCompany(String companyName, String companyId, String industry) {
        this.companyName = companyName;
        this.companyId = companyId;
        this.industry = industry;
    }

    @Override
    public String toString() {
        return "QrCodeDataCompany{" +
                "companyName='" + companyName + '\'' +
                ", companyId='" + companyId + '\'' +
                ", industry='" + industry + '\'' +
                '}';
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public int getQrCodeType() {
        return qrCodeType;
    }

    public void setQrCodeType(int qrCodeType) {
        this.qrCodeType = qrCodeType;
    }
}
