package com.bangjiat.bjt.module.main.ui.activity;

import android.os.Bundle;

import com.bangjiat.bjt.R;

public class AboutActivity extends BaseColorToolBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_about;
    }

    @Override
    protected String getTitleStr() {
        return "关于我们";
    }


}
