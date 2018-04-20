package com.bangjiat.bangjiaapp.module.home.scan.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.bangjiat.bangjiaapp.R;
import com.bangjiat.bangjiaapp.module.main.ui.activity.BaseColorToolBarActivity;

import butterknife.BindView;
import cn.bertsir.zbar.QRUtils;

public class MyCodeActivity extends BaseColorToolBarActivity {
    @BindView(R.id.iv_code)
    ImageView iv_code;
    private Bitmap mBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        mBitmap = QRUtils.getInstance().createQRCode("测试二维码");
        iv_code.setImageBitmap(mBitmap);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_my_code;
    }

    @Override
    protected String getTitleStr() {
        return "我的名片";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBitmap != null) {
            mBitmap.recycle();
            mBitmap = null;
        }
    }
}
