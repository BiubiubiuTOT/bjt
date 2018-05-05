package com.bangjiat.bjt.module.main.account.model;

import com.bangjiat.bjt.api.ApiFactory;
import com.bangjiat.bjt.api.MyCallBack;
import com.bangjiat.bjt.common.BaseResult;
import com.bangjiat.bjt.module.main.account.beans.LoginInput;
import com.bangjiat.bjt.module.main.account.contract.LoginContract;
import com.orhanobut.logger.Logger;

import retrofit2.Response;

/**
 * Created by Administrator on 2018/4/14 0014.
 */

public class LoginModel implements LoginContract.Model {
    private LoginContract.Presenter presenter;

    public LoginModel(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void login(LoginInput input) {
        ApiFactory.getService().login(input).enqueue(new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                BaseResult<String> body = response.body();
                if (body.getStatus() == 200) {
                    presenter.loginSuccess(body);
                } else presenter.loginFail(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.loginFail("网络出错");
                Logger.e(message);
            }
        });
    }
}
