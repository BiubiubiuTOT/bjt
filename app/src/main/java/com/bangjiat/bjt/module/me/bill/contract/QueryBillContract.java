package com.bangjiat.bjt.module.me.bill.contract;

import com.bangjiat.bjt.module.me.bill.beans.PageBillBean;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/4 0004
 */

public interface QueryBillContract {
    interface Model {
        void getPageBill(String token, int page, int size, long begin, long end);
    }

    interface View {
        void showDialog();

        void dismissDialog();

        void getPageBillSuccess(PageBillBean billBean);

        void getPageBillFail(String err);
    }

    interface Presenter {
        void getPageBill(String token, int page, int size, long begin, long end);

        void getPageBillSuccess(PageBillBean billBean);

        void getPageBillFail(String err);
    }
}
