package com.bangjiat.bjt.module.main.account.model;

import com.bangjiat.bjt.api.ApiFactory;
import com.bangjiat.bjt.api.MyCallBack;
import com.bangjiat.bjt.common.BaseResult;
import com.bangjiat.bjt.module.main.account.beans.ValidateCodeInput;
import com.bangjiat.bjt.module.main.account.contract.ValidateCodeContract;

import retrofit2.Response;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/10 0010
 */

public class ValidateCodeModel implements ValidateCodeContract.Model {
    private ValidateCodeContract.Presenter presenter;

    public ValidateCodeModel(ValidateCodeContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getRegisterCode(String phone, int type) {
        ApiFactory.getService().getRegisterCode(phone, type).enqueue(new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                BaseResult<String> body = response.body();
                if (body.getStatus() == 200)
                    presenter.getCodeSuccess(body.getData());
                else presenter.getCodeFail(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.getCodeFail(message);
            }
        });
    }

    @Override
    public void validateCode(ValidateCodeInput input) {
        ApiFactory.getService().validateCode(input).enqueue(new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                BaseResult<String> body = response.body();
                if (body.getStatus() == 200)
                    presenter.validateSuccess();
                else presenter.validateFail(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.validateFail(message);
            }
        });
    }
}
