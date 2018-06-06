package com.bangjiat.bjt.module.home.notice.model;

import com.bangjiat.bjt.api.ApiFactory;
import com.bangjiat.bjt.api.MyCallBack;
import com.bangjiat.bjt.common.BannerBean;
import com.bangjiat.bjt.common.BaseResult;
import com.bangjiat.bjt.module.home.notice.contract.BannerContract;

import java.util.List;

import retrofit2.Response;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/6/6 0006
 */

public class BannerModel implements BannerContract.Model {
    private BannerContract.Presenter presenter;

    public BannerModel(BannerContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getBannerList(String token) {
        ApiFactory.getService().getBannerList(token).enqueue(new MyCallBack<BaseResult<List<BannerBean>>>() {
            @Override
            public void onSuc(Response<BaseResult<List<BannerBean>>> response) {
                BaseResult<List<BannerBean>> body = response.body();
                if (body.getStatus() == 200)
                    presenter.getBannerListSuccess(body.getData());
                else presenter.error(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.error(message);
            }
        });
    }
}
