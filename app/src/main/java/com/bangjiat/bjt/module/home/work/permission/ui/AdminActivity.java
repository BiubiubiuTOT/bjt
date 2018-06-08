package com.bangjiat.bjt.module.home.work.permission.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.main.ui.activity.BaseWhiteToolBarActivity;

import butterknife.OnClick;

public class AdminActivity extends BaseWhiteToolBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_admin;
    }

    @Override
    protected String getTitleStr() {
        return "管理员";
    }

    @OnClick(R.id.btn_admin)
    public void clickAdmin(View view) {
        startActivity(new Intent(mContext, AdminListActivity.class));
    }
}
