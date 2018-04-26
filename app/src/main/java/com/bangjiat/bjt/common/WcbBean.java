package com.bangjiat.bjt.common;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/4/26 0026
 */

public class WcbBean {
    private String name;
    private int corlor;

    public WcbBean(String name) {
        this.name = name;
    }

    public WcbBean(String name, int corlor) {
        this.name = name;
        this.corlor = corlor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCorlor() {
        return corlor;
    }

    public void setCorlor(int corlor) {
        this.corlor = corlor;
    }
}
