package com.bangjiat.bjt.module.home.notice.contract;

import com.bangjiat.bjt.common.BannerBean;

import java.util.List;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/6/6 0006
 */

public interface BannerContract {
    interface Model {
        void getBannerList(String token);
    }

    interface View {

        void getBannerListSuccess(List<BannerBean> list);

        void error(String err);
    }

    interface Presenter {
        void getBannerListSuccess(List<BannerBean> list);

        void error(String err);

        void getBannerList(String token);
    }
}
