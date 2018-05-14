package com.bangjiat.bjt.module.me.setting.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.adorkable.iosdialog.AlertDialog;
import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.module.main.ui.activity.BaseToolBarActivity;
import com.bangjiat.bjt.module.me.setting.contract.UpdatePasswordContract;
import com.bangjiat.bjt.module.me.setting.presenter.UpdatePasswordPresenter;
import com.dou361.dialogui.DialogUIUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class UpdatePasswordActivity extends BaseToolBarActivity implements UpdatePasswordContract.View {
    @BindView(R.id.et_old_password)
    EditText et_old_password;
    @BindView(R.id.et_new_password)
    EditText et_new_password;
    @BindView(R.id.et_new_password_2)
    EditText et_new_password_2;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    private Dialog dialog;
    private UpdatePasswordContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @OnClick(R.id.tv_forget_password)
    public void clickForgetPassword(View view) {
        startActivity(new Intent(mContext, ForgetPasswordActivity.class));
        finish();
    }

    private void initView() {
        presenter = new UpdatePasswordPresenter(this);
        tv_phone.setText(DataUtil.getPhone(mContext));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_update_password;
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");

        TextView tv_title = toolbar.findViewById(R.id.toolbar_title);
        TextView tv_done = toolbar.findViewById(R.id.toolbar_other);

        tv_title.setText("登录密码修改");
        tv_title.setTextColor(getResources().getColor(R.color.black));
        tv_done.setText("完成");
        tv_done.setTextColor(getResources().getColor(R.color.mine_bg));
        toolbar.setNavigationIcon(R.mipmap.back_black);
        toolbar.setBackgroundColor(getResources().getColor(R.color.white));

        tv_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.updatePassword(DataUtil.getToken(mContext), et_old_password.getText().toString(),
                        et_new_password.getText().toString(), et_new_password_2.getText().toString(), DataUtil.getUserId(mContext));
            }
        });
    }

    @Override
    public void showDialog() {
        if (dialog != null) {
            if (!dialog.isShowing())
                dialog.show();
        } else
            dialog = DialogUIUtils.showLoadingVertical(mContext, "加载中").show();
    }

    @Override
    public void dismissDialog() {
        if (dialog != null)
            dialog.dismiss();
    }

    @Override
    public void success() {
        showDialog("登陆密码修改成功", true);
    }

    @Override
    public void showErr(String err) {
        showDialog(err, false);
    }

    private void showDialog(String msg, final boolean isFinish) {
        new AlertDialog(mContext).builder().setMsg(msg).
                setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (isFinish)
                            finish();
                    }
                }).show();
    }
}
