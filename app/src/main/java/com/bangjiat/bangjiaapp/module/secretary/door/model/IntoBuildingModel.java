package com.bangjiat.bangjiaapp.module.secretary.door.model;

import com.bangjiat.bangjiaapp.api.ApiFactory;
import com.bangjiat.bangjiaapp.api.MyCallBack;
import com.bangjiat.bangjiaapp.common.BaseResult;
import com.bangjiat.bangjiaapp.module.secretary.door.beans.IntoBuildingInput;
import com.bangjiat.bangjiaapp.module.secretary.door.contract.IntoBuildingContract;

import retrofit2.Response;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/4/21 0021
 */

public class IntoBuildingModel implements IntoBuildingContract.Model {
    private IntoBuildingContract.Presenter presenter;

    public IntoBuildingModel(IntoBuildingContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void intoBuilding(String token, IntoBuildingInput input) {
        ApiFactory.getService().intoBuilding(token, input).enqueue(new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                BaseResult<String> body = response.body();
                if (body.getStatus() == 200)
                    presenter.success();
                else presenter.fail(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.fail(message);
            }
        });
    }
}
