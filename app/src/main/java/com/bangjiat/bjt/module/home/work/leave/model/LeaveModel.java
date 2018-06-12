package com.bangjiat.bjt.module.home.work.leave.model;

import com.bangjiat.bjt.api.ApiFactory;
import com.bangjiat.bjt.api.MyCallBack;
import com.bangjiat.bjt.common.BaseResult;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.module.home.work.leave.beans.CompanyLeaveResult;
import com.bangjiat.bjt.module.home.work.leave.beans.DealLeaveInput;
import com.bangjiat.bjt.module.home.work.leave.beans.LeaveBean;
import com.bangjiat.bjt.module.home.work.leave.contract.LeaveContract;

import retrofit2.Response;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/15 0015
 */

public class LeaveModel implements LeaveContract.Model {
    private LeaveContract.Presenter presenter;

    public LeaveModel(LeaveContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void addLeave(String token, LeaveBean bean) {
        ApiFactory.getService().addCompanyLeave(token, bean).enqueue(new MyCallBack<BaseResult>() {
            @Override
            public void onSuc(Response<BaseResult> response) {
                BaseResult body = response.body();
                if (body.getStatus() == 200)
                    presenter.addLeaveSuccess();
                else presenter.error(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.error(message);
            }
        });
    }

    @Override
    public void getCompanyLeave(String token, int status, int page, int size) {
        ApiFactory.getService().getCompanyLeave(token, status, page, Constants.SIZE).enqueue(new MyCallBack<BaseResult<CompanyLeaveResult>>() {
            @Override
            public void onSuc(Response<BaseResult<CompanyLeaveResult>> response) {
                BaseResult<CompanyLeaveResult> body = response.body();
                if (body.getStatus() == 200)
                    presenter.getCompanyLeaveSuccess(body.getData());
                else presenter.error(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.error(message);
            }
        });
    }

    @Override
    public void getSelfLeave(String token, int status, int page, int size) {
        ApiFactory.getService().getSelefLeve(token, page, Constants.SIZE).enqueue(new MyCallBack<BaseResult<CompanyLeaveResult>>() {
            @Override
            public void onSuc(Response<BaseResult<CompanyLeaveResult>> response) {
                BaseResult<CompanyLeaveResult> body = response.body();
                if (body.getStatus() == 200)
                    presenter.getSelfLeaveSuccess(body.getData());
                else presenter.error(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.error(message);
            }
        });
    }

    @Override
    public void dealLeave(String token, DealLeaveInput input) {
        ApiFactory.getService().dealLeave(token, input).enqueue(new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                BaseResult<String> body = response.body();
                if (body.getStatus() == 200)
                    presenter.dealLeaveSuccess();
                else presenter.error(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.error(message);
            }
        });
    }
}
