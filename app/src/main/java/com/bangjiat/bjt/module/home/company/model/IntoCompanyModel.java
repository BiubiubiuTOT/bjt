package com.bangjiat.bjt.module.home.company.model;

import com.bangjiat.bjt.api.ApiFactory;
import com.bangjiat.bjt.api.MyCallBack;
import com.bangjiat.bjt.common.BaseResult;
import com.bangjiat.bjt.module.home.company.beans.CompanyDetailResult;
import com.bangjiat.bjt.module.home.company.beans.IntoCompanyInput;
import com.bangjiat.bjt.module.home.company.contract.IntoCompanyContract;
import com.orhanobut.logger.Logger;

import retrofit2.Response;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/5 0005
 */

public class IntoCompanyModel implements IntoCompanyContract.Model {
    private IntoCompanyContract.Presenter presenter;

    public IntoCompanyModel(IntoCompanyContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void intoCompany(String token, IntoCompanyInput input) {
        ApiFactory.getService().intoCompany(token, input).enqueue(new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                BaseResult<String> body = response.body();
                if (body.getStatus() == 200) {
                    presenter.success();
                    Logger.d(body.getData());
                } else {
                    presenter.fail(body.getMessage());
                    Logger.d(body.getMessage());
                }
            }

            @Override
            public void onFail(String message) {
                Logger.e(message);
                presenter.fail(message);
            }
        });
    }

    @Override
    public void getCompanyDetail(String token, String companyId) {
        ApiFactory.getService().getCompanyDetail(token, companyId).enqueue(new MyCallBack<BaseResult<CompanyDetailResult>>() {
            @Override
            public void onSuc(Response<BaseResult<CompanyDetailResult>> response) {
                BaseResult<CompanyDetailResult> body = response.body();
                if (body.getStatus() == 200)
                    presenter.getCompanyDetailSuccess(body.getData());
                else presenter.fail(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.fail(message);
            }
        });
    }
}
