package com.bangjiat.bjt.module.secretary.contact.contract;

import com.bangjiat.bjt.module.secretary.contact.beans.ContactBean;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/5 0005
 */

public interface UpdateContactContract {
    interface Model {
        void updateContact(String token, ContactBean bean);

        void deleteContact(String token, String addressListId);
    }

    interface View {
        void showDialog();

        void dismissDialog();

        void showErr(String err);

        void deleteSuccess();

        void updateSuccess();
    }

    interface Presenter {
        void updateContact(String token, ContactBean bean);

        void deleteContact(String token, String addressListId);

        void showErr(String err);

        void deleteSuccess();

        void updateSuccess();
    }
}
