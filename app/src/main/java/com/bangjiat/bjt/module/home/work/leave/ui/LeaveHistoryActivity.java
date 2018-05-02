package com.bangjiat.bjt.module.home.work.leave.ui;

import android.os.Bundle;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.main.ui.activity.BaseWhiteToolBarActivity;

public class LeaveHistoryActivity extends BaseWhiteToolBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_leave_history;
    }

    @Override
    protected String getTitleStr() {
        return "请假申请";
    }
}
