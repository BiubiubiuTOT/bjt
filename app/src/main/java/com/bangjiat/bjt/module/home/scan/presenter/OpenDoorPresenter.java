package com.bangjiat.bjt.module.home.scan.presenter;

import com.bangjiat.bjt.module.home.scan.contract.OpenDoorContract;
import com.bangjiat.bjt.module.home.scan.model.OpenDoorModel;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/23 0023
 */

public class OpenDoorPresenter implements OpenDoorContract.Presenter {
    private OpenDoorContract.View view;
    private OpenDoorContract.Model model;

    public OpenDoorPresenter(OpenDoorContract.View view) {
        this.view = view;
        model = new OpenDoorModel(this);
    }

    @Override
    public void error(String err) {
        view.dismissDialog();
        view.error(err);
    }

    @Override
    public void success(String str) {
        view.dismissDialog();
        view.success(str);
    }

    @Override
    public void getDoorMessage(String token) {
        view.showDialog();
        model.getDoorMessage(token);
    }
}
