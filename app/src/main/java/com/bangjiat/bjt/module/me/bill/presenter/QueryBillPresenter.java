package com.bangjiat.bjt.module.me.bill.presenter;

import com.bangjiat.bjt.module.me.bill.beans.PageBillBean;
import com.bangjiat.bjt.module.me.bill.contract.QueryBillContract;
import com.bangjiat.bjt.module.me.bill.model.QueryBillModel;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/4 0004
 */

public class QueryBillPresenter implements QueryBillContract.Presenter {
    private QueryBillContract.Model model;
    private QueryBillContract.View view;

    public QueryBillPresenter(QueryBillContract.View view) {
        this.view = view;
        model = new QueryBillModel(this);
    }

    @Override
    public void getPageBill(String token, int page, int size) {
        view.showDialog();
        model.getPageBill(token, page, size);
    }

    @Override
    public void getPageBillSuccess(PageBillBean billBean) {
        view.dismissDialog();
        view.getPageBillSuccess(billBean);
    }

    @Override
    public void getPageBillFail(String err) {
        view.dismissDialog();
        view.getPageBillFail(err);
    }
}
