package com.bangjiat.bjt.module.park.car.contract;

import com.bangjiat.bjt.module.park.car.beans.CarBean;

import java.util.List;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/11 0011
 */

public interface CarListContract {
    interface Model {
        void getCarList(String token);

        void addCar(String token, CarBean bean);

        void deleteCar(String token, String[] arr);
    }

    interface View {
        void showDialog();

        void dismissDialog();

        void error(String err);

        void getCarListSuccess(List<CarBean> bean);

        void addCarSuccess();

        void deleteCarSuccess(String str);
    }

    interface Presenter {
        void getCarListSuccess(List<CarBean> bean);

        void addCarSuccess();

        void error(String err);

        void getCarList(String token);

        void addCar(String token, CarBean bean);

        void deleteCar(String token, String[] arr);

        void deleteCarSuccess(String str);

    }
}
