package com.bangjiat.bjt.module.main.ui.fragment;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.BaseFragment;
import com.bangjiat.bjt.module.home.company.ui.AddOrSelectCompanyActivity;
import com.bangjiat.bjt.module.home.notice.beans.NoticeBean;
import com.bangjiat.bjt.module.home.notice.ui.AllNoticeActivity;
import com.bangjiat.bjt.module.home.notice.ui.NoticeItemActivity;
import com.bangjiat.bjt.module.home.scan.ui.ScanActivity;
import com.bangjiat.bjt.module.home.visitor.ui.VisitorActivity;
import com.bumptech.glide.Glide;
import com.joker.api.Permissions4M;
import com.joker.api.wrapper.Wrapper;
import com.orhanobut.logger.Logger;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bertsir.zbar.QrConfig;
import cn.bertsir.zbar.QrManager;
import cn.bingoogolapple.bgabanner.BGABanner;


public class HomeFragment extends BaseFragment {
    @BindView(R.id.banner_guide_content)
    BGABanner mContentBanner;
    private static final int REQUEST_CODE = 1;
    private static final int READ_CODE = 2;
    private static final int CAMERA_CODE = 3;
    private boolean isOpenRead, isOpenCamera;

    protected void initView() {
        mContentBanner.setData(Arrays.asList("http://pic2.ooopic.com/12/42/25/02bOOOPIC95_1024.jpg",
                "http://pic4.nipic.com/20091121/3764872_215617048242_2.jpg",
                "http://imgsrc.baidu.com/image/c0%3Dshijue1%2C0%2C0%2C294%2C40/sign=9b867a04b299a9012f38537575fc600e/4d086e061d950a7b86bee8d400d162d9f2d3c913.jpg"), null);
        mContentBanner.setDelegate(new BGABanner.Delegate<ImageView, String>() {
            @Override
            public void onBannerItemClick(BGABanner banner, ImageView itemView, String model, int position) {
                Toast.makeText(banner.getContext(), "点击了" + position, Toast.LENGTH_SHORT).show();
            }
        });
        mContentBanner.setAdapter(new BGABanner.Adapter<ImageView, String>() {
            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
                Glide.with(itemView.getContext())
                        .load(model)
                        .placeholder(R.drawable.holder).error(R.drawable.holder).dontAnimate().centerCrop()
                        .into(itemView);
            }
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @OnClick(R.id.tv_more_notice)
    public void clickMoreNotice(View view) {
        startActivity(new Intent(mContext, AllNoticeActivity.class));
    }

    @OnClick(R.id.tv_content)
    public void clickContent(View view) {
        Intent intent = new Intent(mContext, NoticeItemActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", new NoticeBean("国庆放假通知：", "国庆七天假期，国际互联网金融特区大厦国庆七天假期，国际互联网金融特区大厦国庆七天假期，国际互联网金融特区大厦国庆七天假期，国际互联网金融特区大厦国庆七天假期，国际互联网金融特国庆七天假期，国际互联网金融特区大厦国庆七天假期，" +
                "国际互联网金融特区大厦国庆七天假期，国际互联网金融特区大厦国庆七天假期，国际互联网金融特区大厦国庆七天假期，国际互联网金融特区大厦。", "2018-03-24 16:54"));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @OnClick(R.id.tv_visitor)
    public void clickVisitor(View view) {
        startActivity(new Intent(mContext, VisitorActivity.class));
    }

    @OnClick(R.id.iv_scan)
    public void clickScan(View view) {
        checkPermission();
    }

    @OnClick(R.id.tv_open_door)
    public void clickOpenDoor(View view) {
//        startActivity(new Intent(mContext, OpenDoorCodeActivity.class));
        startActivity(new Intent(mContext, AddOrSelectCompanyActivity.class));
    }

    private void checkPermission() {
        Permissions4M.get(this)
                .requestPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .requestCodes(READ_CODE, CAMERA_CODE)
                .requestListener(new Wrapper.PermissionRequestListener() {
                    @Override
                    public void permissionGranted(int code) {
                        switch (code) {
                            case READ_CODE:
//                                Toast.makeText(mContext, "读权限授权成功", Toast.LENGTH_SHORT).show();
                                isOpenRead = true;
                                startScan();
                                break;
                            case CAMERA_CODE:
                                isOpenCamera = true;
//                                Toast.makeText(mContext, "摄像头授权成功", Toast.LENGTH_SHORT).show();
                                startScan();
                                break;
                            default:
                                break;
                        }
                    }

                    @Override
                    public void permissionDenied(int code) {
                        switch (code) {
                            case READ_CODE:
                                isOpenRead = false;
                                Toast.makeText(mContext, "存储权限授权失败", Toast.LENGTH_SHORT).show();
                                break;
                            case CAMERA_CODE:
                                isOpenCamera = false;
                                Toast.makeText(mContext, "摄像头权限授权失败", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }
                    }

                    @Override
                    public void permissionRationale(int code) {
                        switch (code) {
                            case READ_CODE:
//                                Toast.makeText(mContext, "请开启读权限", Toast.LENGTH_SHORT).show();
                                break;
                            case CAMERA_CODE:
//                                Toast.makeText(mContext, "请开启摄像头权限", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }
                    }
                })
                .request();
    }

    private void startScan() {
        if (isOpenCamera && isOpenRead) {
            Logger.d("startScan");
            isOpenRead = false;
            isOpenCamera = false;

            QrConfig options = new QrConfig.Builder().create();
            QrManager.getInstance().init(options).startScan(new QrManager.OnScanResultCallback() {
                @Override
                public void onScanSuccess(String result) {
                    Toast.makeText(mContext, result, Toast.LENGTH_SHORT).show();
                }
            });

            Intent intent = new Intent(mContext, ScanActivity.class);
            intent.putExtra(QrConfig.EXTRA_THIS_CONFIG, options);
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[]
            grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Permissions4M.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }

            }
        }
    }
}

