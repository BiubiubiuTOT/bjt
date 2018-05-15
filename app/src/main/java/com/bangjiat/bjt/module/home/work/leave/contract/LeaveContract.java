package com.bangjiat.bjt.module.home.work.leave.contract;

import com.bangjiat.bjt.module.home.work.leave.beans.CompanyLeaveResult;
import com.bangjiat.bjt.module.home.work.leave.beans.DealLeaveInput;
import com.bangjiat.bjt.module.home.work.leave.beans.LeaveBean;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/15 0015
 */

public interface LeaveContract {
    interface Model {
        void addLeave(String token, LeaveBean bean);

        void getCompanyLeave(String token, int status, int page, int size);

        void getSelfLeave(String token, int status, int page, int size);

        void dealLeave(String token, DealLeaveInput input);
    }

    interface View {
        void showDialog();

        void dismissDialog();

        void error(String err);

        void addLeaveSuccess();

        void getCompanyLeaveSuccess(CompanyLeaveResult result);

        void getSelfLeaveSuccess(CompanyLeaveResult result);

        void dealLeaveSuccess();
    }

    interface Presenter {

        void addLeave(String token, LeaveBean bean);

        void getCompanyLeave(String token, int Status, int page, int size);

        void getSelfLeave(String token, int Status, int page, int size);

        void dealLeave(String token, DealLeaveInput input);

        void error(String err);

        void addLeaveSuccess();

        void getCompanyLeaveSuccess(CompanyLeaveResult result);

        void getSelfLeaveSuccess(CompanyLeaveResult result);

        void dealLeaveSuccess();
    }
}
