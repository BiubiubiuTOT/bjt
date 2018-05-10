package com.bangjiat.bjt.module.secretary.door.model;

import com.bangjiat.bjt.api.ApiFactory;
import com.bangjiat.bjt.api.MyCallBack;
import com.bangjiat.bjt.common.BaseResult;
import com.bangjiat.bjt.module.secretary.door.beans.DoorApplyDetailResult;
import com.bangjiat.bjt.module.secretary.door.contract.DoorApplyDetailContract;

import java.util.List;

import retrofit2.Response;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/10 0010
 */

public class DoorApplyDetailModel implements DoorApplyDetailContract.Model {
    private DoorApplyDetailContract.Presenter presenter;

    public DoorApplyDetailModel(DoorApplyDetailContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getDetail(String token, String guardMainId) {
        ApiFactory.getService().getDoorApplyDetail(token, guardMainId).enqueue(new MyCallBack<BaseResult<List<DoorApplyDetailResult>>>() {
            @Override
            public void onSuc(Response<BaseResult<List<DoorApplyDetailResult>>> response) {
                BaseResult<List<DoorApplyDetailResult>> body = response.body();
                if (body.getStatus() == 200)
                    presenter.success(body.getData());
                else presenter.error(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.error(message);
            }
        });
    }
}
