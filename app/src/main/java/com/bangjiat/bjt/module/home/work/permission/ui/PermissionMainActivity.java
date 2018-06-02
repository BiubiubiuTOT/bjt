package com.bangjiat.bjt.module.home.work.permission.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.module.main.ui.activity.BaseWhiteToolBarActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class PermissionMainActivity extends BaseWhiteToolBarActivity {
    private static final int OK = 2;
    @BindView(R.id.btn_permission)
    Button btn_permission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Constants.isCompanyAdmin() || Constants.isWorkAdmin()) //公司管理员、工作台管理员才能转交权限
            btn_permission.setVisibility(View.VISIBLE);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_permission_main;
    }

    @Override
    protected String getTitleStr() {
        return "权限管理";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == OK) {
            setResult(RESULT_OK);
            finish();
        }
    }

    @OnClick(R.id.btn_permission)
    public void clickPermission(View view) {
        startActivityForResult(new Intent(mContext, PermissionTransferActivity.class), OK);
    }

    @OnClick(R.id.btn_setting)
    public void clickSetting(View view) {
        startActivity(new Intent(mContext, AdminSettingActivity.class));
    }
}
