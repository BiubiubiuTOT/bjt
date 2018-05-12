package com.bangjiat.bjt.module.secretary.communication.presenter;

import com.bangjiat.bjt.module.secretary.communication.beans.EmailBean;
import com.bangjiat.bjt.module.secretary.communication.contract.SendEmailContract;
import com.bangjiat.bjt.module.secretary.communication.model.SendEmailModel;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/12 0012
 */

public class SendEmailPresenter implements SendEmailContract.Presenter {
    private SendEmailContract.View view;
    private SendEmailContract.Model model;

    public SendEmailPresenter(SendEmailContract.View view) {
        this.view = view;
        model = new SendEmailModel(this);
    }

    @Override
    public void sendEmail(String token, EmailBean bean) {
        view.showDialog();
        model.sendEmail(token, bean);
    }

    @Override
    public void success() {
        view.dismissDialog();
        view.success();
    }

    @Override
    public void fail(String err) {
        view.dismissDialog();
        view.fail(err);
    }
}
