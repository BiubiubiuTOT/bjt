package com.bangjiat.bjt.module.home.work.kaoqin.beans;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/7 0007
 */

public class PoiAddressBean {
    private String longitude;
    private String latitude;
    private String title;
    private String text;
    private String provinceName;
    private String cityName;
    private String adName;

    public PoiAddressBean(String longitude, String latitude, String title, String text, String provinceName, String cityName, String adName) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.title = title;
        this.text = text;
        this.provinceName = provinceName;
        this.cityName = cityName;
        this.adName = adName;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }
}
