package com.bangjiat.bjt.module.secretary.contact.contract;

import com.bangjiat.bjt.module.secretary.contact.beans.SearchContactResult;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/4 0004
 */

public interface SaveContactContract {
    interface Model {
        void saveContact(String token, SearchContactResult input);
    }

    interface View {
        void showDialog();

        void dismissDialog();

        void fail(String err);

        void success();
    }

    interface Presenter {
        void saveContact(String token, SearchContactResult input);

        void success();

        void fail(String err);
    }
}
