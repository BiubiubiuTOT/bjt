package com.bangjiat.bangjiaapp.module.main.account.model;

import com.bangjiat.bangjiaapp.api.ApiFactory;
import com.bangjiat.bangjiaapp.api.MyCallBack;
import com.bangjiat.bangjiaapp.common.BaseResult;
import com.bangjiat.bangjiaapp.module.main.account.beans.RegisterInput;
import com.bangjiat.bangjiaapp.module.main.account.contract.RegisterContract;
import com.orhanobut.logger.Logger;

import retrofit2.Response;

/**
 * Created by Administrator on 2018/4/14 0014.
 */

public class RegisterModel implements RegisterContract.Model {
    private RegisterContract.Presenter presenter;

    public RegisterModel(RegisterContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void register(RegisterInput input) {
        ApiFactory.getService().register(input).enqueue(new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                BaseResult<String> body = response.body();
                if (body.getStatus() == 200) {
                    presenter.registerSuccess(body);
                    Logger.d(body.getData());
                } else presenter.error(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                Logger.e(message);
                presenter.error(message);
            }
        });
    }

    @Override
    public void getRegisterCode(String phone, int type) {
        ApiFactory.getService().getRegisterCode(phone, type).enqueue(new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                BaseResult<String> body = response.body();
                if (body.getStatus() == 200) {
                    presenter.getCodeSuccess(body);
                    Logger.d(body.getData());
                } else presenter.error(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                Logger.e(message);
                presenter.error(message);
            }
        });
    }
}
