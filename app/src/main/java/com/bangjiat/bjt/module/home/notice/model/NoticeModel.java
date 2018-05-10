package com.bangjiat.bjt.module.home.notice.model;

import com.bangjiat.bjt.api.ApiFactory;
import com.bangjiat.bjt.api.MyCallBack;
import com.bangjiat.bjt.common.BaseResult;
import com.bangjiat.bjt.module.home.notice.beans.NoticeBean;
import com.bangjiat.bjt.module.home.notice.contract.NoticeContract;

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
        ApiFactory.getService().getNotice(token).enqueue(new MyCallBack<BaseResult<NoticeBean>>() {
            @Override
            public void onSuc(Response<BaseResult<NoticeBean>> response) {
                BaseResult<NoticeBean> body = response.body();
                if (body.getStatus() == 200) {
                    presenter.getNoticeSuccess(body.getData());
                }
            }

            @Override
            public void onFail(String message) {
                presenter.getNoticeFail(message);
            }
        });
    }
}
