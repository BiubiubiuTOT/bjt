package com.bangjiat.bjt.module.me.setting.contract;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/3 0003
 */

public interface ForgetPasswordContract {
    interface Model {
        void getRegisterCode(String phone, int type);
    }

    interface View {
        void showDialog();

        void dismissDialog();

        void getCodeFail(String err);

        void getCodeSuccess();
    }

    interface Presenter {
        void getRegisterCode(String phone, int type);

        void getCodeFail(String err);

        void getCodeSuccess();
    }
}
