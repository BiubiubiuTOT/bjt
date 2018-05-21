package com.bangjiat.bjt.module.secretary.door.model;

import com.bangjiat.bjt.api.ApiFactory;
import com.bangjiat.bjt.api.MyCallBack;
import com.bangjiat.bjt.common.BaseResult;
import com.bangjiat.bjt.module.secretary.contact.beans.BuildUserInfo;
import com.bangjiat.bjt.module.secretary.door.contract.BuildUserInfoContract;

import retrofit2.Response;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/21 0021
 */

public class BuildUserInfoModel implements BuildUserInfoContract.Model {
    private BuildUserInfoContract.Presenter presenter;

    public BuildUserInfoModel(BuildUserInfoContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getInfo(String token) {
        ApiFactory.getService().getBuildUserInfo(token).enqueue(new MyCallBack<BaseResult<BuildUserInfo>>() {
            @Override
            public void onSuc(Response<BaseResult<BuildUserInfo>> response) {
                BaseResult<BuildUserInfo> body = response.body();
                if (body.getStatus() == 200) presenter.success(body.getData());
                else presenter.error(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.error(message);
            }
        });
    }
}
