package com.bangjiat.bjt.module.park.apply.beans;

import java.util.List;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/11 0011
 */

public class ParkApplyInput {
    @Override
    public String toString() {
        return "ParkApplyInput{" +
                "spaceName='" + spaceName + '\'' +
                ", spaceId=" + spaceId +
                ", detailList=" + detailList +
                '}';
    }

    private String spaceName;
    private int spaceId;
    private List<Detail> detailList;

    public static class Detail {
        private String carName;//车主姓名
        private String plateNumber;//车牌号
        private String userId;
        private int type;//1、固定,2、临时
        private int carId;

        public Detail(String carName, String plateNumber, String userId, int type, int carId) {
            this.carName = carName;
            this.plateNumber = plateNumber;
            this.userId = userId;
            this.type = type;
            this.carId = carId;
        }

        @Override
        public String toString() {
            return "Detail{" +
                    "carName='" + carName + '\'' +
                    ", plateNumber='" + plateNumber + '\'' +
                    ", userId='" + userId + '\'' +
                    ", type=" + type +
                    ", carId=" + carId +
                    '}';
        }
    }

    public String getSpaceName() {
        return spaceName;
    }

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }

    public int getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(int spaceId) {
        this.spaceId = spaceId;
    }

    public List<Detail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<Detail> detailList) {
        this.detailList = detailList;
    }


}
