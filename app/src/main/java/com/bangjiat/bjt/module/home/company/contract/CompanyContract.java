package com.bangjiat.bjt.module.home.company.contract;

import com.bangjiat.bjt.module.home.company.beans.CompanyDetailResult;
import com.bangjiat.bjt.module.home.company.beans.CompanyInput;
import com.bangjiat.bjt.module.home.company.beans.DeleteCompanyInput;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/4/19 0019
 */

public interface CompanyContract {
    interface Model {
        void addCompany(String token, CompanyInput input);

        void exitCompany(String token);

        void updateCompany(String token, CompanyDetailResult result);

        void deleteCompany(String token, DeleteCompanyInput input);

    }

    interface View {
        void showDialog();

        void dismissDialog();

        void addCompanySuccess();

        void addCompanyFail(String err);

        void updateCompanySuccess(String str);

        void exitCompanySuccess(String token);

        void error(String err);

        void deleteCompanySuccess();
    }

    interface Presenter {
        void addCompany(String token, CompanyInput input);

        void addCompanySuccess();

        void addCompanyFail(String err);

        void exitCompany(String token);

        void updateCompanySuccess(String str);

        void exitCompanySuccess(String str);

        void updateCompany(String token, CompanyDetailResult result);

        void error(String err);

        void deleteCompany(String token, DeleteCompanyInput input);

        void deleteCompanySuccess();

    }
}
