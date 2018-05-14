package com.bangjiat.bjt.module.park.pay.presenter;

import com.bangjiat.bjt.module.park.pay.beans.ParkingDetail;
import com.bangjiat.bjt.module.park.pay.beans.PayBean;
import com.bangjiat.bjt.module.park.pay.beans.PayInput;
import com.bangjiat.bjt.module.park.pay.beans.PayListResult;
import com.bangjiat.bjt.module.park.pay.contract.PayContract;
import com.bangjiat.bjt.module.park.pay.model.PayModel;

import java.util.List;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/12 0012
 */

public class PayPresenter implements PayContract.Presenter {
    private PayContract.View view;
    private PayContract.Model model;

    public PayPresenter(PayContract.View view) {
        this.view = view;
        model = new PayModel(this);
    }


    @Override
    public void addPayInfo(String token, PayBean bean) {
        view.showDialog();
        model.addPayInfo(token, bean);
    }

    @Override
    public void pay(String token, PayInput input) {
        view.showDialog();
        model.pay(token, input);
    }

    @Override
    public void getPayList(String token) {
        view.showDialog();
        model.getPayList(token);
    }

    @Override
    public void getPayListSuccess(List<PayListResult> str) {
        view.dismissDialog();
        view.getPayListSuccess(str);
    }

    @Override
    public void paySuccess(String str) {
        view.dismissDialog();
        view.paySuccess(str);
    }

    @Override
    public void addPayInfoSuccess(String err) {
        view.dismissDialog();
        view.addPayInfoSuccess(err);
    }

    @Override
    public void getParkingDetail(String token, int spaceId) {
        view.showDialog();
        model.getParkingDetail(token, spaceId);
    }

    @Override
    public void getParkingDetailSuccess(ParkingDetail detail) {
        view.dismissDialog();
        view.getParkingDetailSuccess(detail);
    }

    @Override
    public void fail(String err) {
        view.dismissDialog();
        view.fail(err);
    }
}
