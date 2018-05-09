package com.bangjiat.bjt.module.home.visitor.presenter;

import com.bangjiat.bjt.module.home.visitor.contract.VisitorContract;
import com.bangjiat.bjt.module.home.visitor.model.VisitorModel;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/4/16 0016
 */

public class VisitorPresenter implements VisitorContract.Presenter {
    private VisitorContract.View view;
    private VisitorContract.Model model;

    public VisitorPresenter(VisitorContract.View view) {
        this.view = view;
        model = new VisitorModel(this);
    }

    @Override
    public void getVisitorHistory(String token, int page, int size) {
        view.showDialog();
        model.getVisitorHistory(token, page, size);
    }

    @Override
    public void error(String err) {
        view.dismissDialog();
        view.error(err);
    }

    @Override
    public void success() {
        view.dismissDialog();
        view.success();
    }
}
