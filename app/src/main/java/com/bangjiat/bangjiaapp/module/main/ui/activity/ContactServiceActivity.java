package com.bangjiat.bangjiaapp.module.main.ui.activity;

import android.os.Bundle;

import com.bangjiat.bangjiaapp.R;

public class ContactServiceActivity extends BaseColorToolBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_contact_service;
    }

    @Override
    protected String getTitleStr() {
        return "联系客服";
    }

}
