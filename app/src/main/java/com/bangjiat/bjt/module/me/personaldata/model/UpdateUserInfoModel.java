package com.bangjiat.bjt.module.me.personaldata.model;

import com.bangjiat.bjt.api.ApiFactory;
import com.bangjiat.bjt.api.MyCallBack;
import com.bangjiat.bjt.common.BaseResult;
import com.bangjiat.bjt.module.me.personaldata.beans.UserInfo;
import com.bangjiat.bjt.module.me.personaldata.contract.UpdateUserInfoContract;
import com.orhanobut.logger.Logger;

import retrofit2.Response;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/4/19 0019
 */

public class UpdateUserInfoModel implements UpdateUserInfoContract.Model {
    private UpdateUserInfoContract.Presenter presenter;

    public UpdateUserInfoModel(UpdateUserInfoContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void updateUserInfo(String token, final UserInfo bean) {
        ApiFactory.getService().updateUserInfo(token, bean).enqueue(new MyCallBack<BaseResult<UserInfo>>() {
            @Override
            public void onSuc(Response<BaseResult<UserInfo>> response) {
                BaseResult<UserInfo> body = response.body();
                if (body.getStatus() == 200) {
                    presenter.updateUserInfoSuccess(body.getData());
                } else {
                    presenter.updateUserInfoFail(body.getMessage());
                }
            }

            @Override
            public void onFail(String message) {
                Logger.e(message);
                presenter.updateUserInfoFail(message);
            }
        });
    }
}
