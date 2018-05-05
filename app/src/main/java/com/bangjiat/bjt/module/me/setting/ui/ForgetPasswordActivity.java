package com.bangjiat.bjt.module.me.setting.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.main.ui.activity.BaseToolBarActivity;
import com.bangjiat.bjt.module.me.setting.contract.ForgetPasswordContract;
import com.bangjiat.bjt.module.me.setting.presenter.ForgetPasswordPresenter;
import com.dou361.dialogui.DialogUIUtils;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

public class ForgetPasswordActivity extends BaseToolBarActivity implements ForgetPasswordContract.View {
    @BindView(R.id.et_new_password)
    EditText et_new_password;
    @BindView(R.id.et_new_password_2)
    EditText et_new_password_2;
    @BindView(R.id.tv_getCode)
    TextView tv_getCode;
    private static final int START = 1;
    private static final int STOP = 2;
    private int time = 60;
    private Dialog dialog;
    private ForgetPasswordContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ForgetPasswordPresenter(this);
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

            }
        });
    }

    @OnClick(R.id.tv_getCode)
    public void clickGetCode(View view) {
        presenter.getRegisterCode("17685304679", 1);
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
                    break;
            }
        }
    };

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
    public void getCodeFail(String err) {
        Toast.makeText(mContext, err, Toast.LENGTH_LONG).show();
    }

    @Override
    public void getCodeSuccess() {
        Toast.makeText(mContext, "验证码已下发", Toast.LENGTH_LONG).show();
        showTime();
    }
}
