package com.bangjiat.bjt.module.me.feedback.model;

import com.bangjiat.bjt.api.ApiFactory;
import com.bangjiat.bjt.api.MyCallBack;
import com.bangjiat.bjt.common.BaseResult;
import com.bangjiat.bjt.module.me.feedback.beans.FeedBackInput;
import com.bangjiat.bjt.module.me.feedback.contract.FeedBackContract;

import retrofit2.Response;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/3 0003
 */

public class FeedBackModel implements FeedBackContract.Model {
    private FeedBackContract.Presenter presenter;

    public FeedBackModel(FeedBackContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void saveFeedBack(String token, FeedBackInput input) {
        ApiFactory.getService().saveFeedBack(token, input).enqueue(new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                BaseResult<String> body = response.body();
                if (body.getStatus() == 200) {
                    presenter.success();
                } else presenter.fail(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.fail(message);
            }
        });
    }
}
