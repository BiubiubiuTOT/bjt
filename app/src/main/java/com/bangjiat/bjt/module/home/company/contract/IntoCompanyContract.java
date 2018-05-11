package com.bangjiat.bjt.module.home.company.contract;

import com.bangjiat.bjt.module.home.company.beans.CompanyDetailResult;
import com.bangjiat.bjt.module.home.company.beans.IntoCompanyInput;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/5 0005
 */

public interface IntoCompanyContract {
    interface Model {
        void intoCompany(String token, IntoCompanyInput input);

        void getCompanyDetail(String token, String companyId);
    }

    interface View {
        void showDialog();

        void dismissDialog();

        void success();

        void fail(String err);

        void getCompanyDetailSuccess(CompanyDetailResult result);
    }

    interface Presenter {
        void intoCompany(String token, IntoCompanyInput input);

        void getCompanyDetailSuccess(CompanyDetailResult result);

        void getCompanyDetail(String token, String companyId);

        void success();

        void fail(String err);
    }
}
