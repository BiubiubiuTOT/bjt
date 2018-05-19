package com.bangjiat.bjt.module.secretary.service.presenter;

import com.bangjiat.bjt.module.secretary.door.beans.ApprovalServiceInput;
import com.bangjiat.bjt.module.secretary.service.beans.ServiceApplyHistoryResult;
import com.bangjiat.bjt.module.secretary.service.contract.ServiceApplyHistoryContract;
import com.bangjiat.bjt.module.secretary.service.model.ServiceApplyHistoryModel;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/9 0009
 */

public class ServiceApplyHistoryPresenter implements ServiceApplyHistoryContract.Presenter {
    private ServiceApplyHistoryContract.View view;
    private ServiceApplyHistoryContract.Model model;

    public ServiceApplyHistoryPresenter(ServiceApplyHistoryContract.View view) {
        this.view = view;
        model = new ServiceApplyHistoryModel(this);
    }

    @Override
    public void getHistory(String token, int page, int size) {
        view.showDialog();
        model.getHistory(token, page, size);
    }

    @Override
    public void success(ServiceApplyHistoryResult result) {
        view.dismissDialog();
        view.success(result);
    }

    @Override
    public void error(String err) {
        view.dismissDialog();
        view.error(err);
    }

    @Override
    public void approvalService(String token,int id, ApprovalServiceInput input) {
        view.showDialog();
        model.approvalService(token,id, input);
    }

    @Override
    public void approvalServiceSuccess() {
        view.dismissDialog();
        view.approvalServiceSuccess();
    }

    @Override
    public void getAdminHistory(String token, int id, int page, int size, int status) {
        view.showDialog();
        model.getAdminHistory(token, id, page, size, status);
    }

    @Override
    public void getAdminHistorySuccess(ServiceApplyHistoryResult result) {
        view.dismissDialog();
        view.getAdminHistorySuccess(result);
    }
}
