package com.bangjiat.bjt.module.home.work.leave.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.main.ui.activity.BaseWhiteToolBarActivity;
import com.bangjiat.bjt.module.secretary.door.ui.HistoryActivity;

import butterknife.OnClick;

public class LeaveMainActivity extends BaseWhiteToolBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_leave_main;
    }

    @Override
    protected String getTitleStr() {
        return "请假申请";
    }

    @OnClick(R.id.ll_new_apply)
    public void clickNewApply(View view) {
        startActivity(new Intent(mContext, LeaveNewApplyActivity.class));
    }

    @OnClick(R.id.ll_apply_history)
    public void clickApplyHistory(View view) {
        startActivity(new Intent(mContext, HistoryActivity.class));
    }
}
