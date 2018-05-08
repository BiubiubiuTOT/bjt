package com.bangjiat.bjt.module.secretary.door.presenter;

import com.bangjiat.bjt.module.secretary.door.beans.ApplyHistoryBean;
import com.bangjiat.bjt.module.secretary.door.contract.DoorApplyHistoryContract;
import com.bangjiat.bjt.module.secretary.door.model.DoorApplyHistoryModel;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/8 0008
 */

public class DoorApplyHistoryPresenter implements DoorApplyHistoryContract.Presenter {
    private DoorApplyHistoryContract.View view;
    private DoorApplyHistoryContract.Model model;

    public DoorApplyHistoryPresenter(DoorApplyHistoryContract.View view) {
        this.view = view;
        model = new DoorApplyHistoryModel(this);
    }

    @Override
    public void getDoorApplyHistory(String token, int page, int size) {
        view.showDialog();
        model.getDoorApplyHistory(token, page, size);
    }

    @Override
    public void getDoorApplyHistorySuccess(ApplyHistoryBean bean) {
        view.dismissDialog();
        view.getDoorApplyHistorySuccess(bean);
    }

    @Override
    public void error(String err) {
        view.dismissDialog();
        view.error(err);
    }
}
