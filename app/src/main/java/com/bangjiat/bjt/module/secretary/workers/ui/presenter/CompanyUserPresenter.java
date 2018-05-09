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

    @Override
    public void deleteCompanyUser(String token, String userId) {
        view.showDialog();
        model.deleteCompanyUser(token, userId);
    }

    @Override
    public void updateCompanyUser(String token, WorkersResult.RecordsBean bean) {
        view.showDialog();
        model.updateCompanyUser(token, bean);
    }

    @Override
    public void addCompanyUser(String token, WorkersResult.RecordsBean bean) {
        view.showDialog();
        model.addCompanyUser(token, bean);
    }

    @Override
    public void deleteCompanyUserSuccess() {
        view.dismissDialog();
        view.deleteCompanyUserSuccess();
    }

    @Override
    public void updateCompanyUserSuccess() {
        view.dismissDialog();
        view.updateCompanyUserSuccess();
    }

    @Override
    public void addCompanyUserSuccess() {
view.dismissDialog();
view.addCompanyUserSuccess();
    }
}
