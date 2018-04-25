package com.bangjiat.bangjiaapp.module.park.car.ui;

import android.os.Bundle;

import com.bangjiat.bangjiaapp.R;
import com.bangjiat.bangjiaapp.module.main.ui.activity.BaseWhiteToolBarActivity;

public class MyCarDetailActivity extends BaseWhiteToolBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_my_car_detail;
    }

    @Override
    protected String getTitleStr() {
        return "车辆信息";
    }
}
