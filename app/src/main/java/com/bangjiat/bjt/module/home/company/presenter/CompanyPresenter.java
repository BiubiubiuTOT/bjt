package com.bangjiat.bjt.module.home.company.presenter;

import com.bangjiat.bjt.module.home.company.beans.CompanyDetailResult;
import com.bangjiat.bjt.module.home.company.beans.CompanyInput;
import com.bangjiat.bjt.module.home.company.beans.DeleteCompanyInput;
import com.bangjiat.bjt.module.home.company.contract.CompanyContract;
import com.bangjiat.bjt.module.home.company.model.CompanyModel;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/4/19 0019
 */

public class CompanyPresenter implements CompanyContract.Presenter {
    private CompanyContract.View view;
    private CompanyContract.Model model;

    public CompanyPresenter(CompanyContract.View view) {
        this.view = view;
        model = new CompanyModel(this);
    }

    @Override
    public void addCompany(String token, CompanyInput input) {
        if (input.getName().isEmpty()) {
            view.addCompanyFail("公司名称不能为空");
            return;
        }
        if (input.getAddress().isEmpty()) {
            view.addCompanyFail("地址不能为空");
            return;
        }
        view.showDialog();
        model.addCompany(token, input);
    }

    @Override
    public void addCompanySuccess() {
        view.dismissDialog();
        view.addCompanySuccess();
    }

    @Override
    public void addCompanyFail(String err) {
        view.dismissDialog();
        view.addCompanyFail(err);
    }

    @Override
    public void exitCompany(String token) {
        view.showDialog();
        model.exitCompany(token);
    }

    @Override
    public void updateCompanySuccess(String str) {
        view.dismissDialog();
        view.updateCompanySuccess(str);
    }

    @Override
    public void exitCompanySuccess(String str) {
        view.dismissDialog();
        view.exitCompanySuccess(str);
    }

    @Override
    public void updateCompany(String token, CompanyDetailResult result) {
        view.showDialog();
        model.updateCompany(token, result);
    }

    @Override
    public void error(String err) {
        view.dismissDialog();
        view.error(err);
    }

    @Override
    public void deleteCompany(String token, DeleteCompanyInput input) {
        view.showDialog();
        model.deleteCompany(token, input);
    }

    @Override
    public void deleteCompanySuccess() {
        view.dismissDialog();
        view.deleteCompanySuccess();
    }
}
