package com.bangjiat.bjt.module.main.account.contract;

import com.bangjiat.bjt.module.main.account.beans.ValidateCodeInput;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/10 0010
 */

public interface ValidateCodeContract {
    interface Model {
        void getRegisterCode(String phone, int type);

        void validateCode( ValidateCodeInput input);
    }

    interface View {
        void showDialog();

        void dismissDialog();

        void getCodeSuccess(String code);

        void getCodeFail(String err);

        void validateSuccess();

        void validateFail(String err);
    }

    interface Presenter {
        void getRegisterCode(String phone, int type);

        void validateCode( ValidateCodeInput input);

        void getCodeSuccess(String code);

        void getCodeFail(String err);

        void validateSuccess();

        void validateFail(String err);
    }
}
