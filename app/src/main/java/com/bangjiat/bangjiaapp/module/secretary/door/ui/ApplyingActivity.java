package com.bangjiat.bangjiaapp.module.secretary.door.ui;

import android.os.Bundle;

import com.bangjiat.bangjiaapp.R;
import com.bangjiat.bangjiaapp.module.main.ui.activity.BaseColorToolBarActivity;

/**
 * 入驻申请 审核中
 */
public class ApplyingActivity extends BaseColorToolBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_applying;
    }

    @Override
    protected String getTitleStr() {
        return "入驻申请";
    }
}
