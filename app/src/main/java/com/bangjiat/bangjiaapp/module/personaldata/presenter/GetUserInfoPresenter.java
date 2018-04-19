package com.bangjiat.bangjiaapp.module.personaldata.presenter;

import com.bangjiat.bangjiaapp.module.personaldata.beans.UserInfoBean;
import com.bangjiat.bangjiaapp.module.personaldata.contract.GetUserInfoContract;
import com.bangjiat.bangjiaapp.module.personaldata.model.GetUserInfoModel;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/4/19 0019
 */

public class GetUserInfoPresenter implements GetUserInfoContract.Presenter {
    private GetUserInfoContract.View view;
    private GetUserInfoContract.Model model;

    public GetUserInfoPresenter(GetUserInfoContract.View view) {
        this.view = view;
        model = new GetUserInfoModel(this);
    }

    @Override
    public void getUserInfo(String token) {
        model.getUserInfo(token);
    }

    @Override
    public void getUserInfoFail(String err) {
        view.getUserInfoFail(err);
    }

    @Override
    public void getUserInfoSuccess(UserInfoBean bean) {
        view.getUserInfoSuccess(bean);
    }
}
