package com.bangjiat.bangjiaapp.module.main.account.contract;

import com.bangjiat.bangjiaapp.common.BaseResult;
import com.bangjiat.bangjiaapp.module.main.account.beans.RegisterInput;

/**
 * Created by Administrator on 2018/4/14 0014.
 */

public interface RegisterContract {
    interface Model {
        void register(RegisterInput input);

        void getRegisterCode(String phone, int type);
    }

    interface View {
        void showDialog();

        void dismissDialog();

        void showError(String err);

        void getCodeSuccess();

        void registerSuccess(BaseResult<String> result);
    }

    interface Presenter {
        void register(String phone, String password, String code);

        void getRegisterCode(String phone);

        void getCodeSuccess(BaseResult<String> result);

        void registerSuccess(BaseResult<String> result);

        void error(String err);
    }
}
