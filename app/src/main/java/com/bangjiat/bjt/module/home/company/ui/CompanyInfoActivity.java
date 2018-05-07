package com.bangjiat.bjt.module.home.company.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.adorkable.iosdialog.AlertDialog;
import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.module.home.company.beans.IntoCompanyInput;
import com.bangjiat.bjt.module.home.company.contract.IntoCompanyContract;
import com.bangjiat.bjt.module.home.company.presenter.IntoCompanyPresenter;
import com.bangjiat.bjt.module.home.scan.beans.QrCodeDataCompany;
import com.bangjiat.bjt.module.main.ui.activity.BaseWhiteToolBarActivity;
import com.bangjiat.bjt.module.me.personaldata.beans.CompanyUserBean;
import com.bangjiat.bjt.module.me.personaldata.ui.PersonalDataActivity;
import com.dou361.dialogui.DialogUIUtils;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 公司信息
 */
public class CompanyInfoActivity extends BaseWhiteToolBarActivity implements IntoCompanyContract.View {
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.tv_trade)
    TextView tv_trade;
    QrCodeDataCompany company;
    private Dialog dialog;
    private IntoCompanyContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        presenter = new IntoCompanyPresenter(this);
        String str = getIntent().getStringExtra("data");

        company = new Gson().fromJson(str, QrCodeDataCompany.class);
        if (company != null) {
            tv_name.setText(company.getCompanyName());
        } else {
            Toast.makeText(mContext, "二维码识别失败", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @OnClick(R.id.tv_add_company)
    public void clickAddCompany(View view) {
        presenter.intoCompany(DataUtil.getToken(mContext), new IntoCompanyInput(company.getCompanyId()));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_company_info;
    }

    @Override
    protected String getTitleStr() {
        return "公司信息";
    }

    @Override
    public void showDialog() {
        dialog = DialogUIUtils.showLoadingVertical(mContext, "加载中").show();
    }

    @Override
    public void dismissDialog() {
        if (dialog != null)
            dialog.dismiss();
    }

    @Override
    public void success() {
        CompanyUserBean bean = new CompanyUserBean();
        bean.setCompanyId(company.getCompanyId());
        bean.setCompanyName(company.getCompanyName());
        bean.save();
        Toast.makeText(mContext, "加入公司成功", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void fail(String err) {
        if (err.equals("用户姓名为空")) {
            showDia();
        } else
            Toast.makeText(mContext, err, Toast.LENGTH_SHORT).show();

    }

    private void showDia() {
        new AlertDialog(mContext).builder().setMsg("请先完善个人资料")
                .setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(mContext, PersonalDataActivity.class));
                    }
                }).show();
    }
}
