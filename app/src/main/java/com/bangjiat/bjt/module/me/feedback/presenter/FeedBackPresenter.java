package com.bangjiat.bjt.module.me.feedback.presenter;

import com.bangjiat.bjt.module.me.feedback.beans.FeedBackInput;
import com.bangjiat.bjt.module.me.feedback.contract.FeedBackContract;
import com.bangjiat.bjt.module.me.feedback.model.FeedBackModel;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/3 0003
 */

public class FeedBackPresenter implements FeedBackContract.Presenter {
    private FeedBackContract.View view;
    private FeedBackContract.Model model;

    public FeedBackPresenter(FeedBackContract.View view) {
        this.view = view;
        model = new FeedBackModel(this);
    }

    @Override
    public void saveFeedBack(String token, FeedBackInput input) {
        if (input.getContent().isEmpty()) {
            view.showError("意见不能为空");
            return;
        }
        view.showDialog();
        model.saveFeedBack(token, input);
    }

    @Override
    public void success() {
        view.success();
        view.dismissDialog();
    }

    @Override
    public void fail(String err) {
        view.showError(err);
        view.dismissDialog();
    }
}
