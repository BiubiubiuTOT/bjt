package com.bangjiat.bjt.module.park.apply.beans;

import java.util.List;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/11 0011
 */

public class ParkApplyInput {
    private String spaceName;
    private String spaceId;
    private List<Detail> detailList;

    public static class Detail {
        private String carName;//车主姓名
        private String plateNumber;//车牌号
        private String userId;
        private int type;//1、固定,2、临时
        private String carId;
    }

    public String getSpaceName() {
        return spaceName;
    }

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }

    public String getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(String spaceId) {
        this.spaceId = spaceId;
    }

    public List<Detail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<Detail> detailList) {
        this.detailList = detailList;
    }
}
