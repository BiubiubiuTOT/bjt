package com.bangjiat.bjt.module.secretary.service.presenter;

import com.bangjiat.bjt.module.secretary.service.beans.NewApplyInput;
import com.bangjiat.bjt.module.secretary.service.contract.NewServiceApplyContract;
import com.bangjiat.bjt.module.secretary.service.model.NewServiceApplyModel;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/9 0009
 */

public class NewServiceApplyPresenter implements NewServiceApplyContract.Presenter {
    private NewServiceApplyContract.View view;
    private NewServiceApplyContract.Model model;

    public NewServiceApplyPresenter(NewServiceApplyContract.View view) {
        this.view = view;
        model = new NewServiceApplyModel(this);
    }

    @Override
    public void addNewApply(String token, NewApplyInput input) {
        if (input.getApplication().isEmpty()) {
            error("请填写申请事项");
            return;
        }
        if (input.getContent().isEmpty()) {
            error("请填写具体内容");
            return;
        }

        view.showDialog();
        model.addNewApply(token, input);
    }

    @Override
    public void error(String err) {
        view.dismissDialog();
        view.error(err);
    }

    @Override
    public void success() {
        view.dismissDialog();
        view.success();
    }

    @Override
    public void uploadImage(String photo) {
        view.showDialog();
        model.uploadImage(photo);
    }


    @Override
    public void uploadSuccess(String s) {
        view.dismissDialog();
        view.uploadSuccess(s);
    }

    @Override
    public void uploadFail(String err) {
        view.dismissDialog();
        view.uploadFail(err);
    }
}
