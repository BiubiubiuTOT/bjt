package com.bangjiat.bangjiaapp.module.notice.model;

import com.bangjiat.bangjiaapp.api.ApiFactory;
import com.bangjiat.bangjiaapp.api.MyCallBack;
import com.bangjiat.bangjiaapp.common.BaseResult;
import com.bangjiat.bangjiaapp.module.notice.contract.NoticeContract;

import retrofit2.Response;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/4/16 0016
 */

public class NoticeModel implements NoticeContract.Model {
    private NoticeContract.Presenter presenter;

    public NoticeModel(NoticeContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getAllNotice(String token) {
        ApiFactory.getService().getNotice(token).enqueue(new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
            }

            @Override
            public void onFail(String message) {

            }
        });
    }
}
