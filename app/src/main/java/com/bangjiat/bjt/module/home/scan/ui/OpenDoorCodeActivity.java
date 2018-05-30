package com.bangjiat.bjt.module.home.scan.ui;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.adorkable.iosdialog.AlertDialog;
import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.module.home.scan.contract.OpenDoorContract;
import com.bangjiat.bjt.module.home.scan.presenter.OpenDoorPresenter;
import com.bangjiat.bjt.module.main.ui.activity.BaseWhiteToolBarActivity;
import com.dou361.dialogui.DialogUIUtils;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import cn.bertsir.zbar.QRUtils;

public class OpenDoorCodeActivity extends BaseWhiteToolBarActivity implements OpenDoorContract.View {
    @BindView(R.id.iv_code)
    ImageView iv_code;
    private Bitmap mBitmap;
    private Dialog dialog;
    private OpenDoorContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        presenter = new OpenDoorPresenter(this);
        presenter.getDoorMessage(DataUtil.getToken(mContext));
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
    public void error(String err) {
        Logger.e(err);
        new AlertDialog(mContext).builder().setMsg(err).setCancelable(false).
                setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                }).show();
    }


    @Override
    public void success(String str) {
        Logger.d(str);
        mBitmap = QRUtils.getInstance().createQRCodeAddLogo(str, BitmapFactory.decodeResource(getResources(), R.mipmap.bjt));
        iv_code.setImageBitmap(mBitmap);
    }
}
