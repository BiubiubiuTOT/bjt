package com.bangjiat.bjt.module.home.scan.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.home.scan.beans.QrCodeDataCompany;
import com.bangjiat.bjt.module.main.ui.activity.BaseColorToolBarActivity;
import com.bangjiat.bjt.module.me.personaldata.beans.CompanyUserBean;
import com.google.gson.Gson;

import butterknife.BindView;
import cn.bertsir.zbar.QRUtils;

public class CompanyCodeActivity extends BaseColorToolBarActivity {
    @BindView(R.id.iv_code)
    ImageView iv_code;
    @BindView(R.id.tv_name)
    TextView tv_name;
    private Bitmap mBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        CompanyUserBean companyUserBean = CompanyUserBean.first(CompanyUserBean.class);
        if (companyUserBean != null) {
            QrCodeDataCompany company = new QrCodeDataCompany(companyUserBean.getCompanyId(), 1);

            String json = new Gson().toJson(company);
            mBitmap = QRUtils.getInstance().createQRCode(json);
            iv_code.setImageBitmap(mBitmap);
            tv_name.setText(companyUserBean.getCompanyName());
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_company_code;
    }

    @Override
    protected String getTitleStr() {
        return "公司名片";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBitmap != null) {
            mBitmap.recycle();
            mBitmap = null;
        }
    }
}
