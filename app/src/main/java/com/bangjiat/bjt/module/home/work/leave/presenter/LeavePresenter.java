package com.bangjiat.bjt.module.home.work.leave.presenter;

import com.bangjiat.bjt.module.home.work.leave.beans.CompanyLeaveResult;
import com.bangjiat.bjt.module.home.work.leave.beans.DealLeaveInput;
import com.bangjiat.bjt.module.home.work.leave.beans.LeaveBean;
import com.bangjiat.bjt.module.home.work.leave.contract.LeaveContract;
import com.bangjiat.bjt.module.home.work.leave.model.LeaveModel;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/15 0015
 */

public class LeavePresenter implements LeaveContract.Presenter {
    private LeaveContract.Model model;
    private LeaveContract.View view;

    public LeavePresenter(LeaveContract.View view) {
        this.view = view;
        model = new LeaveModel(this);
    }

    @Override
    public void addLeave(String token, LeaveBean bean) {
        view.showDialog();
        model.addLeave(token, bean);
    }

    @Override
    public void getCompanyLeave(String token, int status, int page, int size) {
        view.showDialog();
        model.getCompanyLeave(token, status, page, size);
    }

    @Override
    public void getSelfLeave(String token, int status, int page, int size) {
        view.showDialog();
        model.getSelfLeave(token, status, page, size);
    }

    @Override
    public void dealLeave(String token, DealLeaveInput input) {
        view.showDialog();
        model.dealLeave(token, input);
    }

    @Override
    public void error(String err) {
        view.dismissDialog();
        view.error(err);
    }

    @Override
    public void addLeaveSuccess() {
        view.dismissDialog();
        view.addLeaveSuccess();
    }

    @Override
    public void getCompanyLeaveSuccess(CompanyLeaveResult result) {
        view.dismissDialog();
        view.getCompanyLeaveSuccess(result);
    }

    @Override
    public void getSelfLeaveSuccess(CompanyLeaveResult result) {
        view.dismissDialog();
        view.getSelfLeaveSuccess(result);
    }

    @Override
    public void dealLeaveSuccess() {
        view.dismissDialog();
        view.dealLeaveSuccess();
    }
}
