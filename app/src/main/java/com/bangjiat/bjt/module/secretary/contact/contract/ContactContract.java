package com.bangjiat.bjt.module.secretary.contact.contract;

import com.bangjiat.bjt.module.secretary.contact.beans.ContactBean;

import java.util.List;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/4 0004
 */

public interface ContactContract {
    interface Model {
        void getAllContacts(String token, String key);
    }

    interface View {
        void showDialog();

        void dismissDialog();

        void fail(String err);

        void success(List<ContactBean> bean);
    }

    interface Presenter {
        void fail(String err);

        void success(List<ContactBean> bean);

        void getAllContacts(String token, String key);
    }
}
