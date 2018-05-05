package com.bangjiat.bjt.module.secretary.contact.model;

import com.bangjiat.bjt.api.ApiFactory;
import com.bangjiat.bjt.api.MyCallBack;
import com.bangjiat.bjt.common.BaseResult;
import com.bangjiat.bjt.module.secretary.contact.beans.SearchContactResult;
import com.bangjiat.bjt.module.secretary.contact.contract.SaveContactContract;

import retrofit2.Response;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/4 0004
 */

public class SaveContactModel implements SaveContactContract.Model {
    private SaveContactContract.Presenter presenter;

    public SaveContactModel(SaveContactContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void saveContact(String token, SearchContactResult input) {
        ApiFactory.getService().saveContact(token, input).enqueue(new MyCallBack<BaseResult<String>>() {
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
