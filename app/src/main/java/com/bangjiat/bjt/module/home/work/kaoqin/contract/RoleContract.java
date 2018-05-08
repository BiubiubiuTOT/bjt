package com.bangjiat.bjt.module.home.work.kaoqin.contract;

import com.bangjiat.bjt.module.home.work.kaoqin.beans.RuleInput;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/8 0008
 */

public interface RoleContract {
    interface Model {
        void saveRole(String token, RuleInput input);

        void updateRole(String token, RuleInput input);

        void getRole(String token);
    }

    interface View {
        void showErr(String err);

        void getRoleSuccess(RuleInput result);

        void saveRoleSuccess();

        void updateSuccess();
    }

    interface Presenter {
        void saveRole(String token, RuleInput input);

        void saveRoleSuccess();

        void updateRole(String token, RuleInput input);

        void getRoleSuccess(RuleInput result);

        void getRole(String token);

        void error(String err);

        void updateSuccess();
    }
}
