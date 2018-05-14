package com.bangjiat.bjt.module.park.pay.contract;

import com.bangjiat.bjt.module.park.pay.beans.ParkingDetail;
import com.bangjiat.bjt.module.park.pay.beans.PayBean;
import com.bangjiat.bjt.module.park.pay.beans.PayInput;
import com.bangjiat.bjt.module.park.pay.beans.PayListResult;

import java.util.List;

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

        void getParkingDetail(String token, int spaceId);
    }

    interface View {
        void showDialog();

        void dismissDialog();

        void getPayListSuccess(List<PayListResult> str);

        void paySuccess(String str);

        void addPayInfoSuccess(String err);

        void getParkingDetailSuccess(ParkingDetail detail);

        void fail(String err);
    }

    interface Presenter {
        void addPayInfo(String token, PayBean bean);

        void pay(String token, PayInput input);

        void getPayList(String token);

        void getPayListSuccess(List<PayListResult> str);

        void paySuccess(String str);

        void addPayInfoSuccess(String err);

        void getParkingDetail(String token, int spaceId);

        void getParkingDetailSuccess(ParkingDetail detail);

        void fail(String err);
    }
}
