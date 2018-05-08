package com.bangjiat.bjt.module.home.work.kaoqin.presenter;

import com.bangjiat.bjt.module.home.work.kaoqin.beans.RuleInput;
import com.bangjiat.bjt.module.home.work.kaoqin.contract.RoleContract;
import com.bangjiat.bjt.module.home.work.kaoqin.model.RoleModel;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/8 0008
 */

public class RolePresenter implements RoleContract.Presenter {
    private RoleContract.Model model;
    private RoleContract.View view;

    public RolePresenter(RoleContract.View view) {
        this.view = view;
        model = new RoleModel(this);
    }

    @Override
    public void saveRole(String token, RuleInput input) {
        model.saveRole(token, input);
    }

    @Override
    public void saveRoleSuccess() {
        view.saveRoleSuccess();
    }

    @Override
    public void updateRole(String token, RuleInput input) {
        model.updateRole(token, input);
    }

    @Override
    public void getRoleSuccess(RuleInput result) {
        view.getRoleSuccess(result);
    }

    @Override
    public void getRole(String token) {
        model.getRole(token);
    }

    @Override
    public void error(String err) {
        view.showErr(err);
    }

    @Override
    public void updateSuccess() {
        view.updateSuccess();
    }
}
