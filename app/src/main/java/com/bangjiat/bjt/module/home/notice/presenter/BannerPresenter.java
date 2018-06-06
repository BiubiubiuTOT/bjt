package com.bangjiat.bjt.module.home.notice.presenter;

import com.bangjiat.bjt.common.BannerBean;
import com.bangjiat.bjt.module.home.notice.contract.BannerContract;
import com.bangjiat.bjt.module.home.notice.model.BannerModel;

import java.util.List;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/6/6 0006
 */

public class BannerPresenter implements BannerContract.Presenter {
    private BannerContract.View view;
    private BannerContract.Model model;

    public BannerPresenter(BannerContract.View view) {
        this.view = view;
        model = new BannerModel(this);
    }

    @Override
    public void getBannerListSuccess(List<BannerBean> list) {
        view.getBannerListSuccess(list);
    }

    @Override
    public void error(String err) {
        view.error(err);
    }

    @Override
    public void getBannerList(String token) {
        model.getBannerList(token);
    }
}
