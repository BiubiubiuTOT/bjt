package com.bangjiat.bjt.module.park.car.model;

import com.bangjiat.bjt.api.ApiFactory;
import com.bangjiat.bjt.api.MyCallBack;
import com.bangjiat.bjt.common.BaseResult;
import com.bangjiat.bjt.module.park.car.beans.CarBean;
import com.bangjiat.bjt.module.park.car.contract.CarListContract;

import java.util.List;

import retrofit2.Response;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/11 0011
 */

public class CarListModel implements CarListContract.Model {
    private CarListContract.Presenter presenter;

    public CarListModel(CarListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getCarList(String token) {
        ApiFactory.getService().getCarList(token).enqueue(new MyCallBack<BaseResult<List<CarBean>>>() {
            @Override
            public void onSuc(Response<BaseResult<List<CarBean>>> response) {
                BaseResult<List<CarBean>> body = response.body();
                if (body.getStatus() == 200) {
                    presenter.getCarListSuccess(body.getData());
                } else presenter.error(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.error(message);
            }
        });
    }

    @Override
    public void addCar(String token, final CarBean bean) {
        ApiFactory.getService().addCar(token, bean).enqueue(new MyCallBack<BaseResult>() {
            @Override
            public void onSuc(Response<BaseResult> response) {
                BaseResult body = response.body();
                if (body.getStatus() == 200) {
                    presenter.addCarSuccess();
                } else presenter.error(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.error(message);
            }
        });
    }
}
