package com.bangjiat.bjt.module.me.setting.presenter;

import com.bangjiat.bjt.module.me.setting.beans.UpdatePasswordInput;
import com.bangjiat.bjt.module.me.setting.contract.UpdatePasswordContract;
import com.bangjiat.bjt.module.me.setting.model.UpdatePasswordModel;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/3 0003
 */

public class UpdatePasswordPresenter implements UpdatePasswordContract.Presenter {
    private UpdatePasswordContract.View view;
    private UpdatePasswordContract.Model model;

    public UpdatePasswordPresenter(UpdatePasswordContract.View view) {
        this.view = view;
        model = new UpdatePasswordModel(this);
    }

    @Override
    public void updatePassword(String token, String old, String ne, String ne2, String userId) {
        if (old.isEmpty() || ne.isEmpty() || ne2.isEmpty()) {
            view.showErr("密码长度必须为6-16位");
            return;
        }
        if (old.length() > 16 || old.length() < 6) {
            view.showErr("密码长度必须为6-16位");
            return;
        }
        if (!ne.equals(ne2)) {
            view.showErr("新密码两次输入的不一致");
            return;
        }
        if (ne.length() > 16 || ne.length() < 6) {
            view.showErr("密码长度必须为6-16位");
            return;
        }
        view.showDialog();

        UpdatePasswordInput input = new UpdatePasswordInput(old, ne, userId);
        model.updatePassword(token, input);
    }

    @Override
    public void success() {
        view.dismissDialog();
        view.success();
    }

    @Override
    public void fail(String err) {
        view.dismissDialog();
        view.showErr(err);
    }
}
