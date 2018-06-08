package com.bangjiat.bjt.module.home.scan.beans;

import java.io.Serializable;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/5 0005
 */

public class QrCodeDataUser implements Serializable {
    private String un;//手机号
    private int type;//2 个人

    public QrCodeDataUser(String un, int type) {
        this.un = un;
        this.type = type;
    }

    public String getUn() {
        return un;
    }

    public void setUn(String un) {
        this.un = un;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
