package com.bangjiat.bjt.module.me.setting.model;

import com.bangjiat.bjt.api.ApiFactory;
import com.bangjiat.bjt.api.MyCallBack;
import com.bangjiat.bjt.common.BaseResult;
import com.bangjiat.bjt.module.me.setting.contract.ForgetPasswordContract;

import retrofit2.Response;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/3 0003
 */

public class ForgetPasswordModel implements ForgetPasswordContract.Model {
    private ForgetPasswordContract.Presenter presenter;

    public ForgetPasswordModel(ForgetPasswordContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getRegisterCode(final String phone, int type) {
        ApiFactory.getService().getRegisterCode(phone, type).enqueue(new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                BaseResult<String> body = response.body();
                if (body.getStatus() == 200) {
                    presenter.getCodeSuccess();
                } else presenter.getCodeFail(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.getCodeFail(message);
            }
        });
    }
}
