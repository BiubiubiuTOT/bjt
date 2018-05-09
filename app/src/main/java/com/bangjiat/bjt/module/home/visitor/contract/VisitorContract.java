package com.bangjiat.bjt.module.home.visitor.contract;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/4/16 0016
 */

public interface VisitorContract {
    interface Model {
        void getVisitorHistory(String token, int page, int size);
    }

    interface View {
        void showDialog();

        void dismissDialog();

        void error(String err);

        void success();
    }

    interface Presenter {
        void getVisitorHistory(String token, int page, int size);

        void error(String err);

        void success();
    }
}
