package com.bangjiat.bjt.module.secretary.door.contract;

import com.bangjiat.bjt.module.secretary.contact.beans.BuildUserInfo;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/21 0021
 */

public interface BuildUserInfoContract {
    interface Model {
        void getInfo(String token);
    }

    interface View {
        void error(String err);

        void success(BuildUserInfo info);
    }

    interface Presenter {
        void getInfo(String token);

        void error(String err);

        void success(BuildUserInfo info);
    }
}
