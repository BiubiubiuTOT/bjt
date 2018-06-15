package com.bangjiat.bjt.module.secretary.service.model;

import com.bangjiat.bjt.api.ApiFactory;
import com.bangjiat.bjt.api.MyCallBack;
import com.bangjiat.bjt.common.BaseResult;
import com.bangjiat.bjt.module.secretary.service.beans.BuildingAdminListResult;
import com.bangjiat.bjt.module.secretary.service.contract.BuildingAdminListContract;

import java.util.List;

import retrofit2.Response;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/9 0009
 */

public class BuildingAdminListModel implements BuildingAdminListContract.Model {
    private BuildingAdminListContract.Presenter presenter;

    public BuildingAdminListModel(BuildingAdminListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getAdminList(String token) {
        ApiFactory.getService().getBuildingAdminList(token).enqueue(new MyCallBack<BaseResult<List<BuildingAdminListResult>>>() {
            @Override
            public void onSuc(Response<BaseResult<List<BuildingAdminListResult>>> response) {
                BaseResult<List<BuildingAdminListResult>> body = response.body();
                if (body.getStatus() == 200)
                    presenter.success(body.getData());
                else presenter.error(body.getMessage());

            }

            @Override
            public void onFail(String message) {
                presenter.error(message);
            }
        });
    }

    @Override
    public void getAdminList(String token, int buildId) {
        ApiFactory.getService().getBuildingAdminList(token, buildId).enqueue(new MyCallBack<BaseResult<List<BuildingAdminListResult>>>() {
            @Override
            public void onSuc(Response<BaseResult<List<BuildingAdminListResult>>> response) {
                BaseResult<List<BuildingAdminListResult>> body = response.body();
                if (body.getStatus() == 200)
                    presenter.success(body.getData());
                else presenter.error(body.getMessage());

            }

            @Override
            public void onFail(String message) {
                presenter.error(message);
            }
        });
    }
}
