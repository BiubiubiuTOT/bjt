package com.bangjiat.bjt.module.secretary.service.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.main.ui.activity.BaseColorToolBarActivity;

import butterknife.OnClick;

/**
 * 服务申请
 */
public class MainActivity extends BaseColorToolBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_apply_service;
    }

    @Override
    protected String getTitleStr() {
        return "服务申请";
    }

    @OnClick(R.id.ll_new_apply)
    public void clickNewApply(View v) {
        startActivity(new Intent(mContext, NewApplyActivity.class));
    }

    @OnClick(R.id.ll_apply_history)
    public void clickApplyHistory(View view) {
        startActivity(new Intent(mContext, HistoryActivity.class));
    }
}
