package com.bangjiat.bjt.module.secretary.door.presenter;

import com.bangjiat.bjt.module.secretary.door.beans.IntoBuildingInput;
import com.bangjiat.bjt.module.secretary.door.beans.IsIntoBuildingResult;
import com.bangjiat.bjt.module.secretary.door.contract.IntoBuildingContract;
import com.bangjiat.bjt.module.secretary.door.model.IntoBuildingModel;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/4/21 0021
 */

public class IntoBuildingPresenter implements IntoBuildingContract.Presenter {
    private IntoBuildingContract.View view;
    private IntoBuildingContract.Model model;

    public IntoBuildingPresenter(IntoBuildingContract.View view) {
        this.view = view;
        model = new IntoBuildingModel(this);
    }

    @Override
    public void intoBuilding(String token, IntoBuildingInput input) {
        model.intoBuilding(token, input);
    }

    @Override
    public void getIsIntoBuildingSuccess(IsIntoBuildingResult result) {
        view.dismissDialog();
        view.getIsIntoBuildingSuccess(result);
    }

    @Override
    public void isIntoBuilding(String token) {
        view.showDialog();
        model.isIntoBuilding(token);
    }

    @Override
    public void success() {
        view.dismissDialog();
        view.success();
    }

    @Override
    public void fail(String err) {
        view.dismissDialog();
        view.fail(err);
    }
}
