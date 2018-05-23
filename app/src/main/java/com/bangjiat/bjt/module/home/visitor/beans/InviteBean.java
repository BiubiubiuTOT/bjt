package com.bangjiat.bjt.module.home.visitor.beans;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/23 0023
 */

public class InviteBean {
    private String visitorPhone;
    private String visitorName;
    private String visitMatter;
    private long visitTime;

    public String getVisitorPhone() {
        return visitorPhone;
    }

    public void setVisitorPhone(String visitorPhone) {
        this.visitorPhone = visitorPhone;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getVisitMatter() {
        return visitMatter;
    }

    public void setVisitMatter(String visitMatter) {
        this.visitMatter = visitMatter;
    }

    public long getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(long visitTime) {
        this.visitTime = visitTime;
    }

    public InviteBean(String visitorPhone, String visitorName, String visitMatter, long visitTime) {
        this.visitorPhone = visitorPhone;
        this.visitorName = visitorName;
        this.visitMatter = visitMatter;
        this.visitTime = visitTime;
    }
}
