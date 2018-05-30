package com.bangjiat.bjt.common;

import android.content.Context;

import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.orhanobut.logger.Logger;
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
        initCloudChannel(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    /**
     * 初始化云推送通道
     *
     * @param applicationContext
     */
    private void initCloudChannel(final Context applicationContext) {
        PushServiceFactory.init(applicationContext);
        CloudPushService pushService = PushServiceFactory.getCloudPushService();
        pushService.register(applicationContext, new CommonCallback() {
            @Override
            public void onSuccess(String response) {
                Logger.d("init cloudchannel success");
            }

            @Override
            public void onFailed(String errorCode, String errorMessage) {
                Logger.d("init cloudchannel failed -- errorcode:" + errorCode + " -- errorMessage:" + errorMessage);
            }
        });
    }

}
