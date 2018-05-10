package com.bangjiat.bjt.module.main.account.beans;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/10 0010
 */

public class ValidateCodeInput {
    private String username;
    private String code;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ValidateCodeInput(String username, String code) {
        this.username = username;
        this.code = code;
    }
}
