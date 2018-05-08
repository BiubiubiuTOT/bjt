package com.bangjiat.bjt.module.secretary.door.contract;

import com.bangjiat.bjt.module.secretary.door.beans.ApplyHistoryBean;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/8 0008
 */

public interface DoorApplyHistoryContract {
    interface Model {
        void getDoorApplyHistory(String token, int page, int size);
    }

    interface View {
        void showDialog();

        void dismissDialog();

        void getDoorApplyHistorySuccess(ApplyHistoryBean bean);

        void error(String err);
    }

    interface Presenter {
        void getDoorApplyHistory(String token, int page, int size);

        void getDoorApplyHistorySuccess(ApplyHistoryBean bean);

        void error(String err);
    }
}
