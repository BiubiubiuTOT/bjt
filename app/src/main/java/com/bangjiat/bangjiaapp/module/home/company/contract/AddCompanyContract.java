package com.bangjiat.bangjiaapp.module.home.company.contract;

import com.bangjiat.bangjiaapp.module.home.company.beans.CompanyInput;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/4/19 0019
 */

public interface AddCompanyContract {
    interface Model {
        void addCompany(String token, CompanyInput input);
    }

    interface View {
        void showDialog();

        void dismissDialog();

        void addCompanySuccess();

        void addCompanyFail(String err);
    }

    interface Presenter {
        void addCompany(String token, CompanyInput input);

        void addCompanySuccess();

        void addCompanyFail(String err);
    }
}
