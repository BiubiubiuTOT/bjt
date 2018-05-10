package com.bangjiat.bjt.module.home.notice.beans;

import java.io.Serializable;
import java.util.List;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/4/16 0016
 */

public class NoticeBean implements Serializable {
    private List<SysNoticeListBean> sysNoticeList;

    public List<SysNoticeListBean> getSysNoticeList() {
        return sysNoticeList;
    }

    public void setSysNoticeList(List<SysNoticeListBean> sysNoticeList) {
        this.sysNoticeList = sysNoticeList;
    }

    public static class SysNoticeListBean implements Serializable {
        /**
         * sNoticeId : 35
         * userId : 1
         * name : gfhfh
         * content : fghd
         * ctime : 1524903088634
         * source : fhgdf
         * type : 1
         */

        private int sNoticeId;
        private String userId;
        private String name;
        private String content;
        private long ctime;
        private String source;
        private int type;

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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
