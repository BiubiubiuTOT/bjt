package com.bangjiat.bjt.module.home.work.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.adorkable.iosdialog.AlertDialog;
import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.module.home.company.beans.CompanyDetailResult;
import com.bangjiat.bjt.module.home.company.contract.CompanyContract;
import com.bangjiat.bjt.module.home.company.contract.IntoCompanyContract;
import com.bangjiat.bjt.module.home.company.presenter.CompanyPresenter;
import com.bangjiat.bjt.module.home.company.presenter.IntoCompanyPresenter;
import com.bangjiat.bjt.module.main.ui.activity.BaseToolBarActivity;
import com.bangjiat.bjt.module.me.personaldata.beans.CompanyUserBean;
import com.dou361.dialogui.DialogUIUtils;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnClick;

public class EditCompanyActivity extends BaseToolBarActivity implements CompanyContract.View, IntoCompanyContract.View {
    @BindView(R.id.tv_company_name)
    TextView tv_company;
    @BindView(R.id.et_industry)
    EditText et_industry;
    @BindView(R.id.et_address)
    EditText et_address;
    @BindView(R.id.iv_industry)
    ImageView iv_industry;
    @BindView(R.id.iv_address)
    ImageView iv_address;

    private Dialog dialog;
    private CompanyContract.Presenter presenter;
    private IntoCompanyContract.Presenter intoPresenter;
    private String token;
    private CompanyDetailResult result;
    private CompanyUserBean first;
    private TextView tv_title;
    private TextView tv_done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        if (!Constants.hasPermission()) {
            iv_address.setVisibility(View.GONE);
            iv_industry.setVisibility(View.GONE);
            et_address.setEnabled(false);
            et_industry.setEnabled(false);
            tv_done.setVisibility(View.GONE);
            tv_title.setText("公司信息");
        }

        first = CompanyUserBean.first(CompanyUserBean.class);
        intoPresenter = new IntoCompanyPresenter(this);
        token = DataUtil.getToken(mContext);
        presenter = new CompanyPresenter(this);
        if (first != null) {
            intoPresenter.getCompanyDetail(token, first.getCompanyId());
        }
    }

    @OnClick(R.id.tv_delete)
    public void clickDelete(View view) {
        showDia();
    }

    @OnClick(R.id.iv_industry)
    public void clickIndustry(View view) {
        et_industry.setText("");
    }

    @OnClick(R.id.iv_address)
    public void clickAddress(View view) {
        et_address.setText("");
    }

    private void showDia() {
        new AlertDialog(mContext).builder().setMsg("确认退出公司吗?").
                setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (Constants.hasPermission()) {
                            error("管理员不能退出公司");
                        } else
                            presenter.exitCompany(token);
                    }
                }).setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }).show();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_edit_company;
    }

    @Override
    protected void initToolbar(final Toolbar toolbar) {
        toolbar.setTitle("");
        tv_title = toolbar.findViewById(R.id.toolbar_title);
        tv_done = toolbar.findViewById(R.id.toolbar_other);

        tv_title.setText("编辑公司");
        tv_done.setText("完成");
        toolbar.setNavigationIcon(R.mipmap.back_black);
        toolbar.setBackgroundColor(getResources().getColor(R.color.white));
        tv_done.setTextColor(getResources().getColor(R.color.black));
        tv_title.setTextColor(getResources().getColor(R.color.black));

        tv_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.setAddress(et_address.getText().toString());
                result.setIndustry(et_industry.getText().toString());

                presenter.updateCompany(token, result);
            }
        });
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

    }

    @Override
    public void fail(String err) {

    }

    @Override
    public void getCompanyDetailSuccess(CompanyDetailResult result) {
        if (result != null) {
            this.result = result;
            tv_company.setText(result.getName());
            et_industry.setText(result.getIndustry());
            et_address.setText(result.getAddress());
        }
    }

    @Override
    public void addCompanySuccess() {

    }

    @Override
    public void addCompanyFail(String err) {

    }

    @Override
    public void updateCompanySuccess(String str) {
        Constants.showSuccessExitDialog(this, "公司修改成功");
    }

    @Override
    public void exitCompanySuccess(String token) {
        first.delete();
        show();
    }

    private void show() {
        new AlertDialog(this).builder().setMsg("退出公司成功").setCancelable(false).
                setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setResult(RESULT_OK);
                        finish();
                    }
                }).show();
    }

    @Override
    public void error(String err) {
        Logger.e(err);
        Constants.showErrorDialog(this, err);
    }
}
