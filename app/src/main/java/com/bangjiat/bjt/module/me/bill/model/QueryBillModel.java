package com.bangjiat.bjt.module.me.bill.model;

import com.bangjiat.bjt.api.ApiFactory;
import com.bangjiat.bjt.api.MyCallBack;
import com.bangjiat.bjt.common.BaseResult;
import com.bangjiat.bjt.module.me.bill.beans.PageBillBean;
import com.bangjiat.bjt.module.me.bill.contract.QueryBillContract;

import retrofit2.Response;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/4 0004
 */

public class QueryBillModel implements QueryBillContract.Model {
    private QueryBillContract.Presenter presenter;

    public QueryBillModel(QueryBillContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getPageBill(String token, final int page, int size) {
        ApiFactory.getService().getPageBill(token, page, size).enqueue(new MyCallBack<BaseResult<PageBillBean>>() {
            @Override
            public void onSuc(Response<BaseResult<PageBillBean>> response) {
                BaseResult<PageBillBean> body = response.body();
                if (body.getStatus() == 200) {
                    presenter.getPageBillSuccess(body.getData());
                } else presenter.getPageBillFail(body.getMessage());
            }

            @Override
            public void onFail(String message) {
                presenter.getPageBillFail(message);
            }
        });
    }
}
