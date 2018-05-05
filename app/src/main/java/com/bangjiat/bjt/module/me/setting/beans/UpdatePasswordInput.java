package com.bangjiat.bjt.module.me.setting.beans;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/3 0003
 */

public class UpdatePasswordInput {
    private String oldPassword;
    private String newPassword;
    private String userId;

    public UpdatePasswordInput(String oldPassword, String newPassword, String userId) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.userId = userId;
    }
}
