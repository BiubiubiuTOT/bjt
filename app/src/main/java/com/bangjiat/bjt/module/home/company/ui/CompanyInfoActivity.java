package com.bangjiat.bjt.module.home.company.ui;

import android.os.Bundle;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.main.ui.activity.BaseWhiteToolBarActivity;

/**
 * 公司信息
 */
public class CompanyInfoActivity extends BaseWhiteToolBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_company_info;
    }

    @Override
    protected String getTitleStr() {
        return "公司信息";
    }
}
