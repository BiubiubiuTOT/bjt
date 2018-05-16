package com.bangjiat.bjt.module.home.work.permission.contract;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/16 0016
 */

public interface PermissionContract {
    interface Model {
        void deleteAdmin(String token, String[] users);

        void addAdmin(String token, String[] users);

        void updateAdmin(String token, String userId);
    }

    interface View {
        void showDialog();

        void dismissDialog();

        void error(String err);

        void deleteAdminSuccess();

        void addAdminSuccess();

        void updateAdminSuccess();
    }

    interface Presenter {
        void deleteAdmin(String token, String[] users);

        void addAdmin(String token, String[] users);

        void updateAdmin(String token, String userId);

        void error(String err);

        void deleteAdminSuccess();

        void addAdminSuccess();

        void updateAdminSuccess();
    }
}
