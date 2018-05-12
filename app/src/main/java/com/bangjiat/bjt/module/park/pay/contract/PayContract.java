package com.bangjiat.bjt.module.park.pay.contract;

import com.bangjiat.bjt.module.park.pay.beans.PayBean;
import com.bangjiat.bjt.module.park.pay.beans.PayInput;
import com.bangjiat.bjt.module.park.pay.beans.PayListResult;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/12 0012
 */

public interface PayContract {
    interface Model {
        void addPayInfo(String token, PayBean bean);

        void pay(String token, PayInput input);

        void getPayList(String token);
    }

    interface View {
        void showDialog();

        void dismissDialog();

        void getPayListSuccess(PayListResult str);

        void paySuccess(String str);

        void addPayInfoSuccess(String err);

        void fail(String err);
    }

    interface Presenter {
        void addPayInfo(String token, PayBean bean);

        void pay(String token, PayInput input);

        void getPayList(String token);

        void getPayListSuccess(PayListResult str);

        void paySuccess(String str);

        void addPayInfoSuccess(String err);

        void fail(String err);
    }
}
