package com.bangjiat.bjt.module.home.work.kaoqin.model;

import com.bangjiat.bjt.api.ApiFactory;
import com.bangjiat.bjt.api.MyCallBack;
import com.bangjiat.bjt.common.BaseResult;
import com.bangjiat.bjt.module.home.work.kaoqin.beans.RuleInput;
import com.bangjiat.bjt.module.home.work.kaoqin.contract.RoleContract;

import retrofit2.Response;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/8 0008
 */

public class RoleModel implements RoleContract.Model {
    private RoleContract.Presenter presenter;

    public RoleModel(RoleContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void saveRole(String token, RuleInput input) {
        ApiFactory.getService().saveCompanyCLockRule(token, input).enqueue(new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                BaseResult<String> body = response.body();
                if (body.getStatus() != 200) {
                    presenter.error(body.getMessage());
                } else {
                    presenter.saveRoleSuccess();
                }
            }

            @Override
            public void onFail(String message) {
                presenter.error(message);
            }
        });

    }

    @Override
    public void updateRole(String token, RuleInput input) {
        ApiFactory.getService().updateCompanyCLockRule(token, input).enqueue(new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                BaseResult<String> body = response.body();
                if (body.getStatus() != 200) {
                    presenter.error(body.getMessage());
                } else presenter.updateSuccess();
            }

            @Override
            public void onFail(String message) {
                presenter.error(message);
            }
        });
    }

    @Override
    public void getRole(String token) {
        ApiFactory.getService().selectCompanyClockRule(token).enqueue(new MyCallBack<BaseResult<RuleInput>>() {
            @Override
            public void onSuc(Response<BaseResult<RuleInput>> response) {
                BaseResult<RuleInput> body = response.body();
                if (body.getStatus() != 200) {
                    presenter.error(body.getMessage());
                } else presenter.getRoleSuccess(body.getData());
            }

            @Override
            public void onFail(String message) {
                presenter.error(message);
            }
        });
    }
}
