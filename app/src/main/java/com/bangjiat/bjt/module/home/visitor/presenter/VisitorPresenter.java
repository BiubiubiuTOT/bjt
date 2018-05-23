package com.bangjiat.bjt.module.home.visitor.presenter;

import com.bangjiat.bjt.module.home.visitor.beans.DealVisitorInput;
import com.bangjiat.bjt.module.home.visitor.beans.InviteBean;
import com.bangjiat.bjt.module.home.visitor.beans.VisitorBean;
import com.bangjiat.bjt.module.home.visitor.contract.VisitorContract;
import com.bangjiat.bjt.module.home.visitor.model.VisitorModel;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/4/16 0016
 */

public class VisitorPresenter implements VisitorContract.Presenter {
    private VisitorContract.View view;
    private VisitorContract.Model model;

    public VisitorPresenter(VisitorContract.View view) {
        this.view = view;
        model = new VisitorModel(this);
    }


    @Override
    public void getVisitorHistory(String token, int page, int size, int type) {
        view.showDialog();
        model.getVisitorHistory(token, page, size, type);
    }

    @Override
    public void error(String err) {
        view.dismissDialog();
        view.error(err);
    }

    @Override
    public void success(VisitorBean bean) {
        view.dismissDialog();
        view.success(bean);
    }


    @Override
    public void dealVisitor(String token, DealVisitorInput input) {
        view.showDialog();
        model.dealVisitor(token, input);
    }

    @Override
    public void dealSuccess(String str) {
        view.dismissDialog();
        view.dealSuccess(str);
    }

    @Override
    public void addInvite(String token, InviteBean bean) {
        if (bean.getVisitMatter().isEmpty()) {
            error("请填写访问事宜");
            return;
        }
        if (bean.getVisitorName().isEmpty()) {
            error("请填写对方姓名");
            return;
        }
        if (bean.getVisitorPhone().isEmpty()){
            error("请添加对方号码");
            return;
        }

        view.showDialog();
        model.addInvite(token, bean);
    }

    @Override
    public void addInviteSuccess(String str) {
        view.dismissDialog();
        view.addInviteSuccess(str);
    }
}
