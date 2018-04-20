package com.bangjiat.bangjiaapp.module.home.company.beans;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/4/18 0018
 */

public class CompanyInput {
    private String name;
    private String address;

    public CompanyInput(String name, String address) {
        this.name = name;
        this.address = address;
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
