package com.bangjiat.bjt.module.home.work.kaoqin.contract;

import com.bangjiat.bjt.module.home.work.kaoqin.beans.InDakaInput;
import com.bangjiat.bjt.module.home.work.kaoqin.beans.OutDakaInput;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/5/8 0008
 */

public interface DakaContract {
    interface Model {
        void inDaka(String token, InDakaInput input);

        void outDaka(String token, OutDakaInput input);

    }

    interface View {
        void showDialog();

        void dismissDialog();

        void error(String err);

        void inDakaSuccess();

        void outDakaSuccess();
    }

    interface Presenter {
        void inDaka(String token, InDakaInput input);

        void outDaka(String token, OutDakaInput input);

        void error(String err);

        void inDakaSuccess();

        void outDakaSuccess();
    }
}
