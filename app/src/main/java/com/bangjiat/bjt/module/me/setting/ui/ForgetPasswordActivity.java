package com.bangjiat.bjt.module.me.setting.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.adorkable.iosdialog.AlertDialog;
import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.module.main.account.beans.ValidateCodeInput;
import com.bangjiat.bjt.module.main.account.contract.RecoveredPasswordContract;
import com.bangjiat.bjt.module.main.account.contract.ValidateCodeContract;
import com.bangjiat.bjt.module.main.account.presenter.RecoveredPasswordPresenter;
import com.bangjiat.bjt.module.main.account.presenter.ValidateCodePresenter;
import com.bangjiat.bjt.module.main.ui.activity.BaseToolBarActivity;
import com.dou361.dialogui.DialogUIUtils;
import com.orhanobut.logger.Logger;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

public class ForgetPasswordActivity extends BaseToolBarActivity implements ValidateCodeContract.View, RecoveredPasswordContract.View {
    @BindView(R.id.et_new_password)
    EditText et_new_password;
    @BindView(R.id.et_new_password_2)
    EditText et_new_password_2;
    @BindView(R.id.tv_getCode)
    TextView tv_getCode;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.et_code)
    EditText et_code;
    private Dialog dialog;
    private ValidateCodeContract.Presenter codePresenter;
    private RecoveredPasswordContract.Presenter presenter;
    private String phone;
    private static final int START = 1;
    private static final int STOP = 2;
    private int time = 60;
    private static final int UPDATE_PHONE = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        codePresenter = new ValidateCodePresenter(this);
        presenter = new RecoveredPasswordPresenter(this);
        phone = DataUtil.getPhone(mContext);
        tv_phone.setText(phone);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_forget_password;
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");

        TextView tv_title = toolbar.findViewById(R.id.toolbar_title);
        TextView tv_done = toolbar.findViewById(R.id.toolbar_other);

        tv_title.setText("忘记密码");
        tv_title.setTextColor(getResources().getColor(R.color.black));
        tv_done.setText("完成");
        tv_done.setTextColor(getResources().getColor(R.color.mine_bg));
        toolbar.setNavigationIcon(R.mipmap.back_black);
        toolbar.setBackgroundColor(getResources().getColor(R.color.white));

        tv_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codePresenter.validateCode(new ValidateCodeInput(phone,
                        et_code.getText().toString()));
            }
        });
    }


    @OnClick(R.id.tv_getCode)
    public void getCode(View v) {
        codePresenter.getRegisterCode(phone, 2);
    }


    @Override
    public void showDialog() {
        if (dialog != null && !dialog.isShowing())
            dialog.show();
        else
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

    @Override
    public void getCodeSuccess(String code) {
        Logger.d(code);
        showTime();
        tv_getCode.setTextColor(getResources().getColor(R.color.register_hint));
    }

    private void showTime() {
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (time > 0) {
                    mHandler.sendEmptyMessage(START);
                } else {
                    mHandler.sendEmptyMessage(STOP);
                    timer.cancel();
                }
            }
        }, 0, 1000);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case START:
                    tv_getCode.setEnabled(false);
                    tv_getCode.setText("重新获取(" + time + "s)");
                    time--;
                    break;
                case STOP:
                    tv_getCode.setEnabled(true);
                    time = 60;
                    tv_getCode.setText("获取验证码");
                    tv_getCode.setTextColor(getResources().getColor(R.color.mine_bg));
                    break;
            }
        }
    };

    @Override
    public void getCodeFail(String err) {
        showDialog(err);
    }

    @Override
    public void validateSuccess() {
        presenter.update(phone, et_new_password.getText().toString(), et_new_password_2.getText().toString());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == UPDATE_PHONE) {
                finish();
            }
        }
    }

    @Override
    public void validateFail(String err) {
        showDialog(err);
    }

    private void showDialog(String msg) {
        new AlertDialog(mContext).builder().setMsg(msg).
                setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }).show();
    }


}
