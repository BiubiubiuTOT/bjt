package com.bangjiat.bjt.module.home.notice.presenter;

import com.bangjiat.bjt.module.home.notice.beans.NoticeBean;
import com.bangjiat.bjt.module.home.notice.contract.NoticeContract;
import com.bangjiat.bjt.module.home.notice.model.NoticeModel;

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
    public void getNoticeSuccess(NoticeBean bean) {
        view.getAllNoticeResult(bean);
    }

    @Override
    public void getNoticeFail(String error) {
        view.showError(error);
    }
}
