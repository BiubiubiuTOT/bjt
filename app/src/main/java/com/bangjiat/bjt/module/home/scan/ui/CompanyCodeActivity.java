package com.bangjiat.bjt.module.home.scan.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.DesUtil;
import com.bangjiat.bjt.module.home.scan.beans.QrCodeDataCompany;
import com.bangjiat.bjt.module.main.ui.activity.BaseColorToolBarActivity;
import com.bangjiat.bjt.module.me.personaldata.beans.CompanyUserBean;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

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
            QrCodeDataCompany company = new QrCodeDataCompany(companyUserBean.getCompanyName(), companyUserBean.getCompanyId(), companyUserBean.getUserId());

            String json = new Gson().toJson(company);
            String bjt = null;
            try {
                bjt = DesUtil.encrypt(json);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Logger.d(bjt);
            mBitmap = QRUtils.getInstance().createQRCode(bjt);
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
