package com.bangjiat.bjt.module.secretary.communication.model;

import com.bangjiat.bjt.api.ApiFactory;
import com.bangjiat.bjt.api.MyCallBack;
import com.bangjiat.bjt.common.BaseResult;
import com.bangjiat.bjt.module.secretary.communication.contract.DealBoxContract;
import com.orhanobut.logger.Logger;

import retrofit2.Response;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/12 0012
 */

public class DealBoxModel implements DealBoxContract.Model {
    private DealBoxContract.Presenter presenter;

    public DealBoxModel(DealBoxContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void deleteOutBox(String token, String[] strings) {
        Logger.d(strings);
        ApiFactory.getService().deleteOutBox(token, strings).enqueue(new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                BaseResult<String> body = response.body();
                if (body.getStatus() == 200)
                    presenter.deleteOutBoxSuccess();
                else presenter.fail(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.fail(message);
            }
        });
    }

    @Override
    public void deleteInBox(String token, String[] strings) {
        ApiFactory.getService().deleteInbox(token, strings).enqueue(new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                BaseResult<String> body = response.body();
                if (body.getStatus() == 200)
                    presenter.deleteInBoxSuccess();
                else presenter.fail(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.fail(message);
            }
        });
    }

    @Override
    public void markBox(String token, String[] strings) {
        ApiFactory.getService().markEmails(token, strings).enqueue(new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                BaseResult<String> body = response.body();
                if (body.getStatus() == 200)
                    presenter.markBoxSuccess();
                else presenter.fail(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.fail(message);
            }
        });
    }

    @Override
    public void getUnReadCounts(String token) {
        ApiFactory.getService().getUnReadCount(token).enqueue(new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                BaseResult<String> body = response.body();
                if (body.getStatus() == 200)
                    presenter.getUnReadCountsSuccess(body.getData());
                else presenter.fail(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.fail(message);
            }
        });
    }
}
