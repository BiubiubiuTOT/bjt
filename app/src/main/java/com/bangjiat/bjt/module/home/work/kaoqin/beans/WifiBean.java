package com.bangjiat.bjt.module.home.work.kaoqin.beans;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/2 0002
 */

public class WifiBean {
    private String name;
    private boolean isConnected;

    public WifiBean(String name, boolean isConnected) {
        this.name = name;
        this.isConnected = isConnected;
    }

    public WifiBean() {
    }

    public WifiBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }
}
