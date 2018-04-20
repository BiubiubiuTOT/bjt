package com.bangjiat.bangjiaapp.module.me.personaldata.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bangjiat.bangjiaapp.R;
import com.bangjiat.bangjiaapp.common.GlideImageLoader;
import com.bangjiat.bangjiaapp.module.main.ui.activity.BaseWhiteToolBarActivity;
import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;
import com.yancy.gallerypick.config.GalleryConfig;
import com.yancy.gallerypick.config.GalleryPick;
import com.yancy.gallerypick.inter.IHandlerCallBack;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class PersonalHeadActivity extends BaseWhiteToolBarActivity {
    @BindView(R.id.iv_head)
    ImageView iv_head;
    private List<String> path;
    private GalleryConfig galleryConfig;
    private final int PERMISSIONS_REQUEST_READ_CONTACTS = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        path = new ArrayList<>();
        Glide.with(mContext).load(R.mipmap.head).centerCrop().into(iv_head);
        galleryConfig = new GalleryConfig.Builder()
                .imageLoader(new GlideImageLoader())
                .iHandlerCallBack(iHandlerCallBack)
                .provider("com.bangjiat.bangjiaapp.fileprovider")
                .pathList(path)
                .crop(true)
                .crop(true, 1, 1, 500, 500)
                .filePath("/Gallery/Pictures")
                .build();
    }

    @OnClick(R.id.tv_from_album)
    public void clickAlbum(View view) {
        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // 提示用户如果想要正常使用，要手动去设置中授权。
                Toast.makeText(mContext, "请在 设置-应用管理 中开启此应用的储存授权。", Toast.LENGTH_SHORT).show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSIONS_REQUEST_READ_CONTACTS);
            }
        } else {
            // 进行正常操作
            galleryConfig.getBuilder().isOpenCamera(false).isShowCamera(false).build();
            GalleryPick.getInstance().setGalleryConfig(galleryConfig).open(this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Logger.d("同意授权");
                galleryConfig.getBuilder().isOpenCamera(false).isShowCamera(false).build();
                GalleryPick.getInstance().setGalleryConfig(galleryConfig).open(this);
                // 进行正常操作。
            } else {
                Logger.d("拒绝授权");
            }
        }
    }

    IHandlerCallBack iHandlerCallBack = new IHandlerCallBack() {
        @Override
        public void onStart() {
            Logger.d("onStart: 开启");
        }

        @Override
        public void onSuccess(List<String> photoList) {
            Logger.d("onSuccess: 返回数据");
            for (String s : photoList) {
                Logger.d(s);
                Glide.with(mContext).load(s).centerCrop().into(iv_head);
            }
            path.clear();
        }

        @Override
        public void onCancel() {
            Logger.d("onCancel: 取消");
        }

        @Override
        public void onFinish() {
            Logger.d("onFinish: 结束");
        }

        @Override
        public void onError() {
            Logger.d("onError: 出错");
        }
    };

    @OnClick(R.id.tv_take_photo)
    public void clickTakePhoto(View view) {
        GalleryPick.getInstance().setGalleryConfig(galleryConfig).openCamera(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_personal_head;
    }

    @Override
    protected String getTitleStr() {
        return "个人头像";
    }
}
