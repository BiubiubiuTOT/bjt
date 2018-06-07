package com.bangjiat.bjt.module.home.work.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.home.work.kaoqin.ui.KaoqinMainActivity;
import com.bangjiat.bjt.module.home.work.leave.ui.LeaveMainActivity;
import com.bangjiat.bjt.module.home.work.permission.ui.PermissionMainActivity;
import com.bangjiat.bjt.module.home.work.worker.ui.WorkerListActivity;
import com.bangjiat.bjt.module.main.ui.activity.BaseWhiteToolBarActivity;
import com.bangjiat.bjt.module.me.personaldata.beans.CompanyUserBean;

import butterknife.BindView;
import butterknife.OnClick;

public class WorkMainActivity extends BaseWhiteToolBarActivity {
    @BindView(R.id.tv_company_name)
    TextView tv_company_name;
    @BindView(R.id.card_permission)
    CardView card_permission;

    private static final int DELETE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CompanyUserBean first = CompanyUserBean.first(CompanyUserBean.class);
        tv_company_name.setText(first.getCompanyName());

//        if (Constants.isCompanyAdmin())
        card_permission.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.tv_workers)
    public void clickWorkers(View view) {
        startActivity(new Intent(mContext, WorkerListActivity.class));
    }

    @OnClick(R.id.tv_off_work_apply)
    public void clickOffWork(View view) {
        startActivity(new Intent(mContext, LeaveMainActivity.class));
    }

    @OnClick(R.id.tv_permission)
    public void clickPermission(View view) {
        startActivityForResult(new Intent(mContext, PermissionMainActivity.class), DELETE);
    }

    @OnClick(R.id.tv_kaoqin)
    public void clickKaoqin(View view) {
        startActivity(new Intent(mContext, KaoqinMainActivity.class));
    }

    @OnClick(R.id.tv_company_name)
    public void clickCompanyName(View view) {
        startActivityForResult(new Intent(mContext, EditCompanyActivity.class), DELETE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == DELETE) {
                finish();
            }
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_work_main;
    }

    @Override
    protected String getTitleStr() {
        return "工作台";
    }
}
