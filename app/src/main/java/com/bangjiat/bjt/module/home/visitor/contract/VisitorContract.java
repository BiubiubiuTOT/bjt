package com.bangjiat.bjt.module.home.visitor.contract;

import com.bangjiat.bjt.module.home.visitor.beans.DealVisitorInput;
import com.bangjiat.bjt.module.home.visitor.beans.InviteBean;
import com.bangjiat.bjt.module.home.visitor.beans.VisitorBean;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/4/16 0016
 */

public interface VisitorContract {
    interface Model {
        void getVisitorHistory(String token, int page, int size, int type);

        void dealVisitor(String token, DealVisitorInput input);

        void addInvite(String token, InviteBean bean);
    }

    interface View {
        void showDialog();

        void dismissDialog();

        void error(String err);

        void success(VisitorBean bean);

        void dealSuccess(String str);
        void addInviteSuccess(String str);
    }

    interface Presenter {
        void getVisitorHistory(String token, int page, int size, int type);

        void error(String err);

        void success(VisitorBean bean);

        void dealVisitor(String token, DealVisitorInput input);

        void dealSuccess(String str);

        void addInvite(String token, InviteBean bean);

        void addInviteSuccess(String str);
    }
}
