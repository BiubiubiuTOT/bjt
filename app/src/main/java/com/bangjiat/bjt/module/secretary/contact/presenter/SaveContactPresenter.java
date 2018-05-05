package com.bangjiat.bjt.module.secretary.contact.presenter;

import com.bangjiat.bjt.module.secretary.contact.beans.SearchContactResult;
import com.bangjiat.bjt.module.secretary.contact.contract.SaveContactContract;
import com.bangjiat.bjt.module.secretary.contact.model.SaveContactModel;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/4 0004
 */

public class SaveContactPresenter implements SaveContactContract.Presenter {
    private SaveContactContract.View view;
    private SaveContactContract.Model model;

    public SaveContactPresenter(SaveContactContract.View view) {
        this.view = view;
        model = new SaveContactModel(this);
    }

    @Override
    public void saveContact(String token, SearchContactResult input) {
        model.saveContact(token, input);
    }

    @Override
    public void success() {
        view.dismissDialog();
        view.success();
    }

    @Override
    public void fail(String err) {
        view.dismissDialog();
        view.fail(err);
    }
}
