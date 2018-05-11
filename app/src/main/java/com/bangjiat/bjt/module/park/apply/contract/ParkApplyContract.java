package com.bangjiat.bjt.module.park.apply.contract;

import com.bangjiat.bjt.module.park.apply.beans.DealParkApplyInput;
import com.bangjiat.bjt.module.park.apply.beans.ParkApplyInput;

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

        void error();

        void getWorkersCarSuccess();

        void getParkSpaceSuccess();

        void parkApplySuccess();

        void dealParkApplySuccess();
    }

    interface Presenter {
        void error();

        void getWorkersCarSuccess();

        void getParkSpaceSuccess();

        void parkApplySuccess();

        void dealParkApplySuccess();

        void getWorkersCar(String token);

        void getParkSpace(String token, int page, int size, String key);

        void parkApply(String token, ParkApplyInput input);

        void dealParkApply(String token, DealParkApplyInput input);
    }
}
