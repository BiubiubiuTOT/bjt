package com.bangjiat.bjt.module.secretary.communication.contract;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/12 0012
 */

public interface DealBoxContract {
    interface Model {
        void deleteOutBox(String token, String[] strings);

        void deleteInBox(String token, String[] strings);

        void markBox(String token, String[] strings);

        void getUnReadCounts(String token);
    }

    interface View {
        void showDialog();

        void dismissDialog();

        void fail(String err);

        void deleteOutBoxSuccess();

        void deleteInBoxSuccess();

        void markBoxSuccess();

        void getUnReadCountsSuccess(String s);
    }

    interface Presenter {
        void fail(String err);

        void deleteOutBoxSuccess();

        void deleteInBoxSuccess();

        void markBoxSuccess();

        void deleteOutBox(String token, String[] strings);

        void deleteInBox(String token, String[] strings);

        void markBox(String token, String[] strings);

        void getUnReadCounts(String token);

        void getUnReadCountsSuccess(String s);
    }
}
