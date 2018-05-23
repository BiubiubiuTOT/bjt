package com.bangjiat.bjt.module.main.ui.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;

import com.adorkable.iosdialog.AlertDialog;
import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.BaseActivity;
import com.bangjiat.bjt.common.ClearEditText;
import com.bangjiat.bjt.module.main.account.contract.RecoveredPasswordContract;
import com.bangjiat.bjt.module.main.account.presenter.RecoveredPasswordPresenter;
import com.dou361.dialogui.DialogUIUtils;
import com.githang.statusbar.StatusBarCompat;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 注册页面
 */
public class ModifyPhoneActivity extends BaseActivity implements RecoveredPasswordContract.View {
    @BindView(R.id.et_new_password)
    ClearEditText et_password;
    @BindView(R.id.et_new_password_2)
    ClearEditText et_new_password_2;
    private String phone;
    private Dialog dialog;
    private RecoveredPasswordContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.white));
        initData();
    }

    private void initData() {
        phone = getIntent().getStringExtra("data");
        if (phone == null)
            finish();
        presenter = new RecoveredPasswordPresenter(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_modify_phone;
    }


    @OnClick(R.id.iv_back)
    public void clickBack(View view) {
        finish();
    }

    @OnClick(R.id.btn_modify)
    public void clickRegister(View view) {
        presenter.update(phone, et_password.getText().toString(), et_new_password_2.getText().toString());
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
    public void error(String err) {
        showDialog(err, false);
    }

    @Override
    public void success() {
        showDialog("修改成功", true);
    }

    private void showDialog(String msg, final boolean isFinish) {
        new AlertDialog(mContext).builder().setMsg(msg).
                setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (isFinish) {
                            setResult(RESULT_OK);
                            finish();
                        }
                    }
                }).show();
    }
}
