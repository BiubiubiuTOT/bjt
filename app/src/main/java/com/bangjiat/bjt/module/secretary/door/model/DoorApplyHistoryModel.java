package com.bangjiat.bjt.module.secretary.door.model;

import com.bangjiat.bjt.api.ApiFactory;
import com.bangjiat.bjt.api.MyCallBack;
import com.bangjiat.bjt.common.BaseResult;
import com.bangjiat.bjt.module.secretary.door.beans.ApplyHistoryBean;
import com.bangjiat.bjt.module.secretary.door.contract.DoorApplyHistoryContract;

import retrofit2.Response;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/8 0008
 */

public class DoorApplyHistoryModel implements DoorApplyHistoryContract.Model {
    private DoorApplyHistoryContract.Presenter presenter;

    public DoorApplyHistoryModel(DoorApplyHistoryContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getDoorApplyHistory(String token, int page, int size) {
        ApiFactory.getService().getDoorApplyHistory(token, page, size).enqueue(new MyCallBack<BaseResult<ApplyHistoryBean>>() {
            @Override
            public void onSuc(Response<BaseResult<ApplyHistoryBean>> response) {
                BaseResult<ApplyHistoryBean> body = response.body();
                if (body.getStatus() == 200) {
                    presenter.getDoorApplyHistorySuccess(body.getData());
                } else {
                    presenter.error(body.getMessage());
                }
            }

            @Override
            public void onFail(String message) {
                presenter.error(message);
            }
        });
    }
}
