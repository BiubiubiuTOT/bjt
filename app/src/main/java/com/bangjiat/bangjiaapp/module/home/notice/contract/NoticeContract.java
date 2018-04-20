package com.bangjiat.bangjiaapp.module.home.notice.contract;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/4/16 0016
 */

public interface NoticeContract {
    interface Model {
        void getAllNotice(String token);
    }

    interface View {
        void showDialog();

        void dismissDialog();

        void getAllNoticeResult();

        void showError(String err);
    }

    interface Presenter {
        void getAllNotice(String token);

        void getNoticeSuccess();

        void getNoticeFail(String error);
    }
}
