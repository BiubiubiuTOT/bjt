package com.bangjiat.bjt.module.secretary.door.contract;

import com.bangjiat.bjt.module.secretary.workers.beans.WorkersResult;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/10 0010
 */

public interface DoorApplyContract {
    interface Model {
        void getCompanyUser(String token, int page, int size, int type);

        void addApply(String token, String[] strings);
    }

    interface View {
        void showDialog();

        void dismissDialog();

        void error(String err);

        void getCompanyUserSuccess(WorkersResult result);
        void addApplySuccess();
    }

    interface Presenter {
        void getCompanyUserSuccess(WorkersResult result);

        void getCompanyUser(String token, int page, int size, int type);

        void addApply(String token, String[] strings);

        void error(String err);
        void addApplySuccess();
    }
}
