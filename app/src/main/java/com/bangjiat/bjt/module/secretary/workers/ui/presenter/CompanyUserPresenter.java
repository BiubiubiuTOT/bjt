package com.bangjiat.bjt.module.secretary.workers.ui.presenter;

import com.bangjiat.bjt.module.secretary.workers.beans.WorkersResult;
import com.bangjiat.bjt.module.secretary.workers.ui.contract.CompanyUserContract;
import com.bangjiat.bjt.module.secretary.workers.ui.model.CompanyUserModel;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/8 0008
 */

public class CompanyUserPresenter implements CompanyUserContract.Presenter {
    private CompanyUserContract.View view;
    private CompanyUserContract.Model model;

    public CompanyUserPresenter(CompanyUserContract.View view) {
        this.view = view;
        model = new CompanyUserModel(this);
    }

    @Override
    public void error(String err) {
        view.dismissDialog();
        view.error(err);
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
}
