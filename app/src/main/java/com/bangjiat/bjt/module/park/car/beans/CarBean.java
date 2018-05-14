package com.bangjiat.bjt.module.park.car.beans;

import java.io.Serializable;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/11 0011
 */

public class CarBean implements Serializable {
    /**
     * brand : string  品牌
     * carId : 0
     * color : string  颜色
     * ctime : 0
     * driveNumber : string 行驶证号
     * idNumber : string
     * licenceNumber : string  驾驶证号
     * model : string  型号
     * name : string  车主姓名
     * plateNumber : string  车牌号
     * resource : string  车辆照片json数据
     * userId : string
     * username : string
     */

    private String brand;
    private int carId;
    private String color;
    private long ctime;
    private String driveNumber;
    private String idNumber;
    private String licenceNumber;
    private String model;
    private String name;
    private String plateNumber;
    private String resource;
    private String userId;
    private String username;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

    public String getDriveNumber() {
        return driveNumber;
    }

    public void setDriveNumber(String driveNumber) {
        this.driveNumber = driveNumber;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "CarBean{" +
                "brand='" + brand + '\'' +
                ", carId=" + carId +
                ", color='" + color + '\'' +
                ", ctime=" + ctime +
                ", driveNumber='" + driveNumber + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", licenceNumber='" + licenceNumber + '\'' +
                ", model='" + model + '\'' +
                ", name='" + name + '\'' +
                ", plateNumber='" + plateNumber + '\'' +
                ", resource='" + resource + '\'' +
                ", userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

}
