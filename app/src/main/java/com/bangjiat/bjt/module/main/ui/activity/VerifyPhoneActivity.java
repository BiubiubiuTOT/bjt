package com.bangjiat.bjt.module.main.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.adorkable.iosdialog.AlertDialog;
import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.BaseActivity;
import com.bangjiat.bjt.common.ClearEditText;
import com.bangjiat.bjt.module.main.account.beans.ValidateCodeInput;
import com.bangjiat.bjt.module.main.account.contract.ValidateCodeContract;
import com.bangjiat.bjt.module.main.account.presenter.ValidateCodePresenter;
import com.dou361.dialogui.DialogUIUtils;
import com.githang.statusbar.StatusBarCompat;
import com.orhanobut.logger.Logger;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 验证手机号
 */
public class VerifyPhoneActivity extends BaseActivity implements ValidateCodeContract.View {
    @BindView(R.id.et_code)
    ClearEditText et_code;
    @BindView(R.id.et_phone)
    ClearEditText et_phone;
    @BindView(R.id.tv_getCode)
    TextView tv_getCode;
    private Dialog dialog;
    private ValidateCodeContract.Presenter presenter;
    private String phone;
    private static final int START = 1;
    private static final int STOP = 2;
    private int time = 60;
    private static final int UPDATE_PHONE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.white));
        presenter = new ValidateCodePresenter(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_verify_phone;
    }


    @OnClick(R.id.iv_back)
    public void clickBack(View view) {
        finish();
    }

    @OnClick(R.id.btn_verify)
    public void clickVerify(View view) {
        phone = et_phone.getText().toString();
        presenter.validateCode(new ValidateCodeInput(phone,
                et_code.getText().toString()));
    }

    @OnClick(R.id.tv_getCode)
    public void getCode(View v) {
        presenter.getRegisterCode(et_phone.getText().toString(), 2);
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
        Intent intent = new Intent(mContext, ModifyPhoneActivity.class);
        intent.putExtra("data", phone);
        startActivityForResult(intent, UPDATE_PHONE);
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
