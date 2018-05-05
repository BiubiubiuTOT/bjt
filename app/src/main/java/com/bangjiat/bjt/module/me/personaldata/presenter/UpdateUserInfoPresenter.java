package com.bangjiat.bjt.module.me.personaldata.presenter;

import com.bangjiat.bjt.module.me.personaldata.beans.UserInfo;
import com.bangjiat.bjt.module.me.personaldata.contract.UpdateUserInfoContract;
import com.bangjiat.bjt.module.me.personaldata.model.UpdateUserInfoModel;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/4/19 0019
 */

public class UpdateUserInfoPresenter implements UpdateUserInfoContract.Presenter {
    private UpdateUserInfoContract.View view;
    private UpdateUserInfoContract.Model model;

    public UpdateUserInfoPresenter(UpdateUserInfoContract.View view) {
        this.view = view;
        model = new UpdateUserInfoModel(this);
    }

    @Override
    public void updateUserInfoSuccess(UserInfo info) {
        view.dismissDialog();
        view.updateUserInfoSuccess(info);
    }

    @Override
    public void updateUserInfoFail(String err) {
        view.updateUserInfoFail(err);
    }

    @Override
    public void updateUserInfo(String token, UserInfo bean) {
        view.showDialog();
        model.updateUserInfo(token, bean);
    }
}
