package com.bangjiat.bjt.module.me.setting.contract;

import com.bangjiat.bjt.module.me.setting.beans.UpdatePasswordInput;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/10 0010
 */

public interface UpdatePasswordContract {
    interface Model {
        void updatePassword(String token, UpdatePasswordInput input);
    }

    interface View {
        void showDialog();

        void dismissDialog();

        void success();

        void showErr(String err);
    }

    interface Presenter {
        void updatePassword(String token, String old, String ne, String ne2, String userId);

        void success();

        void fail(String err);
    }
}
