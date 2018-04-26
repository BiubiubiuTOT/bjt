package com.bangjiat.bjt.module.park.car.ui;

import android.os.Bundle;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.main.ui.activity.BaseWhiteToolBarActivity;

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
