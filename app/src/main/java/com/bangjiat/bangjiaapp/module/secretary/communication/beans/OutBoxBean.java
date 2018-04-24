package com.bangjiat.bangjiaapp.module.secretary.communication.beans;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/4/23 0023
 */

public class OutBoxBean {
    private String head;
    private String name;
    private String message;
    private String detail;
    private String time;

    public OutBoxBean(String name) {
        this.name = name;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
