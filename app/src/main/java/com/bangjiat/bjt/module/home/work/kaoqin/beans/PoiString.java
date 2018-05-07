package com.bangjiat.bjt.module.home.work.kaoqin.beans;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/7 0007
 */

public class PoiString {
    private String name;
    private boolean isDefault;

    public PoiString(String name, boolean isDefault) {
        this.name = name;
        this.isDefault = isDefault;
    }

    public PoiString(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }
}
