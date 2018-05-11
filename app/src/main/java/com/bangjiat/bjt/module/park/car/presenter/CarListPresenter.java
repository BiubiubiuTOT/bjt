package com.bangjiat.bjt.module.park.car.presenter;

import com.bangjiat.bjt.module.park.car.beans.CarBean;
import com.bangjiat.bjt.module.park.car.contract.CarListContract;
import com.bangjiat.bjt.module.park.car.model.CarListModel;

import java.util.List;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/11 0011
 */

public class CarListPresenter implements CarListContract.Presenter {
    private CarListContract.View view;
    private CarListContract.Model model;

    public CarListPresenter(CarListContract.View view) {
        this.view = view;
        model = new CarListModel(this);
    }

    @Override
    public void getCarListSuccess(List<CarBean> bean) {
        view.dismissDialog();
        view.getCarListSuccess(bean);
    }

    @Override
    public void addCarSuccess() {
        view.dismissDialog();
        view.addCarSuccess();
    }

    @Override
    public void error(String err) {
        view.dismissDialog();
        view.error(err);
    }

    @Override
    public void getCarList(String token) {
        view.showDialog();
        model.getCarList(token);
    }

    @Override
    public void addCar(String token, CarBean bean) {
        if (bean.getBrand().isEmpty()) {
            view.error("请填写车辆品牌");
            return;
        }
        if (bean.getColor().isEmpty()) {
            view.error("请填写车辆颜色");
            return;
        }
        if (bean.getDriveNumber().isEmpty()) {
            view.error("请填写行驶证号");
            return;
        }
        if (bean.getName().isEmpty()) {
            view.error("请填写车主姓名");
            return;
        }
        if (bean.getResource().isEmpty()) {
            view.error("请选择车辆照片");
            return;
        }
        if (bean.getIdNumber().isEmpty()) {
            view.error("请填写身份证号");
            return;
        }
        if (bean.getPlateNumber().isEmpty()) {
            view.error("请填写车牌号");
            return;
        }
        if (bean.getLicenceNumber().isEmpty()) {
            view.error("请填写驾驶证号");
            return;
        }
        if (bean.getModel().isEmpty()) {
            view.error("请填写车辆型号");
            return;
        }

        view.showDialog();
        model.addCar(token, bean);
    }
}
