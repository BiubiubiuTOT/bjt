package com.bangjiat.bjt.module.secretary.service.contract;

import com.bangjiat.bjt.module.secretary.service.beans.ServiceApplyHistoryResult;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/9 0009
 */

public interface ServiceApplyHistoryContract {
    interface Model {
        void getHistory(String token, int page, int size);
    }

    interface View {
        void showDialog();

        void dismissDialog();

        void success(ServiceApplyHistoryResult result);

        void error(String err);
    }

    interface Presenter {
        void getHistory(String token, int page, int size);

        void success(ServiceApplyHistoryResult result);

        void error(String err);
    }
}
