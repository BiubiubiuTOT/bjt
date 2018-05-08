package com.bangjiat.bjt.module.secretary.workers.ui.contract;

import com.bangjiat.bjt.module.secretary.workers.beans.WorkersResult;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/8 0008
 */

public interface CompanyUserContract {
    interface Model {
        void getCompanyUser(String token, int page, int size, int type);
    }

    interface View {
        void showDialog();

        void dismissDialog();

        void error(String err);

        void getCompanyUserSuccess(WorkersResult result);
    }

    interface Presenter {
        void error(String err);

        void getCompanyUserSuccess(WorkersResult result);

        void getCompanyUser(String token, int page, int size, int type);
    }
}
