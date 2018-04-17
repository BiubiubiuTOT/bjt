package com.bangjiat.bangjiaapp.module.account.beans;

/**
 * Created by Administrator on 2018/4/14 0014.
 */

public class Account {
    private String phone;
    private String password;

    public Account(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" +
                "phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
