package com.bangjiat.bjt.module.me.setting.presenter;

import com.bangjiat.bjt.module.me.setting.contract.ForgetPasswordContract;
import com.bangjiat.bjt.module.me.setting.model.ForgetPasswordModel;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/3 0003
 */

public class ForgetPasswordPresenter implements ForgetPasswordContract.Presenter {
    private ForgetPasswordContract.View view;
    private ForgetPasswordContract.Model model;

    public ForgetPasswordPresenter(ForgetPasswordContract.View view) {
        this.view = view;
        model = new ForgetPasswordModel(this);
    }

    @Override
    public void getRegisterCode(String phone, int type) {
        model.getRegisterCode(phone, type);
        view.showDialog();
    }

    @Override
    public void getCodeFail(String err) {
        view.getCodeFail(err);
        view.dismissDialog();
    }

    @Override
    public void getCodeSuccess() {
        view.getCodeSuccess();
        view.dismissDialog();
    }
}
