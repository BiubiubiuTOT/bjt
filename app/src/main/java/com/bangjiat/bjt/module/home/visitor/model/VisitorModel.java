package com.bangjiat.bjt.module.home.visitor.model;

import com.bangjiat.bjt.api.ApiFactory;
import com.bangjiat.bjt.api.MyCallBack;
import com.bangjiat.bjt.common.BaseResult;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.module.home.visitor.beans.DealVisitorInput;
import com.bangjiat.bjt.module.home.visitor.beans.InviteBean;
import com.bangjiat.bjt.module.home.visitor.beans.VisitorBean;
import com.bangjiat.bjt.module.home.visitor.contract.VisitorContract;
import com.orhanobut.logger.Logger;

import retrofit2.Response;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/4/16 0016
 */

public class VisitorModel implements VisitorContract.Model {
    private VisitorContract.Presenter presenter;

    public VisitorModel(VisitorContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getVisitorHistory(String token, int page, int size, int type) {
        ApiFactory.getService().getVisitorHistory(token, page, Constants.SIZE, type).enqueue(new MyCallBack<BaseResult<VisitorBean>>() {
            @Override
            public void onSuc(Response<BaseResult<VisitorBean>> response) {
                BaseResult<VisitorBean> body = response.body();
                if (body.getStatus() == 200) {
                    presenter.success(body.getData());
                } else presenter.error(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.error(message);
                Logger.e(message);
            }
        });
    }

    @Override
    public void dealVisitor(String token, DealVisitorInput input) {
        ApiFactory.getService().dealVisitorApply(token, input).enqueue(new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                BaseResult<String> body = response.body();
                if (body.getStatus() == 200) presenter.dealSuccess(body.getData());
                else presenter.error(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.error(message);
            }
        });
    }

    @Override
    public void addInvite(String token, final InviteBean bean) {
        ApiFactory.getService().addInvite(token, bean).enqueue(new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                BaseResult<String> body = response.body();
                if (body.getStatus() == 200)
                    presenter.addInviteSuccess(body.getData());
                else presenter.error(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.error(message);
            }
        });
    }
}
