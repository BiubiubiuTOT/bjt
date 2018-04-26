package com.bangjiat.bjt.module.home.company.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.module.home.company.beans.CompanyInput;
import com.bangjiat.bjt.module.home.company.contract.AddCompanyContract;
import com.bangjiat.bjt.module.home.company.presenter.AddCompanyPresenter;
import com.bangjiat.bjt.module.main.ui.activity.BaseToolBarActivity;
import com.dou361.dialogui.DialogUIUtils;

import butterknife.BindView;

public class AddCompanyActivity extends BaseToolBarActivity implements AddCompanyContract.View {
    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.et_address)
    EditText et_address;
    @BindView(R.id.et_trade)
    EditText et_trade;
    private Dialog dialog;
    private AddCompanyContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new AddCompanyPresenter(this);
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
    }

    @Override
    public void addCompanyFail(String err) {
        Toast.makeText(mContext, "新建公司失败：" + err, Toast.LENGTH_SHORT).show();
    }
}
