package com.bangjiat.bjt.module.home.visitor.model;

import com.bangjiat.bjt.api.ApiFactory;
import com.bangjiat.bjt.api.MyCallBack;
import com.bangjiat.bjt.common.BaseResult;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.module.home.visitor.contract.VisitorContract;
import com.orhanobut.logger.Logger;

import retrofit2.Response;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/4/16 0016
 */

public class VisitorModel implements VisitorContract.Model {
    private VisitorContract.Presenter presenter;

    public VisitorModel(VisitorContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getVisitorHistory(String token, int page, int size) {
        ApiFactory.getService().getVisitorHistory(token, page,  Constants.SIZE).enqueue(new MyCallBack<BaseResult>() {
            @Override
            public void onSuc(Response<BaseResult> response) {
                BaseResult body = response.body();
                if (body.getStatus() == 200) {
                    presenter.success();
                } else presenter.error(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.error(message);
                Logger.e(message);
            }
        });
    }
}
