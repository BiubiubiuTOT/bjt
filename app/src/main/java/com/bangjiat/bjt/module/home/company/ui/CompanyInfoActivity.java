package com.bangjiat.bjt.module.home.company.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.adorkable.iosdialog.AlertDialog;
import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.module.home.company.beans.CompanyDetailResult;
import com.bangjiat.bjt.module.home.company.beans.IntoCompanyInput;
import com.bangjiat.bjt.module.home.company.contract.IntoCompanyContract;
import com.bangjiat.bjt.module.home.company.presenter.IntoCompanyPresenter;
import com.bangjiat.bjt.module.home.scan.beans.QrCodeDataCompany;
import com.bangjiat.bjt.module.main.ui.activity.BaseWhiteToolBarActivity;
import com.bangjiat.bjt.module.me.personaldata.beans.CompanyUserBean;
import com.bangjiat.bjt.module.me.personaldata.ui.PersonalDataActivity;
import com.dou361.dialogui.DialogUIUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.orhanobut.logger.Logger;

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
    @BindView(R.id.tv_add_company)
    TextView tv_add_company;
    private Dialog dialog;
    private IntoCompanyContract.Presenter presenter;
    private CompanyDetailResult company;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        if (!Constants.isIntoCompany()) tv_add_company.setVisibility(View.VISIBLE);

        presenter = new IntoCompanyPresenter(this);
        String str = getIntent().getStringExtra("data");
        try {
            QrCodeDataCompany company = new Gson().fromJson(str, QrCodeDataCompany.class);
            if (company != null)
                presenter.getCompanyDetail(DataUtil.getToken(mContext), company.getCid());
            else {
                Toast.makeText(mContext, "二维码识别失败", Toast.LENGTH_SHORT).show();
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
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
        bean.setCompanyName(company.getName());
        bean.save();
        Toast.makeText(mContext, "加入公司成功", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void fail(String err) {
        Logger.e(err);
        if (err.equals("用户姓名为空")) {
            showDia();
        } else
            Constants.showSuccessExitDialog(this, "二维码识别失败");

    }

    @Override
    public void getCompanyDetailSuccess(CompanyDetailResult result) {
        if (result != null) {
            company = result;
            tv_name.setText(result.getName());
            tv_address.setText(result.getAddress());
            tv_trade.setText(result.getIndustry());
        } else {
            fail("公司不存在");
        }
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
