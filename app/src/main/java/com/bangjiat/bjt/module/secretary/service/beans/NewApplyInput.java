package com.bangjiat.bjt.module.secretary.service.beans;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/9 0009
 */

public class NewApplyInput {
    private String application;//申请事项
    private String content;
    private ProgressBean progress;//审批进度
    private String sources;

    public NewApplyInput(String application, String content, ProgressBean progress) {
        this.application = application;
        this.content = content;
        this.progress = progress;
    }

    public NewApplyInput(String application, String content, ProgressBean progress, String source) {
        this.application = application;
        this.content = content;
        this.progress = progress;
        this.sources = source;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static class ProgressBean {
        private String userId;
        private String userRealname;
        private String username;

        public ProgressBean(String userId, String userRealname, String username) {
            this.userId = userId;
            this.userRealname = userRealname;
            this.username = username;
        }
    }
}
