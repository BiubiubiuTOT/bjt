package com.bangjiat.bjt.module.park.apply.beans;

import java.util.List;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/11 0011
 */

public class DealParkApplyInput {
    private String applyId;
    private int type;//操作类型1表示同意，2表示拒绝
    private List<Detail> detailList;

    public static class Detail {
        private String userId;
        private int type;//1、固定,2、临时
        private String lotNumber;//车位编号
    }
}
