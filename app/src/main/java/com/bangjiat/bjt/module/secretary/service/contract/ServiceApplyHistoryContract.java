package com.bangjiat.bjt.module.secretary.service.contract;

import com.bangjiat.bjt.module.secretary.door.beans.ApprovalServiceInput;
import com.bangjiat.bjt.module.secretary.service.beans.ServiceApplyHistoryResult;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/9 0009
 */

public interface ServiceApplyHistoryContract {
    interface Model {
        void getHistory(String token, int page);

        void getAdminHistory(String token, int id, int page, int status);

        void approvalService(String token, int id, ApprovalServiceInput input);
    }

    interface View {
        void showDialog();

        void dismissDialog();

        void success(ServiceApplyHistoryResult result);

        void error(String err);

        void approvalServiceSuccess();

        void getAdminHistorySuccess(ServiceApplyHistoryResult result);
    }

    interface Presenter {
        void getHistory(String token, int page);

        void success(ServiceApplyHistoryResult result);

        void error(String err);

        void approvalService(String token, int id, ApprovalServiceInput input);

        void approvalServiceSuccess();

        void getAdminHistory(String token, int id, int page, int status);

        void getAdminHistorySuccess(ServiceApplyHistoryResult result);
    }
}
