package com.bangjiat.bjt.module.home.work.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.home.work.kaoqin.ui.KaoqinMainActivity;
import com.bangjiat.bjt.module.home.work.permission.ui.PermissionMainActivity;
import com.bangjiat.bjt.module.home.work.worker.ui.WorkerListActivity;
import com.bangjiat.bjt.module.home.work.leave.ui.LeaveMainActivity;
import com.bangjiat.bjt.module.main.ui.activity.BaseWhiteToolBarActivity;

import butterknife.OnClick;

public class WorkMainActivity extends BaseWhiteToolBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        startActivity(new Intent(mContext, PermissionMainActivity.class));
    }

    @OnClick(R.id.tv_kaoqin)
    public void clickKaoqin(View view) {
        startActivity(new Intent(mContext, KaoqinMainActivity.class));
    }

    @OnClick(R.id.tv_company_name)
    public void clickCompanyName(View view) {
        startActivity(new Intent(mContext, EditCompanyActivity.class));
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
