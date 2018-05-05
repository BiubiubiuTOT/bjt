package com.bangjiat.bjt.module.secretary.contact.model;

import com.bangjiat.bjt.api.ApiFactory;
import com.bangjiat.bjt.api.MyCallBack;
import com.bangjiat.bjt.common.BaseResult;
import com.bangjiat.bjt.module.secretary.contact.beans.SearchContactResult;
import com.bangjiat.bjt.module.secretary.contact.contract.SearchContactContract;

import retrofit2.Response;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/4 0004
 */

public class SearchContactModel implements SearchContactContract.Model {
    private SearchContactContract.Presenter presenter;

    public SearchContactModel(SearchContactContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void searchContact(String token, String key) {
        ApiFactory.getService().searchContact(token, key).enqueue(new MyCallBack<BaseResult<SearchContactResult>>() {
            @Override
            public void onSuc(Response<BaseResult<SearchContactResult>> response) {
                BaseResult<SearchContactResult> body = response.body();
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
