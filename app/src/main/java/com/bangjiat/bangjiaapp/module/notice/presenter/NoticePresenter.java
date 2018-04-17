package com.bangjiat.bangjiaapp.module.notice.presenter;

import com.bangjiat.bangjiaapp.module.notice.contract.NoticeContract;
import com.bangjiat.bangjiaapp.module.notice.model.NoticeModel;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/4/16 0016
 */

public class NoticePresenter implements NoticeContract.Presenter {
    private NoticeContract.View view;
    private NoticeContract.Model model;

    public NoticePresenter(NoticeContract.View view) {
        this.view = view;
        model = new NoticeModel(this);
    }

    @Override
    public void getAllNotice(String token) {
        model.getAllNotice(token);
    }

    @Override
    public void getNoticeSuccess() {

    }

    @Override
    public void getNoticeFail(String error) {

    }
}
