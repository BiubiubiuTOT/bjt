package com.bangjiat.bjt.module.secretary.service.contract;

import com.bangjiat.bjt.module.secretary.service.beans.NewApplyInput;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/9 0009
 */

public interface NewServiceApplyContract {
    interface Model {
        void addNewApply(String token, NewApplyInput input);

        void uploadImage(String photo);
    }

    interface View {
        void showDialog();

        void dismissDialog();

        void error(String err);

        void success();

        void uploadSuccess(String s);

        void uploadFail(String err);
    }

    interface Presenter {
        void addNewApply(String token, NewApplyInput input);

        void error(String err);

        void success();

        void uploadImage(String photo);

        void uploadSuccess(String s);

        void uploadFail(String err);
    }
}
