package com.bangjiat.bjt.module.secretary.door.ui;

import android.os.Bundle;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.main.ui.activity.BaseColorToolBarActivity;

/**
 * 入驻申请 审核未通过
 */
public class FailActivity extends BaseColorToolBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_apply_fail;
    }

    @Override
    protected String getTitleStr() {
        return "入驻申请";
    }
}
