package com.bangjiat.bangjiaapp.module.park.apply.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bangjiat.bangjiaapp.R;
import com.bangjiat.bangjiaapp.module.main.ui.activity.BaseWhiteToolBarActivity;
import com.bangjiat.bangjiaapp.module.secretary.service.ui.HistoryActivity;

import butterknife.OnClick;

public class ApplyMainActivity extends BaseWhiteToolBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick(R.id.ll_new_apply)
    public void clickNewApply(View v) {
        startActivity(new Intent(mContext, NewApplyActivity.class));
    }

    @OnClick(R.id.ll_apply_history)
    public void clickApplyHistory(View view) {
        startActivity(new Intent(mContext, HistoryActivity.class));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_apply_main;
    }

    @Override
    protected String getTitleStr() {
        return "停车申请";
    }
}
