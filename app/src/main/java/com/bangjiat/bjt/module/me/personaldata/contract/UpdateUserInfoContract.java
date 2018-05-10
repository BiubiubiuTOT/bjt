package com.bangjiat.bjt.module.me.personaldata.contract;

import com.bangjiat.bjt.module.me.personaldata.beans.UserInfo;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/4/19 0019
 */

public interface UpdateUserInfoContract {
    interface Model {
        void updateUserInfo(String token, UserInfo bean);

        void uploadUserHead(String photo);
    }

    interface View {
        void showDialog();

        void dismissDialog();

        void updateUserInfoSuccess(UserInfo info);

        void updateUserInfoFail(String err);

        void uploadUserHeadSuccess(String url);

        void uploadUserHeadFail(String err);
    }

    interface Presenter {

        void updateUserInfoSuccess(UserInfo info);

        void updateUserInfoFail(String err);

        void updateUserInfo(String token, UserInfo bean);

        void uploadUserHead(String photo);

        void uploadUserHeadSuccess(String url);

        void uploadUserHeadFail(String err);
    }
}
