package com.bangjiat.bjt.module.home.company.presenter;

import com.bangjiat.bjt.module.home.company.beans.CompanyInput;
import com.bangjiat.bjt.module.home.company.contract.AddCompanyContract;
import com.bangjiat.bjt.module.home.company.model.AddCompanyModel;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/4/19 0019
 */

public class AddCompanyPresenter implements AddCompanyContract.Presenter {
    private AddCompanyContract.View view;
    private AddCompanyContract.Model model;

    public AddCompanyPresenter(AddCompanyContract.View view) {
        this.view = view;
        model = new AddCompanyModel(this);
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
}
