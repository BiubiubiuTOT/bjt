package com.bangjiat.bjt.module.main.account.model;

import com.bangjiat.bjt.api.ApiFactory;
import com.bangjiat.bjt.api.MyCallBack;
import com.bangjiat.bjt.common.BaseResult;
import com.bangjiat.bjt.module.main.account.beans.RecoveredPasswordInput;
import com.bangjiat.bjt.module.main.account.contract.RecoveredPasswordContract;

import retrofit2.Response;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/10 0010
 */

public class RecoveredPasswordModel implements RecoveredPasswordContract.Model {
    private RecoveredPasswordContract.Presenter presenter;

    public RecoveredPasswordModel(RecoveredPasswordContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void update(RecoveredPasswordInput input) {
        ApiFactory.getService().updatePassword(input).enqueue(new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                BaseResult<String> body = response.body();
                if (body.getStatus() == 200) {
                    presenter.success();
                } else presenter.error(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.error(message);
            }
        });
    }
}
