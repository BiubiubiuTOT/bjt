package com.bangjiat.bangjiaapp.module.main.account.beans;

/**
 * Created by Administrator on 2018/4/14 0014.
 */

public class RegisterInput {
    private String username;
    private String phone;
    private String password;
    private String code;

    public RegisterInput(String username, String phone, String password, String code) {
        this.username = username;
        this.phone = phone;
        this.password = password;
        this.code = code;
    }

    @Override
    public String toString() {
        return "RegisterInput{" +
                "username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
