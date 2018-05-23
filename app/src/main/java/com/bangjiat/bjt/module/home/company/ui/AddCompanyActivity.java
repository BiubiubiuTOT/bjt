package com.bangjiat.bjt.module.home.company.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.adorkable.iosdialog.AlertDialog;
import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.ClearEditText;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.module.home.company.beans.CompanyInput;
import com.bangjiat.bjt.module.home.company.contract.CompanyContract;
import com.bangjiat.bjt.module.home.company.presenter.CompanyPresenter;
import com.bangjiat.bjt.module.main.ui.activity.BaseToolBarActivity;
import com.bangjiat.bjt.module.me.personaldata.beans.CompanyUserBean;
import com.bangjiat.bjt.module.me.personaldata.beans.UserInfoBean;
import com.bangjiat.bjt.module.me.personaldata.contract.GetUserInfoContract;
import com.bangjiat.bjt.module.me.personaldata.presenter.GetUserInfoPresenter;
import com.bangjiat.bjt.module.me.personaldata.ui.PersonalDataActivity;
import com.dou361.dialogui.DialogUIUtils;

import butterknife.BindView;

public class AddCompanyActivity extends BaseToolBarActivity implements CompanyContract.View, GetUserInfoContract.View {
    @BindView(R.id.et_name)
    ClearEditText et_name;
    @BindView(R.id.et_address)
    ClearEditText et_address;
    @BindView(R.id.et_trade)
    ClearEditText et_trade;
    private Dialog dialog;
    private CompanyContract.Presenter presenter;
    private GetUserInfoContract.Presenter presenter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new CompanyPresenter(this);
        presenter1 = new GetUserInfoPresenter(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_add_company;
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        TextView textView = findViewById(R.id.toolbar_title);
        TextView textView1 = findViewById(R.id.toolbar_other);

        toolbar.setTitle("");
        textView.setText("新建公司");
        textView1.setText("完成");

        textView.setTextColor(getResources().getColor(R.color.black));
        textView1.setTextColor(getResources().getColor(R.color.black));
        toolbar.setBackgroundColor(getResources().getColor(R.color.white));
        toolbar.setNavigationIcon(R.mipmap.back_black);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CompanyInput input = new CompanyInput(et_name.getText().toString(), et_address.getText().toString());
                presenter.addCompany(DataUtil.getToken(mContext), input);
            }
        });
    }

    @Override
    public void showDialog() {
        dialog = DialogUIUtils.showLoadingVertical(this, "加载中...").show();
        dialog.setCancelable(false);
    }

    @Override
    public void dismissDialog() {
        if (dialog != null)
            dialog.dismiss();
    }

    @Override
    public void addCompanySuccess() {
        Toast.makeText(mContext, "新建公司成功！", Toast.LENGTH_SHORT).show();
        presenter1.getUserInfo(DataUtil.getToken(mContext));
        showDialog();
    }

    @Override
    public void addCompanyFail(String err) {
        if (err.equals("用户姓名为空")) {
            showDia();
        } else {
            Constants.showErrorDialog(mContext, err);
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

    @Override
    public void updateCompanySuccess(String str) {

    }

    @Override
    public void exitCompanySuccess(String token) {

    }

    @Override
    public void error(String err) {

    }

    @Override
    public void getUserInfoFail(String err) {
        dismissDialog();
        Toast.makeText(mContext, "加入公司失败", Toast.LENGTH_LONG).show();
    }


    @Override
    public void getUserInfoSuccess(UserInfoBean bean) {
        dismissDialog();
        CompanyUserBean companyUser = bean.getCompanyUser();

        if (companyUser != null)
            companyUser.save();

        finish();
    }
}
