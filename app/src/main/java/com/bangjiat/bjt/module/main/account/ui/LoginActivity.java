package com.bangjiat.bjt.module.main.account.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.BaseActivity;
import com.bangjiat.bjt.common.BaseResult;
import com.bangjiat.bjt.common.ClearEditText1;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.common.DialogPopup;
import com.bangjiat.bjt.module.main.account.beans.Account;
import com.bangjiat.bjt.module.main.account.contract.LoginContract;
import com.bangjiat.bjt.module.main.account.presenter.LoginPresenter;
import com.bangjiat.bjt.module.main.ui.activity.AgreementActivity;
import com.bangjiat.bjt.module.main.ui.activity.MainActivity;
import com.bangjiat.bjt.module.main.ui.activity.VerifyPhoneActivity;
import com.dou361.dialogui.DialogUIUtils;
import com.githang.statusbar.StatusBarCompat;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录页面
 */
public class LoginActivity extends BaseActivity implements LoginContract.View {
    @BindView(R.id.iv_bg)
    ImageView iv_bg;
    @BindView(R.id.et_phone)
    ClearEditText1 et_phone;
    @BindView(R.id.et_password)
    ClearEditText1 et_password;

    private LoginContract.Presenter presenter;
    private String password, phone;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.white));
        initView();
    }

    @OnClick(R.id.btn_login)
    public void clickLogin(View view) {
        password = et_password.getText().toString();
        phone = et_phone.getText().toString();
        presenter.login(phone, password);
    }

    @OnClick(R.id.tv_register)
    public void clickRegister(View view) {
        startActivity(new Intent(mContext, RegisterActivity.class));
    }

    @OnClick(R.id.tv_forget_password)
    public void clickForgetPassword(View view) {
        startActivity(new Intent(mContext, VerifyPhoneActivity.class));
    }

    @OnClick(R.id.tv_agreement)
    public void clickAgreement(View view) {
        startActivity(new Intent(mContext, AgreementActivity.class));
    }

    private void initView() {
        Account account = DataUtil.getAccount(mContext);
        et_phone.setText(account.getPhone());
        et_password.setText(account.getPassword());

        presenter = new LoginPresenter(this);
//        Glide.with(LoginActivity.this).load(R.mipmap.login_bg)
//               .dontAnimate()
//                .into(iv_bg);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
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
    public void showError(String err) {
        DialogPopup popup = new DialogPopup(this, err, R.mipmap.popup_error);
        popup.setBlurBackgroundEnable(true);
        popup.setShowAnimation(null);
        popup.showPopupWindow();
    }

    @Override
    public void loginSuccess(BaseResult<String> result) {
        DataUtil.setToken(mContext, result.getData());
        DataUtil.setAccount(mContext, phone, password);
        DataUtil.setLogin(mContext, true);
        initPush(phone);

        startActivity(new Intent(mContext, MainActivity.class));
        finish();
    }

    private void initPush(String phone) {
        PushServiceFactory.getCloudPushService().
                bindAccount(phone, new CommonCallback() {
                    @Override
                    public void onSuccess(String s) {
                        Logger.d("阿里推送绑定成功 " + s + " ");
                    }

                    @Override
                    public void onFailed(String s, String s1) {
                        Logger.d("阿里推送绑定失败 " + s + " " + s1);
                    }
                });
    }
}
