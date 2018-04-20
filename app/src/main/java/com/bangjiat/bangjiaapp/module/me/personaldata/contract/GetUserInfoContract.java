package com.bangjiat.bangjiaapp.module.me.personaldata.contract;

import com.bangjiat.bangjiaapp.module.me.personaldata.beans.UserInfoBean;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/4/19 0019
 */

public interface GetUserInfoContract {
    interface Model {
        void getUserInfo(String token);
    }

    interface View {
        void getUserInfoFail(String err);

        void getUserInfoSuccess(UserInfoBean bean);

    }

    interface Presenter {
        void getUserInfo(String token);

        void getUserInfoFail(String err);

        void getUserInfoSuccess(UserInfoBean bean);

    }
}
