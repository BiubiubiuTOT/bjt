package com.bangjiat.bjt.module.secretary.door.contract;

import com.bangjiat.bjt.module.secretary.door.beans.IntoBuildingInput;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/4/21 0021
 */

public interface IntoBuildingContract {
    interface Model {
        void intoBuilding(String token, IntoBuildingInput input);
    }

    interface View {
        void showDialog();

        void dismissDialog();

        void success();

        void fail(String err);

    }

    interface Presenter {
        void intoBuilding(String token, IntoBuildingInput input);

        void success();

        void fail(String err);
    }
}
