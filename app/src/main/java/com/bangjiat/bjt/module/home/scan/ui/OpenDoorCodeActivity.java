package com.bangjiat.bjt.module.home.scan.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.main.ui.activity.BaseWhiteToolBarActivity;

import butterknife.BindView;
import cn.bertsir.zbar.QRUtils;

public class OpenDoorCodeActivity extends BaseWhiteToolBarActivity {
    @BindView(R.id.iv_code)
    ImageView iv_code;
    private Bitmap mBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        mBitmap = QRUtils.getInstance().createQRCodeAddLogo("aa", BitmapFactory.decodeResource(getResources(), R.mipmap.bjt));
        iv_code.setImageBitmap(mBitmap);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_open_door_code;
    }

    @Override
    protected String getTitleStr() {
        return "开门二维码";
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
