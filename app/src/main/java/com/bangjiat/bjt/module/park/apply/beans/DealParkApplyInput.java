package com.bangjiat.bjt.module.park.apply.beans;

import java.util.List;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/11 0011
 */

public class DealParkApplyInput {
    private int applyId;
    private int type;//操作类型1表示同意，2表示拒绝
    private String opinion;
    private List<Detail> detailList;

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public static class Detail {
        private String userId;
        private int type;//1、固定,2、临时
        private String lotNumber;//车位编号
        private long terminalTime;
        private int detailId;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getLotNumber() {
            return lotNumber;
        }

        public void setLotNumber(String lotNumber) {
            this.lotNumber = lotNumber;
        }

        public long getTerminalTime() {
            return terminalTime;
        }

        public void setTerminalTime(long terminalTime) {
            this.terminalTime = terminalTime;
        }

        public int getDetailId() {
            return detailId;
        }

        public void setDetailId(int detailId) {
            this.detailId = detailId;
        }

        public Detail(String userId, int type, int detailId) {
            this.userId = userId;
            this.type = type;
            this.detailId = detailId;
        }

        public Detail(String userId, int type, String lotNumber, long terminalTime, int detailId) {
            this.userId = userId;
            this.type = type;
            this.lotNumber = lotNumber;
            this.terminalTime = terminalTime;
            this.detailId = detailId;
        }

        @Override
        public String toString() {
            return "Detail{" +
                    "userId='" + userId + '\'' +
                    ", type=" + type +
                    ", lotNumber='" + lotNumber + '\'' +
                    '}';
        }
    }

    public int getApplyId() {
        return applyId;
    }

    public void setApplyId(int applyId) {
        this.applyId = applyId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Detail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<Detail> detailList) {
        this.detailList = detailList;
    }

    @Override
    public String toString() {
        return "DealParkApplyInput{" +
                "applyId=" + applyId +
                ", type=" + type +
                ", detailList=" + detailList +
                '}';
    }
}
