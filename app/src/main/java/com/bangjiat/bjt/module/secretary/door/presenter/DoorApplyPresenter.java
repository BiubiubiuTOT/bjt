package com.bangjiat.bjt.module.secretary.door.presenter;

import com.bangjiat.bjt.module.secretary.door.contract.DoorApplyContract;
import com.bangjiat.bjt.module.secretary.door.model.DoorApplyModel;
import com.bangjiat.bjt.module.secretary.workers.beans.WorkersResult;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/10 0010
 */

public class DoorApplyPresenter implements DoorApplyContract.Presenter {
    private DoorApplyContract.View view;
    private DoorApplyContract.Model model;

    public DoorApplyPresenter(DoorApplyContract.View view) {
        this.view = view;
        model = new DoorApplyModel(this);
    }

    @Override
    public void getCompanyUserSuccess(WorkersResult result) {
        view.dismissDialog();
        view.getCompanyUserSuccess(result);
    }

    @Override
    public void getCompanyUser(String token, int page, int size, int type) {
        view.showDialog();
        model.getCompanyUser(token, page, size, type);
    }

    @Override
    public void addApply(String token, String[] strings) {
        view.showDialog();
        model.addApply(token, strings);
    }

    @Override
    public void error(String err) {
        view.dismissDialog();
        view.error(err);
    }

    @Override
    public void addApplySuccess() {
        view.dismissDialog();
        view.addApplySuccess();
    }
}
