package com.bangjiat.bangjiaapp.module.main.ui.activity;

import android.os.Bundle;

import com.bangjiat.bangjiaapp.R;

public class FeedBackActivity extends BaseColorToolBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_feed_back;
    }

    @Override
    protected String getTitleStr() {
        return "意见反馈";
    }

}
