package com.bangjiat.bangjiaapp.module.main.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;

import com.bangjiat.bangjiaapp.R;
import com.bangjiat.bangjiaapp.common.BaseActivity;
import com.bangjiat.bangjiaapp.common.DataUtil;
import com.bangjiat.bangjiaapp.module.account.ui.LoginActivity;

/**
 * 引导页
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        mHandler.sendEmptyMessageDelayed(1, 2000);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_splash;
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                startActivity(new Intent(mContext, DataUtil.isLogin(mContext) ? MainActivity.class : LoginActivity.class));
                finish();
            }
        }
    };
}
