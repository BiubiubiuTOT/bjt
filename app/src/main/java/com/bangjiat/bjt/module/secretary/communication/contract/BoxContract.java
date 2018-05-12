package com.bangjiat.bjt.module.secretary.communication.contract;

import com.bangjiat.bjt.module.secretary.communication.beans.EmailResult;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/12 0012
 */

public interface BoxContract {
    interface Model {
        void getOutBoxList(String token, String key, int page, int size);

        void getInBoxList(String token, String key, int page, int size);
    }

    interface View {
        void showDialog();

        void dismissDialog();

        void success(EmailResult list);

        void fail(String err);
    }

    interface Presenter {
        void getOutBoxList(String token, String key, int page, int size);

        void getInBoxList(String token, String key, int page, int size);

        void success(EmailResult list);

        void fail(String err);
    }
}
