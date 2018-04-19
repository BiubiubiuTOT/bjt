package com.bangjiat.bangjiaapp.module.main.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.WindowManager;
import android.widget.Toast;

import com.bangjiat.bangjiaapp.R;
import com.bangjiat.bangjiaapp.common.BaseActivity;
import com.bangjiat.bangjiaapp.common.DataUtil;
import com.bangjiat.bangjiaapp.module.account.ui.LoginActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 引导页
 */
public class SplashActivity extends BaseActivity {
    private static final String[] permissionsArray = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
    };
    private List<String> permissionsList;
    private static final int REQUEST_CODE_ASK_PERMISSIONS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        checkRequiredPermission();
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
                    startActivity(new Intent(mContext, DataUtil.isLogin(mContext) ? MainActivity.class : LoginActivity.class));
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
