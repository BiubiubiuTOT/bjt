package com.bangjiat.bangjiaapp.module.personaldata.model;

import com.bangjiat.bangjiaapp.api.ApiFactory;
import com.bangjiat.bangjiaapp.api.MyCallBack;
import com.bangjiat.bangjiaapp.common.BaseResult;
import com.bangjiat.bangjiaapp.module.personaldata.beans.UserInfoBean;
import com.bangjiat.bangjiaapp.module.personaldata.contract.UpdateUserInfoContract;

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
    public void updateUserInfo(String token, UserInfoBean bean) {
        ApiFactory.getService().updateUserInfo(token, bean).enqueue(new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {

            }

            @Override
            public void onFail(String message) {
                presenter.updateUserInfoFail(message);
            }
        });
    }
}
