package com.bangjiat.bjt.module.secretary.door.contract;

import com.bangjiat.bjt.module.secretary.door.beans.DoorApplyDetailResult;

import java.util.List;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/10 0010
 */

public interface DoorApplyDetailContract {
    interface Model {
        void getDetail(String token, String guardMainId);
    }

    interface View {
        void success(List<DoorApplyDetailResult> results);

        void error(String err);

        void showDialog();

        void dismissDialog();
    }

    interface Presenter {
        void getDetail(String token, String guardMainId);

        void success(List<DoorApplyDetailResult> results);

        void error(String err);
    }
}
