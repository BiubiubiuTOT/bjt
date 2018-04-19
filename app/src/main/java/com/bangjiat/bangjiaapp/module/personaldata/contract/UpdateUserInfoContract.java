package com.bangjiat.bangjiaapp.module.personaldata.contract;

import com.bangjiat.bangjiaapp.module.personaldata.beans.UserInfoBean;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/4/19 0019
 */

public interface UpdateUserInfoContract {
    interface Model {
        void updateUserInfo(String token, UserInfoBean bean);
    }

    interface View {
        void showDialog();

        void dismissDialog();

        void updateUserInfoSuccess();

        void updateUserInfoFail(String err);
    }

    interface Presenter {

        void updateUserInfoSuccess();

        void updateUserInfoFail(String err);

        void updateUserInfo(String token, UserInfoBean bean);
    }
}
