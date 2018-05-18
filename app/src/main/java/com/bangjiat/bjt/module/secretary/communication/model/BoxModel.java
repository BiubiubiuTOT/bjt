package com.bangjiat.bjt.module.secretary.communication.model;

import com.bangjiat.bjt.api.ApiFactory;
import com.bangjiat.bjt.api.MyCallBack;
import com.bangjiat.bjt.common.BaseResult;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.module.secretary.communication.beans.EmailResult;
import com.bangjiat.bjt.module.secretary.communication.contract.BoxContract;

import retrofit2.Response;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/12 0012
 */

public class BoxModel implements BoxContract.Model {
    private BoxContract.Presenter presenter;

    public BoxModel(BoxContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getOutBoxList(String token, String key, int page, int size) {
        ApiFactory.getService().getOutBoxList(token, key, page,  Constants.SIZE).enqueue(new MyCallBack<BaseResult<EmailResult>>() {
            @Override
            public void onSuc(Response<BaseResult<EmailResult>> response) {
                BaseResult<EmailResult> body = response.body();
                if (body.getStatus() == 200)
                    presenter.success(body.getData());
                else presenter.fail(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.fail(message);
            }
        });
    }

    @Override
    public void getInBoxList(String token, String key, int page, int size) {
        ApiFactory.getService().getInBoxList(token, key, page,  Constants.SIZE).enqueue(new MyCallBack<BaseResult<EmailResult>>() {
            @Override
            public void onSuc(Response<BaseResult<EmailResult>> response) {
                BaseResult<EmailResult> body = response.body();
                if (body.getStatus() == 200)
                    presenter.success(body.getData());
                else presenter.fail(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.fail(message);
            }
        });
    }
}
