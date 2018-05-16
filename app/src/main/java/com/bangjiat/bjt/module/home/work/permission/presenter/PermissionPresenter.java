package com.bangjiat.bjt.module.home.work.permission.presenter;

import com.bangjiat.bjt.module.home.work.permission.contract.PermissionContract;
import com.bangjiat.bjt.module.home.work.permission.model.PermissionModel;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/16 0016
 */

public class PermissionPresenter implements PermissionContract.Presenter {
    private PermissionContract.Model model;
    private PermissionContract.View view;

    public PermissionPresenter(PermissionContract.View view) {
        this.view = view;
        model = new PermissionModel(this);
    }

    @Override
    public void deleteAdmin(String token, String[] users) {
        view.showDialog();
        model.deleteAdmin(token, users);
    }

    @Override
    public void addAdmin(String token, String[] users) {
        view.showDialog();
        model.addAdmin(token, users);
    }

    @Override
    public void updateAdmin(String token, String userId) {
        view.showDialog();
        model.updateAdmin(token, userId);
    }

    @Override
    public void error(String err) {
        view.dismissDialog();
        view.error(err);
    }

    @Override
    public void deleteAdminSuccess() {
        view.dismissDialog();
        view.deleteAdminSuccess();
    }

    @Override
    public void addAdminSuccess() {
        view.dismissDialog();
        view.addAdminSuccess();
    }

    @Override
    public void updateAdminSuccess() {
        view.dismissDialog();
        view.updateAdminSuccess();
    }
}
