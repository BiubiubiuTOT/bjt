package com.bangjiat.bjt.module.me.feedback.contract;

import com.bangjiat.bjt.module.me.feedback.beans.FeedBackInput;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/3 0003
 */

public interface FeedBackContract {

    interface Model {
        void saveFeedBack(String token, FeedBackInput input);
    }

    interface View {
        void showDialog();

        void dismissDialog();

        void showError(String err);

        void success();
    }

    interface Presenter {
        void saveFeedBack(String token, FeedBackInput input);

        void success();

        void fail(String err);
    }
}
