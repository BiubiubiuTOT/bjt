package com.bangjiat.bjt.module.home.scan.beans;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/5 0005
 */

public class QrCodeDataCompany {
    private String cid;//公司id
    private int type;

    public QrCodeDataCompany(String cid, int type) {
        this.cid = cid;
        this.type = type;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
