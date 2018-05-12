package com.bangjiat.bjt.module.secretary.communication.presenter;

import com.bangjiat.bjt.module.secretary.communication.beans.EmailResult;
import com.bangjiat.bjt.module.secretary.communication.contract.BoxContract;
import com.bangjiat.bjt.module.secretary.communication.model.BoxModel;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/12 0012
 */

public class BoxPresenter implements BoxContract.Presenter {
    private BoxContract.View view;
    private BoxContract.Model model;

    public BoxPresenter(BoxContract.View view) {
        this.view = view;
        model = new BoxModel(this);
    }

    @Override
    public void getOutBoxList(String token, String key, int page, int size) {
        view.showDialog();
        model.getOutBoxList(token, key, page, size);
    }

    @Override
    public void getInBoxList(String token, String key, int page, int size) {
        view.showDialog();
        model.getInBoxList(token, key, page, size);
    }

    @Override
    public void success(EmailResult list) {
        view.dismissDialog();
        view.success(list);
    }

    @Override
    public void fail(String err) {
        view.dismissDialog();
        view.fail(err);
    }
}
