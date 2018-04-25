package com.bangjiat.bangjiaapp.module.park.pay.ui;

import android.os.Bundle;

import com.bangjiat.bangjiaapp.R;
import com.bangjiat.bangjiaapp.module.main.ui.activity.BaseWhiteToolBarActivity;

public class PayDetailActivity extends BaseWhiteToolBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_pay_detail;
    }

    @Override
    protected String getTitleStr() {
        return "付款详情";
    }
}
