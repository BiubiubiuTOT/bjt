package com.bangjiat.bjt.module.secretary.door.presenter;

import com.bangjiat.bjt.module.secretary.door.beans.DoorApplyDetailResult;
import com.bangjiat.bjt.module.secretary.door.contract.DoorApplyDetailContract;
import com.bangjiat.bjt.module.secretary.door.model.DoorApplyDetailModel;

import java.util.List;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/10 0010
 */

public class DoorApplyDetailPresenter implements DoorApplyDetailContract.Presenter {
    private DoorApplyDetailContract.View view;
    private DoorApplyDetailContract.Model model;

    public DoorApplyDetailPresenter(DoorApplyDetailContract.View view) {
        model = new DoorApplyDetailModel(this);
        this.view = view;
    }

    @Override
    public void getDetail(String token, String guardMainId) {
        view.showDialog();
        model.getDetail(token, guardMainId);
    }

    @Override
    public void success(List<DoorApplyDetailResult> results) {
        view.dismissDialog();
        view.success(results);
    }

    @Override
    public void error(String err) {
        view.dismissDialog();
        view.error(err);
    }
}
