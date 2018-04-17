package com.bangjiat.bangjiaapp.module.main.ui.activity;

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
public class ModifyPhoneActivity extends BaseActivity {

    @BindView(R.id.et_new_password)
    EditText et_password;
    @BindView(R.id.et_new_password_2)
    EditText et_new_password_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_modify_phone;
    }


    @OnClick(R.id.iv_back)
    public void clickBack(View view) {
        finish();
    }

    @OnClick(R.id.btn_modify)
    public void clickRegister(View view) {

    }

}
