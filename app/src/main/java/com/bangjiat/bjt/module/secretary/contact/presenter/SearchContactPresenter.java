package com.bangjiat.bjt.module.secretary.contact.presenter;

import com.bangjiat.bjt.module.secretary.contact.beans.SearchContactResult;
import com.bangjiat.bjt.module.secretary.contact.contract.SearchContactContract;
import com.bangjiat.bjt.module.secretary.contact.model.SearchContactModel;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/4 0004
 */

public class SearchContactPresenter implements SearchContactContract.Presenter {
    private SearchContactContract.View view;
    private SearchContactContract.Model model;

    public SearchContactPresenter(SearchContactContract.View view) {
        this.view = view;
        model = new SearchContactModel(this);
    }

    @Override
    public void searchContact(String token, String key) {
        model.searchContact(token, key);
    }

    @Override
    public void fail(String err) {
        view.fail(err);

    }

    @Override
    public void success(SearchContactResult bean) {
        view.success(bean);
    }
}
