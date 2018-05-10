package com.bangjiat.bjt.module.main.account.contract;

import com.bangjiat.bjt.module.main.account.beans.RecoveredPasswordInput;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/10 0010
 */

public interface RecoveredPasswordContract {
    interface Model {
        void update(RecoveredPasswordInput input);
    }

    interface View {
        void showDialog();

        void dismissDialog();

        void error(String err);

        void success();
    }

    interface Presenter {
        void update(String phone,String ps, String ps2);

        void error(String err);

        void success();
    }
}
