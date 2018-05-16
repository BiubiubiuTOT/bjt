package com.bangjiat.bjt.module.home.work.permission.model;

import com.bangjiat.bjt.api.ApiFactory;
import com.bangjiat.bjt.api.MyCallBack;
import com.bangjiat.bjt.common.BaseResult;
import com.bangjiat.bjt.module.home.work.permission.beans.UpdateAdminInput;
import com.bangjiat.bjt.module.home.work.permission.contract.PermissionContract;

import retrofit2.Response;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/16 0016
 */

public class PermissionModel implements PermissionContract.Model {
    private PermissionContract.Presenter presenter;

    public PermissionModel(PermissionContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void deleteAdmin(String token, String[] users) {
        ApiFactory.getService().deleteAdmin(token, users).enqueue(new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                BaseResult<String> body = response.body();
                if (body.getStatus() == 200)
                    presenter.deleteAdminSuccess();
                else presenter.error(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.error(message);
            }
        });
    }

    @Override
    public void addAdmin(String token, String[] users) {
        ApiFactory.getService().addAdmin(token, users).enqueue(new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                BaseResult<String> body = response.body();
                if (body.getStatus() == 200)
                    presenter.addAdminSuccess();
                else presenter.error(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.error(message);
            }
        });
    }

    @Override
    public void updateAdmin(String token, String userId) {
        ApiFactory.getService().updateAdmin(token, new UpdateAdminInput(userId)).enqueue(new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                BaseResult<String> body = response.body();
                if (body.getStatus() == 200)
                    presenter.updateAdminSuccess();
                else presenter.error(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.error(message);
            }
        });
    }
}
