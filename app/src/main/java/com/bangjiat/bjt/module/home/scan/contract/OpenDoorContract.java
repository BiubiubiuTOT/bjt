package com.bangjiat.bjt.module.home.scan.contract;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/23 0023
 */

public interface OpenDoorContract {
    interface Model {
        void getDoorMessage(String token);
    }

    interface View {
        void showDialog();

        void dismissDialog();

        void error(String err);

        void success(String str);
    }

    interface Presenter {
        void error(String err);

        void success(String str);

        void getDoorMessage(String token);
    }
}
