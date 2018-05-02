package com.bangjiat.bjt.module.home.work.permission.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.main.ui.activity.BaseWhiteToolBarActivity;

import butterknife.OnClick;

public class PermissionMainActivity extends BaseWhiteToolBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_permission_main;
    }

    @Override
    protected String getTitleStr() {
        return "权限管理";
    }

    @OnClick(R.id.btn_permission)
    public void clickPermission(View view) {
        startActivity(new Intent(mContext, PermissionTransferActivity.class));
    }

    @OnClick(R.id.btn_setting)
    public void clickSetting(View view) {

    }
}
