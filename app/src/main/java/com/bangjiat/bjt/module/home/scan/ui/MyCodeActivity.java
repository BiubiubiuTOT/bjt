package com.bangjiat.bjt.module.home.scan.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.DesUtil;
import com.bangjiat.bjt.module.home.scan.beans.QrCodeDataUser;
import com.bangjiat.bjt.module.main.ui.activity.BaseColorToolBarActivity;
import com.bangjiat.bjt.module.me.personaldata.beans.UserInfo;
import com.bangjiat.bjt.module.secretary.contact.util.GlideCircleTransform;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import cn.bertsir.zbar.QRUtils;

public class MyCodeActivity extends BaseColorToolBarActivity {
    @BindView(R.id.iv_code)
    ImageView iv_code;
    @BindView(R.id.iv_icon)
    ImageView iv_icon;
    @BindView(R.id.tv_name)
    TextView tv_name;
    private Bitmap mBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        UserInfo userInfo = UserInfo.first(UserInfo.class);
        if (userInfo != null) {
            QrCodeDataUser user = new QrCodeDataUser(userInfo.getUserId(), userInfo.getNickname(), userInfo.getPhone(),
                    "http://img5.duitang.com/uploads/item/201611/18/20161118090311_Cw2dU.jpeg", 2);

            String json = new Gson().toJson(user);
            String bjt = null;
            try {
                bjt = DesUtil.encrypt(json);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Logger.d(bjt);
            mBitmap = QRUtils.getInstance().createQRCode(bjt);
            iv_code.setImageBitmap(mBitmap);
            tv_name.setText(user.getNickname());
            Glide.with(mContext).load("http://img5.duitang.com/uploads/item/201611/18/20161118090311_Cw2dU.jpeg").centerCrop().
                    transform(new GlideCircleTransform(this)).into(iv_icon);
        }
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
