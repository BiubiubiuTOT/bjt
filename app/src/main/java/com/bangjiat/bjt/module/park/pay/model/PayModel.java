package com.bangjiat.bjt.module.park.pay.model;

import com.bangjiat.bjt.api.ApiFactory;
import com.bangjiat.bjt.api.MyCallBack;
import com.bangjiat.bjt.common.BaseResult;
import com.bangjiat.bjt.module.park.pay.beans.PayBean;
import com.bangjiat.bjt.module.park.pay.beans.PayInput;
import com.bangjiat.bjt.module.park.pay.beans.PayListResult;
import com.bangjiat.bjt.module.park.pay.contract.PayContract;

import retrofit2.Response;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/12 0012
 */

public class PayModel implements PayContract.Model {
    private PayContract.Presenter presenter;

    public PayModel(PayContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void addPayInfo(final String token, final PayBean bean) {
        ApiFactory.getService().addPayInfo(token, bean).enqueue(new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                BaseResult<String> body = response.body();
                if (body.getStatus() == 200)
                    presenter.addPayInfoSuccess(body.getData());
                else presenter.fail(body.getMessage());

            }

            @Override
            public void onFail(String message) {
                presenter.fail(message);
            }
        });
    }

    @Override
    public void pay(String token, PayInput input) {
        ApiFactory.getService().pay(token, input).enqueue(new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                BaseResult<String> body = response.body();
                if (body.getStatus() == 200)
                    presenter.paySuccess(body.getData());
                else presenter.fail(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.fail(message);
            }
        });
    }

    @Override
    public void getPayList(String token) {
        ApiFactory.getService().getPayList(token).enqueue(new MyCallBack<BaseResult<PayListResult>>() {
            @Override
            public void onSuc(Response<BaseResult<PayListResult>> response) {
                BaseResult<PayListResult> body = response.body();
                if (body.getStatus() == 200) {
                    presenter.getPayListSuccess(body.getData());
                } else presenter.fail(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.fail(message);
            }
        });
    }
}
