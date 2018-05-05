package com.bangjiat.bjt.module.me.setting.model;

import com.bangjiat.bjt.api.ApiFactory;
import com.bangjiat.bjt.api.MyCallBack;
import com.bangjiat.bjt.common.BaseResult;
import com.bangjiat.bjt.module.me.setting.beans.UpdatePasswordInput;
import com.bangjiat.bjt.module.me.setting.contract.UpdatePasswordContract;

import retrofit2.Response;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/3 0003
 */

public class UpdatePasswordModel implements UpdatePasswordContract.Model {
    private UpdatePasswordContract.Presenter presenter;

    public UpdatePasswordModel(UpdatePasswordContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void updatePassword(String token, UpdatePasswordInput input) {
        ApiFactory.getService().updatePassword(token, input).enqueue(new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                BaseResult<String> body = response.body();
                if (body.getStatus() == 200) {
                    presenter.success();
                } else presenter.fail(response.body().getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.fail(message);
            }
        });
    }
}
