package com.bangjiat.bjt.module.park.pay.beans;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/14 0014
 */

public class ParkingDetail {
    /**
     * address : string
     * contact : string
     * contactWay : string
     * ctime : 0
     * fixed : 0
     * fixedNumber : 0
     * hourFee : 0
     * latitude : string
     * longitude : string
     * monthFee : 0
     * name : string
     * number : 0
     * open : 0
     * spaceId : 0
     * temporary : 0
     * temporaryNumber : 0
     * yearFee : 0
     */

    private String address;
    private String contact;
    private String contactWay;
    private long ctime;
    private int fixed;
    private int fixedNumber;
    private int hourFee;
    private String latitude;
    private String longitude;
    private String monthFee;
    private String name;
    private int number;
    private int open;
    private int spaceId;
    private int temporary;
    private int temporaryNumber;
    private int yearFee;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

    public int getFixed() {
        return fixed;
    }

    public void setFixed(int fixed) {
        this.fixed = fixed;
    }

    public int getFixedNumber() {
        return fixedNumber;
    }

    public void setFixedNumber(int fixedNumber) {
        this.fixedNumber = fixedNumber;
    }

    public int getHourFee() {
        return hourFee;
    }

    public void setHourFee(int hourFee) {
        this.hourFee = hourFee;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getMonthFee() {
        return monthFee;
    }

    public void setMonthFee(String monthFee) {
        this.monthFee = monthFee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getOpen() {
        return open;
    }

    public void setOpen(int open) {
        this.open = open;
    }

    public int getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(int spaceId) {
        this.spaceId = spaceId;
    }

    public int getTemporary() {
        return temporary;
    }

    public void setTemporary(int temporary) {
        this.temporary = temporary;
    }

    public int getTemporaryNumber() {
        return temporaryNumber;
    }

    public void setTemporaryNumber(int temporaryNumber) {
        this.temporaryNumber = temporaryNumber;
    }

    public int getYearFee() {
        return yearFee;
    }

    public void setYearFee(int yearFee) {
        this.yearFee = yearFee;
    }
}
