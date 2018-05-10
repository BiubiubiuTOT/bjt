package com.bangjiat.bjt.module.main.account.presenter;

import com.bangjiat.bjt.module.main.account.beans.ValidateCodeInput;
import com.bangjiat.bjt.module.main.account.contract.ValidateCodeContract;
import com.bangjiat.bjt.module.main.account.model.ValidateCodeModel;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/10 0010
 */

public class ValidateCodePresenter implements ValidateCodeContract.Presenter {
    private ValidateCodeContract.View view;
    private ValidateCodeContract.Model model;

    public ValidateCodePresenter(ValidateCodeContract.View view) {
        this.view = view;
        model = new ValidateCodeModel(this);
    }

    @Override
    public void getRegisterCode(String phone, int type) {
        if (phone.isEmpty()) {
            getCodeFail("请填写手机号");
            return;
        }

        view.showDialog();
        model.getRegisterCode(phone, type);
    }

    @Override
    public void validateCode(ValidateCodeInput input) {
        if (input.getUsername().isEmpty()) {
            validateFail("请填写手机号");
            return;
        }
        if (input.getCode().isEmpty()) {
            validateFail("请填写验证码");
            return;
        }

        view.showDialog();
        model.validateCode(input);
    }

    @Override
    public void getCodeSuccess(String code) {
        view.dismissDialog();
        view.getCodeSuccess(code);
    }

    @Override
    public void getCodeFail(String err) {
        view.dismissDialog();
        view.getCodeFail(err);
    }

    @Override
    public void validateSuccess() {
        view.dismissDialog();
        view.validateSuccess();
    }

    @Override
    public void validateFail(String err) {
        view.dismissDialog();
        view.validateFail(err);
    }
}
