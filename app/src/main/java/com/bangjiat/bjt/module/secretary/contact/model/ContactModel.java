package com.bangjiat.bjt.module.secretary.contact.model;

import com.bangjiat.bjt.api.ApiFactory;
import com.bangjiat.bjt.api.MyCallBack;
import com.bangjiat.bjt.common.BaseResult;
import com.bangjiat.bjt.module.secretary.contact.beans.ContactBean;
import com.bangjiat.bjt.module.secretary.contact.contract.ContactContract;

import java.util.List;

import retrofit2.Response;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/4 0004
 */

public class ContactModel implements ContactContract.Model {
    private ContactContract.Presenter presenter;

    public ContactModel(ContactContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getAllContacts(String token, String key) {
        ApiFactory.getService().getAllAddressList(token, key).enqueue(new MyCallBack<BaseResult<List<ContactBean>>>() {
            @Override
            public void onSuc(Response<BaseResult<List<ContactBean>>> response) {
                BaseResult<List<ContactBean>> body = response.body();
                if (body.getStatus() == 200) {
                    presenter.success(body.getData());
                } else presenter.fail(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.fail(message);
            }
        });
    }
}
