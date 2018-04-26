package com.bangjiat.bjt.module.main.account.presenter;

import android.text.TextUtils;

import com.bangjiat.bjt.common.BaseResult;
import com.bangjiat.bjt.module.main.account.beans.LoginInput;
import com.bangjiat.bjt.module.main.account.contract.LoginContract;
import com.bangjiat.bjt.module.main.account.model.LoginModel;

/**
 * Created by Administrator on 2018/4/14 0014.
 */

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View view;
    private LoginContract.Model model;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        model = new LoginModel(this);
    }

    @Override
    public void login(String phone, String password) {
        if (TextUtils.isEmpty(phone)) {
            view.showError("请输入手机号");
            return;
        }
        if (password.isEmpty()) {
            view.showError("密码为空");
            return;
        }
        view.showDialog();

        LoginInput input = new LoginInput(phone, password);
        model.login(input);
    }

    @Override
    public void loginSuccess(BaseResult<String> result) {
        view.dismissDialog();
        view.loginSuccess(result);
    }

    @Override
    public void loginFail(String error) {
        view.dismissDialog();
        view.showError(error);
    }
}