package com.bangjiat.bangjiaapp.module.park.apply.beans;

import java.io.Serializable;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/4/25 0025
 */

public class CarInfoBean implements Serializable {
    private String userName;
    private String carNumber;
    private String type;
    private String color;

    public CarInfoBean(String userName, String carNumber, String type, String color) {
        this.userName = userName;
        this.carNumber = carNumber;
        this.type = type;
        this.color = color;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
