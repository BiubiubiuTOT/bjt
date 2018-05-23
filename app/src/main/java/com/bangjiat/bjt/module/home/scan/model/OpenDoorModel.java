package com.bangjiat.bjt.module.home.scan.model;

import com.bangjiat.bjt.api.ApiFactory;
import com.bangjiat.bjt.api.MyCallBack;
import com.bangjiat.bjt.common.BaseResult;
import com.bangjiat.bjt.module.home.scan.contract.OpenDoorContract;

import retrofit2.Response;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/23 0023
 */

public class OpenDoorModel implements OpenDoorContract.Model {
    private OpenDoorContract.Presenter presenter;

    public OpenDoorModel(OpenDoorContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getDoorMessage(String token) {
        ApiFactory.getService().getDoorMessage(token).enqueue(new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                BaseResult<String> body = response.body();
                if (body.getStatus() == 200)
                    presenter.success(body.getData());
                else presenter.error(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.error(message);
            }
        });
    }
}
