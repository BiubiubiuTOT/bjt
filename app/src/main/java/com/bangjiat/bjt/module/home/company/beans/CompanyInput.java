package com.bangjiat.bjt.module.home.company.beans;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/4/18 0018
 */

public class CompanyInput {
    private String name;
    private String address;
    private String industry;

    public CompanyInput(String name, String address,String industry) {
        this.name = name;
        this.address = address;
        this.industry = industry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
