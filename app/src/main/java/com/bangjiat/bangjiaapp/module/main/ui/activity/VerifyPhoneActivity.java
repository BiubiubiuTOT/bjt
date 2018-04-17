package com.bangjiat.bangjiaapp.module.main.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.bangjiat.bangjiaapp.R;
import com.bangjiat.bangjiaapp.common.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 注册页面
 */
public class VerifyPhoneActivity extends BaseActivity {
    @BindView(R.id.et_code)
    EditText et_code;
    @BindView(R.id.et_phone)
    EditText et_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_verify_phone;
    }


    @OnClick(R.id.iv_back)
    public void clickBack(View view) {
        finish();
    }

    @OnClick(R.id.btn_verify)
    public void clickVerify(View view) {
        startActivity(new Intent(mContext, ModifyPhoneActivity.class));
    }

    @OnClick(R.id.tv_getCode)
    public void getCode(View v) {

    }
}
