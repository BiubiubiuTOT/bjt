package com.bangjiat.bangjiaapp.module.me.personaldata.model;

import com.bangjiat.bangjiaapp.api.ApiFactory;
import com.bangjiat.bangjiaapp.api.MyCallBack;
import com.bangjiat.bangjiaapp.common.BaseResult;
import com.bangjiat.bangjiaapp.module.me.personaldata.beans.UserInfoBean;
import com.bangjiat.bangjiaapp.module.me.personaldata.contract.GetUserInfoContract;

import retrofit2.Response;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/4/19 0019
 */

public class GetUserInfoModel implements GetUserInfoContract.Model {
    private GetUserInfoContract.Presenter presenter;

    public GetUserInfoModel(GetUserInfoContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getUserInfo(String token) {
        ApiFactory.getService().getUserInfo(token).enqueue(new MyCallBack<BaseResult<UserInfoBean>>() {
            @Override
            public void onSuc(Response<BaseResult<UserInfoBean>> response) {
                BaseResult<UserInfoBean> body = response.body();
                if (body.getStatus() == 200) {
                    presenter.getUserInfoSuccess(body.getData());
                } else presenter.getUserInfoFail(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.getUserInfoFail(message);
            }
        });
    }
}
