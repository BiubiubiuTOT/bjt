package com.bangjiat.bjt.module.home.work.kaoqin.model;

import com.bangjiat.bjt.api.ApiFactory;
import com.bangjiat.bjt.api.MyCallBack;
import com.bangjiat.bjt.common.BaseResult;
import com.bangjiat.bjt.module.home.work.kaoqin.beans.InDakaInput;
import com.bangjiat.bjt.module.home.work.kaoqin.beans.OutDakaInput;
import com.bangjiat.bjt.module.home.work.kaoqin.contract.DakaContract;

import retrofit2.Response;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/8 0008
 */

public class DakaModel implements DakaContract.Model {
    private DakaContract.Presenter presenter;

    public DakaModel(DakaContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void inDaka(String token, InDakaInput input) {
        ApiFactory.getService().saveInDaka(token, input).enqueue(new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                BaseResult<String> body = response.body();
                if (body.getStatus() != 200) presenter.error(body.getMessage());
                else presenter.inDakaSuccess();
            }

            @Override
            public void onFail(String message) {
                presenter.error(message);
            }
        });

    }

    @Override
    public void outDaka(String token, OutDakaInput input) {
        ApiFactory.getService().saveOutDaka(token, input).enqueue(new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                BaseResult<String> body = response.body();
                if (body.getStatus() != 200) {
                    presenter.error(body.getMessage());
                } else {
                    presenter.outDakaSuccess();
                }
            }

            @Override
            public void onFail(String message) {
                presenter.error(message);
            }
        });
    }

}
