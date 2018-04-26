package com.bangjiat.bjt.module.me.personaldata.presenter;

import com.bangjiat.bjt.module.me.personaldata.beans.UserInfoBean;
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
    public void updateUserInfoSuccess() {

    }

    @Override
    public void updateUserInfoFail(String err) {

    }

    @Override
    public void updateUserInfo(String token, UserInfoBean bean) {
        model.updateUserInfo(token, bean);
    }
}
