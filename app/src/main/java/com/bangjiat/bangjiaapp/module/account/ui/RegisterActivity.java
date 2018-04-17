package com.bangjiat.bangjiaapp.module.account.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bangjiat.bangjiaapp.R;
import com.bangjiat.bangjiaapp.common.BaseActivity;
import com.bangjiat.bangjiaapp.common.BaseResult;
import com.bangjiat.bangjiaapp.common.DataUtil;
import com.bangjiat.bangjiaapp.common.DialogPopup;
import com.bangjiat.bangjiaapp.module.account.contract.RegisterContract;
import com.bangjiat.bangjiaapp.module.account.presenter.RegisterPresenter;
import com.bangjiat.bangjiaapp.module.main.ui.activity.AgreementActivity;
import com.bangjiat.bangjiaapp.module.main.ui.activity.MainActivity;
import com.dou361.dialogui.DialogUIUtils;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 注册页面
 */
public class RegisterActivity extends BaseActivity implements RegisterContract.View {
    @BindView(R.id.et_code)
    EditText et_code;
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.tv_getCode)
    TextView tv_getCode;

    private static final int START = 1;
    private static final int STOP = 2;

    private RegisterContract.Presenter presenter;
    private String phone, password, code;
    private int time = 60;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new RegisterPresenter(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_register;
    }

    @OnClick(R.id.tv_agreement)
    public void clickAgreement(View view) {
        startActivity(new Intent(mContext, AgreementActivity.class));
    }

    @OnClick(R.id.iv_back)
    public void clickBack(View view) {
        finish();
    }

    @OnClick(R.id.btn_register)
    public void clickRegister(View view) {
        phone = et_phone.getText().toString();
        password = et_password.getText().toString();
        code = et_code.getText().toString();
        presenter.register(phone, password, code);
    }

    @OnClick(R.id.tv_getCode)
    public void getCode(View v) {
        presenter.getRegisterCode(et_phone.getText().toString());
    }

    @Override
    public void showDialog() {
        dialog = DialogUIUtils.showLoadingVertical(this, "加载中...").show();
    }

    @Override
    public void dismissDialog() {
        if (dialog != null)
            dialog.dismiss();
    }

    @Override
    public void showError(String err) {
        DialogPopup popup = new DialogPopup(this, err, R.mipmap.popup_error);
        popup.setBlurBackgroundEnable(true);
        popup.showPopupWindow();
    }

    @Override
    public void getCodeSuccess() {
        Toast.makeText(mContext, "验证码已发送,请注意查收", Toast.LENGTH_LONG).show();
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

    @Override
    public void registerSuccess(BaseResult<String> registerResult) {
        DataUtil.setToken(mContext, registerResult.getData());
        DataUtil.setAccount(mContext, phone, password);
        DataUtil.setLogin(mContext, true);

        startActivity(new Intent(mContext, MainActivity.class));
        finish();
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
}
