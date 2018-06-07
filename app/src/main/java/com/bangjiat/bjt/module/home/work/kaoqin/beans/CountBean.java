package com.bangjiat.bjt.module.home.work.kaoqin.beans;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/17 0017
 */

public class CountBean {
    private String name;//名称
    private int counts;//次数
    private int time;//时长

    public CountBean(String name, int counts, int time) {
        this.name = name;
        this.counts = counts;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "CountBean{" +
                "name='" + name + '\'' +
                ", counts=" + counts +
                ", time=" + time +
                '}';
    }
}
