package com.bangjiat.bjt.module.home.company.contract;

import com.bangjiat.bjt.module.home.company.beans.IntoCompanyInput;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/5 0005
 */

public interface IntoCompanyContract {
    interface Model {
        void intoCompany(String token, IntoCompanyInput input);
    }

    interface View {
        void showDialog();

        void dismissDialog();

        void success();

        void fail(String err);
    }

    interface Presenter {
        void intoCompany(String token, IntoCompanyInput input);

        void success();

        void fail(String err);
    }
}
