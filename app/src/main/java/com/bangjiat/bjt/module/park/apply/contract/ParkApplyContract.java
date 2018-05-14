package com.bangjiat.bjt.module.park.apply.contract;

import com.bangjiat.bjt.module.park.apply.beans.DealParkApplyInput;
import com.bangjiat.bjt.module.park.apply.beans.ParkApplyInput;
import com.bangjiat.bjt.module.park.apply.beans.ParkingResult;
import com.bangjiat.bjt.module.park.car.beans.CarBean;

import java.util.List;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/11 0011
 */

public interface ParkApplyContract {
    interface Model {
        void getWorkersCar(String token);

        void getParkSpace(String token, int page, int size, String key);

        void parkApply(String token, ParkApplyInput input);

        void dealParkApply(String token, DealParkApplyInput input);
    }

    interface View {
        void showDialog();

        void dismissDialog();

        void error(String err);

        void getWorkersCarSuccess(List<CarBean> list);

        void getParkSpaceSuccess(ParkingResult s);

        void parkApplySuccess();

        void dealParkApplySuccess();
    }

    interface Presenter {
        void error(String err);

        void getParkSpaceSuccess(ParkingResult s);

        void getWorkersCarSuccess( List<CarBean> list);

        void parkApplySuccess();

        void dealParkApplySuccess();

        void getWorkersCar(String token);

        void getParkSpace(String token, int page, int size, String key);

        void parkApply(String token, ParkApplyInput input);

        void dealParkApply(String token, DealParkApplyInput input);
    }
}
