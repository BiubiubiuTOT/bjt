package com.bangjiat.bangjiaapp.module.account.presenter;

import android.text.TextUtils;

import com.bangjiat.bangjiaapp.common.BaseResult;
import com.bangjiat.bangjiaapp.module.account.beans.RegisterInput;
import com.bangjiat.bangjiaapp.module.account.contract.RegisterContract;
import com.bangjiat.bangjiaapp.module.account.model.RegisterModel;

/**
 * Created by Administrator on 2018/4/14 0014.
 */

public class RegisterPresenter implements RegisterContract.Presenter {
    private RegisterContract.View view;
    private RegisterContract.Model model;

    public RegisterPresenter(RegisterContract.View view) {
        this.view = view;
        model = new RegisterModel(this);
    }

    @Override
    public void register(String phone, String password, String code) {
        if (TextUtils.isEmpty(phone)) {
            view.showError("请输入手机号");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            view.showError("密码为空");
            return;
        }
        if (TextUtils.isEmpty(code)) {
            view.showError("验证码不能为空");
            return;
        }
        view.showDialog();

        RegisterInput input = new RegisterInput(phone, phone, password, code);
        model.register(input);
    }

    @Override
    public void getRegisterCode(String input) {
        if (TextUtils.isEmpty(input)) {
            view.showError("手机号不能为空");
            return;
        }
        view.showDialog();
        model.getRegisterCode(input, 1);
    }

    @Override
    public void getCodeSuccess(BaseResult<String> result) {
        view.getCodeSuccess();
        view.dismissDialog();
    }

    @Override
    public void registerSuccess(BaseResult<String> result) {
        view.registerSuccess(result);
        view.dismissDialog();
    }

    @Override
    public void error(String err) {
        view.showError(err);
        view.dismissDialog();
    }
}
