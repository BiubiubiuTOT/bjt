package com.bangjiat.bjt.module.secretary.communication.contract;

import com.bangjiat.bjt.module.secretary.communication.beans.EmailBean;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/12 0012
 */

public interface SendEmailContract {
    interface Model {
        void sendEmail(String token, EmailBean bean);
    }

    interface View {
        void showDialog();

        void dismissDialog();

        void success();

        void fail(String err);
    }

    interface Presenter {
        void sendEmail(String token, EmailBean bean);

        void success();

        void fail(String err);
    }
}
