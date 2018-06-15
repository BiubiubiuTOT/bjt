package com.bangjiat.bjt.module.secretary.service.presenter;

import com.bangjiat.bjt.module.secretary.service.beans.BuildingAdminListResult;
import com.bangjiat.bjt.module.secretary.service.contract.BuildingAdminListContract;
import com.bangjiat.bjt.module.secretary.service.model.BuildingAdminListModel;

import java.util.List;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/9 0009
 */

public class BuildingAdminListPresenter implements BuildingAdminListContract.Presenter {
    private BuildingAdminListContract.View view;
    private BuildingAdminListContract.Model model;

    public BuildingAdminListPresenter(BuildingAdminListContract.View view) {
        this.view = view;
        model = new BuildingAdminListModel(this);
    }

    @Override
    public void getAdminList(String token) {
        view.showDialog();
        model.getAdminList(token);
    }

    @Override
    public void getAdminList(String token, int buildId) {
        view.showDialog();
        model.getAdminList(token, buildId);
    }

    @Override
    public void error(String err) {
        view.dismissDialog();
        view.error(err);
    }

    @Override
    public void success(List<BuildingAdminListResult> result) {
        view.dismissDialog();
        view.success(result);
    }
}
