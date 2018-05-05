package com.bangjiat.bjt.module.secretary.contact.presenter;

import com.bangjiat.bjt.module.secretary.contact.beans.ContactBean;
import com.bangjiat.bjt.module.secretary.contact.contract.ContactContract;
import com.bangjiat.bjt.module.secretary.contact.model.ContactModel;

import java.util.List;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/4 0004
 */

public class ContactPresenter implements ContactContract.Presenter {
    private ContactContract.View view;
    private ContactContract.Model model;

    public ContactPresenter(ContactContract.View view) {
        this.view = view;
        model = new ContactModel(this);
    }

    @Override
    public void fail(String err) {
        view.dismissDialog();
        view.fail(err);
    }

    @Override
    public void success(List<ContactBean> bean) {
        view.dismissDialog();
        view.success(bean);
    }

    @Override
    public void getAllContacts(String token, String key) {
        view.showDialog();
        model.getAllContacts(token, key);
    }
}
