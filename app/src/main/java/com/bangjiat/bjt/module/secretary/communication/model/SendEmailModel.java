package com.bangjiat.bjt.module.secretary.communication.model;

import com.bangjiat.bjt.api.ApiFactory;
import com.bangjiat.bjt.api.MyCallBack;
import com.bangjiat.bjt.common.BaseResult;
import com.bangjiat.bjt.module.secretary.communication.beans.EmailBean;
import com.bangjiat.bjt.module.secretary.communication.contract.SendEmailContract;

import retrofit2.Response;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/12 0012
 */

public class SendEmailModel implements SendEmailContract.Model {
    private SendEmailContract.Presenter presenter;

    public SendEmailModel(SendEmailContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void sendEmail(String token, final EmailBean bean) {
        ApiFactory.getService().sendEmail(token, bean).enqueue(new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                BaseResult<String> body = response.body();
                if (body.getStatus() == 200)
                    presenter.success();
                else
                    presenter.fail(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.fail(message);
            }
        });

    }
}
