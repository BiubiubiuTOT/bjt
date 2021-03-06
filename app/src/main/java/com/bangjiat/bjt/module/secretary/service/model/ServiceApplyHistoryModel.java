package com.bangjiat.bjt.module.secretary.service.model;

import com.bangjiat.bjt.api.ApiFactory;
import com.bangjiat.bjt.api.MyCallBack;
import com.bangjiat.bjt.common.BaseResult;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.module.secretary.door.beans.ApprovalServiceInput;
import com.bangjiat.bjt.module.secretary.service.beans.ServiceApplyHistoryResult;
import com.bangjiat.bjt.module.secretary.service.contract.ServiceApplyHistoryContract;

import retrofit2.Response;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/9 0009
 */

public class ServiceApplyHistoryModel implements ServiceApplyHistoryContract.Model {
    private ServiceApplyHistoryContract.Presenter presenter;

    public ServiceApplyHistoryModel(ServiceApplyHistoryContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getHistory(String token, int page) {
        ApiFactory.getService().getServiceApplyHistory(token, page, Constants.SIZE).enqueue(new MyCallBack<BaseResult<ServiceApplyHistoryResult>>() {
            @Override
            public void onSuc(Response<BaseResult<ServiceApplyHistoryResult>> response) {
                BaseResult<ServiceApplyHistoryResult> body = response.body();
                if (body.getStatus() == 200) {
                    presenter.success(body.getData());
                } else presenter.error(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.error(message);
            }
        });
    }

    @Override
    public void getAdminHistory(String token, int id, int page, int status) {
        ApiFactory.getService().getAdminServiceApplyHistory(token, id, page, Constants.SIZE, status).enqueue(new MyCallBack<BaseResult<ServiceApplyHistoryResult>>() {
            @Override
            public void onSuc(Response<BaseResult<ServiceApplyHistoryResult>> response) {
                BaseResult<ServiceApplyHistoryResult> body = response.body();
                if (body.getStatus() == 200) {
                    presenter.getAdminHistorySuccess(body.getData());
                } else presenter.error(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.error(message);
            }
        });
    }

    @Override
    public void approvalService(String token, int id, ApprovalServiceInput input) {
        ApiFactory.getService().approvalService(token, id, input).enqueue(new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                BaseResult<String> body = response.body();
                if (body.getStatus() == 200) {
                    presenter.approvalServiceSuccess();
                } else presenter.error(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.error(message);
            }
        });
    }
}
