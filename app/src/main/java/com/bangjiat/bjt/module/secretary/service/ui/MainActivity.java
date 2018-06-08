package com.bangjiat.bjt.module.secretary.service.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.module.main.ui.activity.BaseColorToolBarActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 服务申请
 */
public class MainActivity extends BaseColorToolBarActivity {
    @BindView(R.id.ll_nickname)
    LinearLayout ll_add_people;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Constants.isBuildingAdmin())
            ll_add_people.setVisibility(View.GONE);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_apply_service;
    }

    @Override
    protected String getTitleStr() {
        return "服务申请";
    }

    @OnClick(R.id.ll_new_apply)
    public void clickNewApply(View v) {
        startActivity(new Intent(mContext, NewApplyActivity.class));
    }

    @OnClick(R.id.ll_apply_history)
    public void clickApplyHistory(View view) {
        startActivity(new Intent(mContext, ServiceHistoryActivity.class));
    }
}
