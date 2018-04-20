package com.bangjiat.bangjiaapp.module.secretary.door.ui;

import android.os.Bundle;

import com.bangjiat.bangjiaapp.R;
import com.bangjiat.bangjiaapp.module.main.ui.activity.BaseColorToolBarActivity;

/**
 * 申请入驻楼宇
 */
public class ApplyIntoBuildingActivity extends BaseColorToolBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_apply_into_buliding;
    }

    @Override
    protected String getTitleStr() {
        return "申请入驻楼宇";
    }
}
