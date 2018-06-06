package com.bangjiat.bjt.common;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/6/6 0006
 */

public class BannerBean {
    /**
     * belong : 0
     * carouselId : 0
     * ctime : 0
     * imagePath : string
     * link : string
     * name : string
     * orderValue : 0
     * remark : string
     * showEndTime : 0
     * showStartTime : 0
     * status : 0
     * type : 0
     */

    private int belong;//所属的APP
    private int carouselId;
    private long ctime;
    private String imagePath;//图片路径
    private String link;//跳转链接
    private String name;//图片名称
    private int orderValue;//显示顺序
    private String remark;
    private long showEndTime;//投放在APP首页的结束时间
    private long showStartTime;//投放在APP首页的开始时间
    private int status;//状态：1为禁用、2为启用
    private int type;//类型：1为广告，2为普通图片

    public int getBelong() {
        return belong;
    }

    public void setBelong(int belong) {
        this.belong = belong;
    }

    public int getCarouselId() {
        return carouselId;
    }

    public void setCarouselId(int carouselId) {
        this.carouselId = carouselId;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(int orderValue) {
        this.orderValue = orderValue;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getShowEndTime() {
        return showEndTime;
    }

    public void setShowEndTime(long showEndTime) {
        this.showEndTime = showEndTime;
    }

    public long getShowStartTime() {
        return showStartTime;
    }

    public void setShowStartTime(long showStartTime) {
        this.showStartTime = showStartTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
