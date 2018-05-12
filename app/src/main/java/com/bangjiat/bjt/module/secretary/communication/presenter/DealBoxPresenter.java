package com.bangjiat.bjt.module.secretary.communication.presenter;

import com.bangjiat.bjt.module.secretary.communication.contract.DealBoxContract;
import com.bangjiat.bjt.module.secretary.communication.model.DealBoxModel;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/12 0012
 */

public class DealBoxPresenter implements DealBoxContract.Presenter {
    private DealBoxContract.Model model;
    private DealBoxContract.View view;

    public DealBoxPresenter(DealBoxContract.View view) {
        this.view = view;
        model = new DealBoxModel(this);
    }

    @Override
    public void fail(String err) {
        view.dismissDialog();
        view.fail(err);
    }

    @Override
    public void deleteOutBoxSuccess() {
        view.dismissDialog();
        view.deleteOutBoxSuccess();
    }

    @Override
    public void deleteInBoxSuccess() {
        view.dismissDialog();
        view.deleteInBoxSuccess();
    }

    @Override
    public void markBoxSuccess() {
        view.dismissDialog();
        view.markBoxSuccess();
    }

    @Override
    public void deleteOutBox(String token, String[] strings) {
        view.showDialog();
        model.deleteOutBox(token, strings);
    }

    @Override
    public void deleteInBox(String token, String[] strings) {
        view.showDialog();
        model.deleteInBox(token, strings);
    }

    @Override
    public void markBox(String token, String[] strings) {
        view.showDialog();
        model.markBox(token, strings);
    }

    @Override
    public void getUnReadCounts(String token) {
        view.showDialog();
        model.getUnReadCounts(token);
    }

    @Override
    public void getUnReadCountsSuccess(String s) {
        view.dismissDialog();
        view.getUnReadCountsSuccess(s);
    }
}
