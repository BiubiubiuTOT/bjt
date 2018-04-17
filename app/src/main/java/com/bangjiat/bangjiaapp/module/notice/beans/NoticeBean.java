package com.bangjiat.bangjiaapp.module.notice.beans;

import java.io.Serializable;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/4/16 0016
 */

public class NoticeBean implements Serializable {
    private boolean isRead;
    private String title;
    private String content;
    private String time;

    public NoticeBean(boolean isRead, String title, String content, String time) {
        this.isRead = isRead;
        this.title = title;
        this.content = content;
        this.time = time;
    }

    public NoticeBean(String title, String content, String time) {
        this.title = title;
        this.content = content;
        this.time = time;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "NoticeBean{" +
                "isRead=" + isRead +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
