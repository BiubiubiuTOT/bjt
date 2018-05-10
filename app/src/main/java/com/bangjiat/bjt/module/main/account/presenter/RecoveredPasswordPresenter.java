package com.bangjiat.bjt.module.main.account.presenter;

import com.bangjiat.bjt.module.main.account.beans.RecoveredPasswordInput;
import com.bangjiat.bjt.module.main.account.contract.RecoveredPasswordContract;
import com.bangjiat.bjt.module.main.account.model.RecoveredPasswordModel;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/10 0010
 */

public class RecoveredPasswordPresenter implements RecoveredPasswordContract.Presenter {
    private RecoveredPasswordContract.Model model;
    private RecoveredPasswordContract.View view;

    public RecoveredPasswordPresenter(RecoveredPasswordContract.View view) {
        this.view = view;
        model = new RecoveredPasswordModel(this);
    }

    @Override
    public void update(String phone, String ps, String ps2) {
        if (ps.isEmpty()) {
            error("请输入新密码");
            return;
        }
        if (ps.length() > 16 || ps.length() < 6) {
            view.error("密码长度必须为6-16位");
            return;
        }
        if (!ps.equals(ps2)) {
            error("两次输入新密码不一致");
            return;
        }
        view.showDialog();
        model.update(new RecoveredPasswordInput(phone, ps));
    }

    @Override
    public void error(String err) {
        view.dismissDialog();
        view.error(err);
    }

    @Override
    public void success() {
        view.success();
    }
}
