package com.bangjiat.bjt.module.secretary.contact.model;

import com.bangjiat.bjt.api.ApiFactory;
import com.bangjiat.bjt.api.MyCallBack;
import com.bangjiat.bjt.common.BaseResult;
import com.bangjiat.bjt.module.secretary.contact.beans.ContactBean;
import com.bangjiat.bjt.module.secretary.contact.contract.UpdateContactContract;

import retrofit2.Response;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/5 0005
 */

public class UpdateContactModel implements UpdateContactContract.Model {
    private UpdateContactContract.Presenter presenter;

    public UpdateContactModel(UpdateContactContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void updateContact(String token, ContactBean bean) {
        ApiFactory.getService().updateContact(token, bean).enqueue(new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                BaseResult<String> body = response.body();
                if (body.getStatus() == 200)
                    presenter.updateSuccess();
                else presenter.showErr(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.showErr(message);
            }
        });
    }

    @Override
    public void deleteContact(String token, String addressListId) {
        ApiFactory.getService().delelteContact(token, addressListId).enqueue(new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                BaseResult<String> body = response.body();
                if (body.getStatus() == 200)
                    presenter.deleteSuccess();
                else presenter.showErr(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.showErr(message);
            }
        });

    }
}
