package com.bangjiat.bjt.module.me.feedback.beans;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/3 0003
 */

public class FeedBackInput {
    /**
     * content : string
     * ctime : 0
     * feedId : 0
     * resource : string
     * userId : string
     */

    private String content;
    private long ctime;
    private long feedId;
    private String resource;
    private String userId;

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

    public long getFeedId() {
        return feedId;
    }

    public void setFeedId(long feedId) {
        this.feedId = feedId;
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
}
