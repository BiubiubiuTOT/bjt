package com.bangjiat.bjt.module.home.company.presenter;

import com.bangjiat.bjt.module.home.company.beans.CompanyDetailResult;
import com.bangjiat.bjt.module.home.company.beans.IntoCompanyInput;
import com.bangjiat.bjt.module.home.company.contract.IntoCompanyContract;
import com.bangjiat.bjt.module.home.company.model.IntoCompanyModel;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/5 0005
 */

public class IntoCompanyPresenter implements IntoCompanyContract.Presenter {
    private IntoCompanyContract.View view;
    private IntoCompanyContract.Model model;

    public IntoCompanyPresenter(IntoCompanyContract.View view) {
        this.view = view;
        model = new IntoCompanyModel(this);
    }

    @Override
    public void intoCompany(String token, IntoCompanyInput input) {
        view.showDialog();
        model.intoCompany(token, input);
    }

    @Override
    public void getCompanyDetailSuccess(CompanyDetailResult result) {
        view.dismissDialog();
        view.getCompanyDetailSuccess(result);
    }

    @Override
    public void getCompanyDetail(String token, String companyId) {
        view.showDialog();
        model.getCompanyDetail(token, companyId);
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
