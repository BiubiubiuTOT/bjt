package com.bangjiat.bjt.module.home.notice.beans;

import com.orm.SugarRecord;
import com.orm.dsl.Column;

import java.io.Serializable;
import java.util.List;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/4/16 0016
 */

public class NoticeBean implements Serializable {
    private List<SysNoticeListBean> buildNoticeList;
    private List<SysNoticeListBean> sysNoticeList;

    public List<SysNoticeListBean> getBuildNoticeList() {
        return buildNoticeList;
    }

    public void setBuildNoticeList(List<SysNoticeListBean> buildNoticeList) {
        this.buildNoticeList = buildNoticeList;
    }

    public List<SysNoticeListBean> getSysNoticeList() {
        return sysNoticeList;
    }

    public void setSysNoticeList(List<SysNoticeListBean> sysNoticeList) {
        this.sysNoticeList = sysNoticeList;
    }


    public static class SysNoticeListBean extends SugarRecord implements Serializable {
        /**
         * sNoticeId : 1
         * userId : 3
         * name : 多写点字
         * content : 多写点字多写点
         * ctime : 1527851342452
         * source : fgh
         */
        @Column(name = "sNoticeId")
        private int sNoticeId;

        private String userId;
        private int buildId;
        private String name;
        private String content;
        private long ctime;

        @Column(name = "isRead")
        private boolean isRead;
        private String source;

        public boolean isRead() {
            return isRead;
        }

        public void setRead(boolean read) {
            isRead = read;
        }

        public int getsNoticeId() {
            return sNoticeId;
        }

        public SysNoticeListBean() {
        }

        public void setsNoticeId(int sNoticeId) {
            this.sNoticeId = sNoticeId;
        }

        public int getBuildId() {
            return buildId;
        }

        public void setBuildId(int buildId) {
            this.buildId = buildId;
        }

        public int getSNoticeId() {
            return sNoticeId;
        }

        public void setSNoticeId(int sNoticeId) {
            this.sNoticeId = sNoticeId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getCtime() {
            return ctime;
        }

        public void setCtime(long ctime) {
            this.ctime = ctime;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        @Override
        public String toString() {
            return "SysNoticeListBean{" +
                    "sNoticeId=" + sNoticeId +
                    ", userId='" + userId + '\'' +
                    ", buildId=" + buildId +
                    ", name='" + name + '\'' +
                    ", content='" + content + '\'' +
                    ", ctime=" + ctime +
                    ", source='" + source + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "NoticeBean{" +
                "buildNoticeList=" + buildNoticeList +
                ", sysNoticeList=" + sysNoticeList +
                '}';
    }
}
