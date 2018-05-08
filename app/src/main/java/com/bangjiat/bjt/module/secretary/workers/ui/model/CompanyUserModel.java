package com.bangjiat.bjt.module.secretary.workers.ui.model;

import com.bangjiat.bjt.api.ApiFactory;
import com.bangjiat.bjt.api.MyCallBack;
import com.bangjiat.bjt.common.BaseResult;
import com.bangjiat.bjt.module.secretary.workers.beans.WorkersResult;
import com.bangjiat.bjt.module.secretary.workers.ui.contract.CompanyUserContract;

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
        ApiFactory.getService().getCompanyUser(token, page, size, type).enqueue(new MyCallBack<BaseResult<WorkersResult>>() {
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
}
