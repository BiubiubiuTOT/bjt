package com.bangjiat.bangjiaapp.module.company.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bangjiat.bangjiaapp.R;
import com.bangjiat.bangjiaapp.module.scan.ui.ScanActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bertsir.zbar.QrConfig;
import cn.bertsir.zbar.QrManager;


public class AddOrSelectCompanyActivity extends Activity {
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_add_or_select_company);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
    }

    @OnClick(R.id.iv_close)
    public void clickClose(View view) {
        finish();
    }

    @OnClick(R.id.btn_add_company)
    public void clickAddCompany(View view) {
        Intent intent = new Intent(mContext, AddCompanyActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.btn_select_company)
    public void clickSelectCompany(View view) {
        QrConfig options = new QrConfig.Builder().create();
        QrManager.getInstance().init(options).startScan(new QrManager.OnScanResultCallback() {
            @Override
            public void onScanSuccess(String result) {
                Toast.makeText(mContext, result, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, CompanyInfoActivity.class);
                startActivity(intent);
            }
        });

        Intent intent = new Intent(mContext, ScanActivity.class);
        intent.putExtra(QrConfig.EXTRA_THIS_CONFIG, options);
        intent.putExtra(ScanActivity.SCAN_TYPE, 2);
        startActivity(intent);
        finish();
    }

}
