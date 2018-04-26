package com.bangjiat.bjt.module.secretary.door.ui;

import android.os.Bundle;
import android.view.View;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.main.ui.activity.BaseColorToolBarActivity;

import butterknife.OnClick;

/**
 * 入驻申请 审核中
 */
public class ApplyingActivity extends BaseColorToolBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick(R.id.btn_return)
    public void clickReturn(View view) {
        finish();
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
