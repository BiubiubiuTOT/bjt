package com.bangjiat.bjt.module.secretary.service.contract;

import com.bangjiat.bjt.module.secretary.service.beans.BuildingAdminListResult;

import java.util.List;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/9 0009
 */

public interface BuildingAdminListContract {
    interface Model {
        void getAdminList(String token);
    }

    interface View {
        void showDialog();

        void dismissDialog();

        void error(String err);

        void success(List<BuildingAdminListResult>  result);
    }

    interface Presenter {
        void getAdminList(String token);

        void error(String err);

        void success(List<BuildingAdminListResult> result);
    }
}
