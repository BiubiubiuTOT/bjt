package com.bangjiat.bjt.module.main.contract;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/11 0011
 */

public interface UploadImageContract {
    interface Model {
        void uploadImage(String photo);
    }

    interface View {
        void success(String url);

        void fail(String err);
    }

    interface Presenter {
        void uploadImage(String photo);

        void success(String url);

        void fail(String err);

    }
}
