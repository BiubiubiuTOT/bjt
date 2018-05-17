package com.bangjiat.bjt.module.home.company.model;

import com.bangjiat.bjt.api.ApiFactory;
import com.bangjiat.bjt.api.MyCallBack;
import com.bangjiat.bjt.common.BaseResult;
import com.bangjiat.bjt.module.home.company.beans.CompanyDetailResult;
import com.bangjiat.bjt.module.home.company.beans.CompanyInput;
import com.bangjiat.bjt.module.home.company.contract.CompanyContract;

import retrofit2.Response;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/4/19 0019
 */

public class CompanyModel implements CompanyContract.Model {
    private CompanyContract.Presenter presenter;

    public CompanyModel(CompanyContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void addCompany(String token, CompanyInput input) {
        ApiFactory.getService().addCompany(token, input).enqueue(new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                BaseResult<String> body = response.body();
                if (body.getStatus() == 200) {
                    presenter.addCompanySuccess();
                } else presenter.addCompanyFail(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.addCompanyFail(message);
            }
        });
    }

    @Override
    public void exitCompany(String token) {
        ApiFactory.getService().exitCompany(token).enqueue(new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                BaseResult<String> body = response.body();
                if (body.getStatus() == 200) {
                    presenter.exitCompanySuccess(body.getData());
                } else presenter.error(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.error(message);
            }
        });
    }

    @Override
    public void updateCompany(String token, CompanyDetailResult result) {
        ApiFactory.getService().updateCpompany(token, result).enqueue(new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                BaseResult<String> body = response.body();
                if (body.getStatus() == 200)
                    presenter.updateCompanySuccess(body.getData());
                else presenter.error(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.error(message);
            }
        });
    }
}
