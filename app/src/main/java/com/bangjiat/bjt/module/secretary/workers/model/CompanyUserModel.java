package com.bangjiat.bjt.module.secretary.workers.model;

import com.bangjiat.bjt.api.ApiFactory;
import com.bangjiat.bjt.api.MyCallBack;
import com.bangjiat.bjt.common.BaseResult;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.module.secretary.workers.beans.WorkersResult;
import com.bangjiat.bjt.module.secretary.workers.contract.CompanyUserContract;
import com.orhanobut.logger.Logger;

import retrofit2.Response;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/8 0008
 */

public class CompanyUserModel implements CompanyUserContract.Model {
    private CompanyUserContract.Presenter presenter;

    public CompanyUserModel(CompanyUserContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getCompanyUser(String token, int page, int size, int type) {
        ApiFactory.getService().getCompanyUser(token, page,  Constants.SIZE, type).enqueue(new MyCallBack<BaseResult<WorkersResult>>() {
            @Override
            public void onSuc(Response<BaseResult<WorkersResult>> response) {
                BaseResult<WorkersResult> body = response.body();
                if (body.getStatus() == 200)
                    presenter.getCompanyUserSuccess(body.getData());
                else
                    presenter.error(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.error(message);
            }
        });
    }

    @Override
    public void deleteCompanyUser(String token, String userId) {
        ApiFactory.getService().deleteCompanyUser(token, userId).enqueue(new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                BaseResult<String> body = response.body();
                if (body.getStatus() == 200)
                    presenter.deleteCompanyUserSuccess();
                else
                    presenter.error(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.error(message);
                Logger.d(message);
            }
        });
    }

    @Override
    public void updateCompanyUser(String token, final WorkersResult.RecordsBean bean) {
        ApiFactory.getService().updateCompanyUser(token, bean)
                .enqueue(new MyCallBack<BaseResult<String>>() {
                    @Override
                    public void onSuc(Response<BaseResult<String>> response) {
                        BaseResult<String> body = response.body();
                        if (body.getStatus() == 200)
                            presenter.updateCompanyUserSuccess();
                        else presenter.error(body.getMessage());
                    }

                    @Override
                    public void onFail(String message) {
                        presenter.error(message);
                    }
                });
    }

    @Override
    public void addCompanyUser(String token, WorkersResult.RecordsBean bean) {
        ApiFactory.getService().addCompanyUser(token, bean)
                .enqueue(new MyCallBack<BaseResult>() {
                    @Override
                    public void onSuc(Response<BaseResult> response) {
                        BaseResult body = response.body();
                        if (body.getStatus() == 200)
                            presenter.addCompanyUserSuccess();
                        else presenter.error(body.getMessage());
                    }

                    @Override
                    public void onFail(String message) {
                        presenter.error(message);
                    }
                });
    }
}
