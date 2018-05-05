package com.bangjiat.bjt.module.secretary.contact.presenter;

import com.bangjiat.bjt.module.secretary.contact.beans.ContactBean;
import com.bangjiat.bjt.module.secretary.contact.contract.UpdateContactContract;
import com.bangjiat.bjt.module.secretary.contact.model.UpdateContactModel;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/5 0005
 */

public class UpdateContactPresenter implements UpdateContactContract.Presenter {
    private UpdateContactContract.View view;
    private UpdateContactContract.Model model;

    public UpdateContactPresenter(UpdateContactContract.View view) {
        this.view = view;
        model = new UpdateContactModel(this);
    }

    @Override
    public void updateContact(String token, ContactBean bean) {
        view.showDialog();
        model.updateContact(token, bean);
    }

    @Override
    public void deleteContact(String token, String addressListId) {
        view.showDialog();
        model.deleteContact(token, addressListId);
    }

    @Override
    public void showErr(String err) {
        view.dismissDialog();
        view.showErr(err);
    }

    @Override
    public void deleteSuccess() {
        view.dismissDialog();
        view.deleteSuccess();
    }

    @Override
    public void updateSuccess() {
        view.updateSuccess();
    }
}
