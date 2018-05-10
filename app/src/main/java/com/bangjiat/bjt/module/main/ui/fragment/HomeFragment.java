package com.bangjiat.bjt.module.main.ui.fragment;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.BaseFragment;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.module.home.company.ui.AddOrSelectCompanyActivity;
import com.bangjiat.bjt.module.home.notice.beans.NoticeBean;
import com.bangjiat.bjt.module.home.notice.contract.NoticeContract;
import com.bangjiat.bjt.module.home.notice.presenter.NoticePresenter;
import com.bangjiat.bjt.module.home.notice.ui.AllNoticeActivity;
import com.bangjiat.bjt.module.home.notice.ui.NoticeItemActivity;
import com.bangjiat.bjt.module.home.scan.beans.QrCodeDataUser;
import com.bangjiat.bjt.module.home.scan.ui.OpenDoorCodeActivity;
import com.bangjiat.bjt.module.home.scan.ui.ScanActivity;
import com.bangjiat.bjt.module.home.visitor.ui.VisitorActivity;
import com.bangjiat.bjt.module.home.work.ui.WorkMainActivity;
import com.bangjiat.bjt.module.secretary.contact.beans.SearchContactResult;
import com.bangjiat.bjt.module.secretary.contact.view.ContactInfoActivity;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.joker.api.Permissions4M;
import com.joker.api.wrapper.Wrapper;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bertsir.zbar.QrConfig;
import cn.bertsir.zbar.QrManager;
import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.bgabanner.BGALocalImageSize;


public class HomeFragment extends BaseFragment implements NoticeContract.View {
    @BindView(R.id.banner_guide_content)
    BGABanner mContentBanner;
    @BindView(R.id.tv_content)
    TextView tv_content;
    @BindView(R.id.tv_title)
    TextView tv_title;
    private static final int READ_CODE = 2;
    private static final int CAMERA_CODE = 3;
    private boolean isOpenRead, isOpenCamera;
    private NoticeBean.SysNoticeListBean sysNoticeListBean;
    private NoticeContract.Presenter presenter;

    protected void initView() {
        presenter = new NoticePresenter(this);
        presenter.getAllNotice(DataUtil.getToken(mContext));
        BGALocalImageSize localImageSize = new BGALocalImageSize(720, 1280, 320, 640);
        mContentBanner.setData(localImageSize, ImageView.ScaleType.CENTER_CROP,
                R.drawable.banner1,
                R.drawable.banner2,
                R.drawable.banner3,
                R.drawable.banner4);
        mContentBanner.setDelegate(new BGABanner.Delegate<ImageView, Integer>() {
            @Override
            public void onBannerItemClick(BGABanner banner, ImageView itemView, Integer model, int position) {
//                Toast.makeText(banner.getContext(), "点击了" + position, Toast.LENGTH_SHORT).show();
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
        if (sysNoticeListBean != null) {
            Intent intent = new Intent(mContext, NoticeItemActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("data", sysNoticeListBean);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    @OnClick(R.id.tv_visitor)
    public void clickVisitor(View view) {
        startActivity(new Intent(mContext, Constants.isIntoCompany() ? VisitorActivity.class : AddOrSelectCompanyActivity.class));
    }

    @OnClick(R.id.iv_scan)
    public void clickScan(View view) {
        checkPermission();
    }

    @OnClick(R.id.tv_open_door)
    public void clickOpenDoor(View view) {
        startActivity(new Intent(mContext, Constants.isIntoCompany() ?
                OpenDoorCodeActivity.class : AddOrSelectCompanyActivity.class));
    }

    @OnClick(R.id.tv_work)
    public void clickWork(View view) {
        startActivity(new Intent(mContext, Constants.isIntoCompany() ? WorkMainActivity.class : AddOrSelectCompanyActivity.class));
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
                    try {
                        QrCodeDataUser user = new Gson().fromJson(result, QrCodeDataUser.class);
                        Intent intent = new Intent(mContext, ContactInfoActivity.class);
                        SearchContactResult bean = new SearchContactResult(user.getNickname(),
                                user.getUserId(), user.getUsername());
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("data", bean);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                        Toast.makeText(mContext, "二维码解析失败", Toast.LENGTH_SHORT).show();
                    }
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
    public void showDialog() {

    }

    @Override
    public void dismissDialog() {

    }

    @Override
    public void getAllNoticeResult(NoticeBean bean) {
        if (bean != null) {
            List<NoticeBean.SysNoticeListBean> sysNoticeList = bean.getSysNoticeList();
            if (sysNoticeList != null) {
                sysNoticeListBean = sysNoticeList.get(0);
                tv_title.setText(sysNoticeListBean.getName());
                tv_content.setText(sysNoticeListBean.getContent());
            }
        }
    }

    @Override
    public void showError(String err) {

    }
}

