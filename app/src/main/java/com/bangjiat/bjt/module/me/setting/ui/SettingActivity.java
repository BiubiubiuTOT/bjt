package com.bangjiat.bjt.module.me.setting.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.module.main.ui.activity.BaseWhiteToolBarActivity;

import butterknife.BindView;
import butterknife.OnClick;
import vn.luongvo.widget.iosswitchview.SwitchView;

public class SettingActivity extends BaseWhiteToolBarActivity {
    @BindView(R.id.switch_view)
    SwitchView switch_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        boolean b = DataUtil.isReceiveNotification(mContext);
        switch_view.setChecked(b);
        switch_view.setOnCheckedChangeListener(new SwitchView.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchView switchView, boolean isChecked) {
                DataUtil.setReceiveNotification(mContext, isChecked);
            }
        });
    }

    @OnClick(R.id.tv_update_password)
    public void clickUpdatePassword(View view) {
        startActivity(new Intent(mContext, UpdatePasswordActivity.class));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_setting;
    }

    @Override
    protected String getTitleStr() {
        return "设置";
    }
}
