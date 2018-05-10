package com.bangjiat.bjt.module.me.personaldata.ui;

import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.common.GlideImageLoader;
import com.bangjiat.bjt.module.main.ui.activity.BaseWhiteToolBarActivity;
import com.bangjiat.bjt.module.me.personaldata.beans.UserInfo;
import com.bangjiat.bjt.module.me.personaldata.contract.UpdateUserInfoContract;
import com.bangjiat.bjt.module.me.personaldata.presenter.UpdateUserInfoPresenter;
import com.bumptech.glide.Glide;
import com.dou361.dialogui.DialogUIUtils;
import com.orhanobut.logger.Logger;
import com.yancy.gallerypick.config.GalleryConfig;
import com.yancy.gallerypick.config.GalleryPick;
import com.yancy.gallerypick.inter.IHandlerCallBack;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class PersonalHeadActivity extends BaseWhiteToolBarActivity implements UpdateUserInfoContract.View {
    @BindView(R.id.iv_head)
    ImageView iv_head;
    private List<String> path;
    private GalleryConfig galleryConfig;
    private final int PERMISSIONS_REQUEST_READ_CONTACTS = 8;
    private Dialog dialog;
    private UpdateUserInfoContract.Presenter presenter;
    private UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        presenter = new UpdateUserInfoPresenter(this);
        userInfo = UserInfo.first(UserInfo.class);
        path = new ArrayList<>();
        Glide.with(mContext).load(userInfo.getAvatar()).centerCrop().into(iv_head);
        galleryConfig = new GalleryConfig.Builder()
                .imageLoader(new GlideImageLoader())
                .iHandlerCallBack(iHandlerCallBack)
                .provider("com.bangjiat.bjt.fileprovider")
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

    private String url;
    IHandlerCallBack iHandlerCallBack = new IHandlerCallBack() {
        @Override
        public void onStart() {
            Logger.d("onStart: 开启");
        }

        @Override
        public void onSuccess(List<String> photoList) {
            Logger.d("onSuccess: 返回数据");
            url = photoList.get(0);
            Logger.d(url);
            presenter.uploadUserHead(url);
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

    @Override
    public void showDialog() {
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        } else
            dialog = DialogUIUtils.showLoadingVertical(mContext, "加载中").show();
    }

    @Override
    public void dismissDialog() {
        if (dialog != null)
            dialog.dismiss();
    }

    @Override
    public void updateUserInfoSuccess(UserInfo info) {
        Toast.makeText(mContext, "修改成功", Toast.LENGTH_SHORT).show();
        Glide.with(mContext).load(url).centerCrop().into(iv_head);
        userInfo.delete();
        info.save();
        EventBus.getDefault().post(info);
    }

    @Override
    public void updateUserInfoFail(String err) {
        Toast.makeText(mContext, "修改失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void uploadUserHeadSuccess(String url) {
        userInfo.setAvatar(url);
        presenter.updateUserInfo(DataUtil.getToken(mContext), userInfo);
    }

    @Override
    public void uploadUserHeadFail(String err) {
        Toast.makeText(mContext, "修改失败", Toast.LENGTH_SHORT).show();
    }
}
