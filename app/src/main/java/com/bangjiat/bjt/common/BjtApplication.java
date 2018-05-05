package com.bangjiat.bjt.common;

import com.baidu.mapapi.SDKInitializer;
import com.orm.SugarApp;


/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/4/17 0017
 */

public class BjtApplication extends SugarApp {
    @Override
    public void onCreate() {
        super.onCreate();

        SDKInitializer.initialize(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
