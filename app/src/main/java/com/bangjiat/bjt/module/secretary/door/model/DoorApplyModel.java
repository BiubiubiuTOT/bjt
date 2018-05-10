package com.bangjiat.bjt.module.secretary.door.model;

import com.bangjiat.bjt.api.ApiFactory;
import com.bangjiat.bjt.api.MyCallBack;
import com.bangjiat.bjt.common.BaseResult;
import com.bangjiat.bjt.module.secretary.door.contract.DoorApplyContract;
import com.bangjiat.bjt.module.secretary.workers.beans.WorkersResult;

import retrofit2.Response;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/10 0010
 */

public class DoorApplyModel implements DoorApplyContract.Model {
    private DoorApplyContract.Presenter presenter;

    public DoorApplyModel(DoorApplyContract.Presenter presenter) {
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

    @Override
    public void addApply(String token, String[] strings) {
        ApiFactory.getService().addDoorApply(token, strings).enqueue(new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                BaseResult<String> body = response.body();
                if (body.getStatus() == 200)
                    presenter.addApplySuccess();
                else presenter.error(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.error(message);
            }
        });
    }
}
