package com.bangjiat.bjt.module.secretary.door.presenter;

import com.bangjiat.bjt.module.secretary.contact.beans.BuildUserInfo;
import com.bangjiat.bjt.module.secretary.door.contract.BuildUserInfoContract;
import com.bangjiat.bjt.module.secretary.door.model.BuildUserInfoModel;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/21 0021
 */

public class BuildUserInfoPresenter implements BuildUserInfoContract.Presenter {
    private BuildUserInfoContract.View view;
    private BuildUserInfoContract.Model model;

    public BuildUserInfoPresenter(BuildUserInfoContract.View view) {
        this.view = view;
        model = new BuildUserInfoModel(this);
    }

    @Override
    public void getInfo(String token) {
        model.getInfo(token);
    }

    @Override
    public void error(String err) {
        view.error(err);
    }

    @Override
    public void success(BuildUserInfo info) {
        view.success(info);
    }
}
