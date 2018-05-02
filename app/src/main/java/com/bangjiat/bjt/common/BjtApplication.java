package com.bangjiat.bjt.common;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;


/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/4/17 0017
 */

public class BjtApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        SDKInitializer.initialize(getApplicationContext());
    }

}