package com.bangjiat.bjt.module.main.account.beans;

/**
 * Created by Administrator on 2018/4/14 0014.
 */

public class LoginInput {
    private String username;
    private String password;

    public LoginInput(String userName, String password) {
        this.username = userName;
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginInput{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
