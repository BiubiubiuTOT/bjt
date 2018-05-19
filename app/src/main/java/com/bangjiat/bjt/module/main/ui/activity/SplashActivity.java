package com.bangjiat.bjt.module.main.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.WindowManager;
import android.widget.Toast;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.BaseActivity;
import com.bangjiat.bjt.common.BaseResult;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.module.main.account.beans.Account;
import com.bangjiat.bjt.module.main.account.contract.LoginContract;
import com.bangjiat.bjt.module.main.account.presenter.LoginPresenter;
import com.bangjiat.bjt.module.main.account.ui.LoginActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 引导页
 */
public class SplashActivity extends BaseActivity implements LoginContract.View {
    private static final String[] permissionsArray = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };
    private List<String> permissionsList;
    private static final int REQUEST_CODE_ASK_PERMISSIONS = 1;
    private LoginContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        checkRequiredPermission();

        presenter = new LoginPresenter(this);
    }

    private void checkRequiredPermission() {
        permissionsList = new ArrayList<>();
        for (String permission : permissionsArray) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionsList.add(permission);
            }
        }
        if (permissionsList.size() > 0) {
            ActivityCompat.requestPermissions(SplashActivity.this,
                    permissionsList.toArray(new String[permissionsList.size()]), REQUEST_CODE_ASK_PERMISSIONS);
        } else toMain();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                boolean isSet = true;
                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        isSet = false;
                    }
                }
                if (isSet) {
                    toMain();
                } else {
                    Toast.makeText(mContext, "为了确保程序正常运行，请允许该权限", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_splash;
    }

    private void toMain() {
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(2000);
                    boolean login = DataUtil.isLogin(mContext);
                    if (!login) {
                        startActivity(new Intent(mContext, LoginActivity.class));
                        finish();
                    } else {
                        Account account = DataUtil.getAccount(mContext);
                        presenter.login(account.getPhone(), account.getPassword());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public void showDialog() {
    }

    @Override
    public void dismissDialog() {
    }

    @Override
    public void showError(String err) {
        Toast.makeText(mContext, err, Toast.LENGTH_SHORT).show();
        DataUtil.setLogin(mContext, false);

        startActivity(new Intent(mContext, LoginActivity.class));
        finish();
    }

    @Override
    public void loginSuccess(BaseResult<String> result) {
        DataUtil.setToken(mContext, result.getData());
        DataUtil.setLogin(mContext, true);

        startActivity(new Intent(mContext, MainActivity.class));
        finish();
    }

}
