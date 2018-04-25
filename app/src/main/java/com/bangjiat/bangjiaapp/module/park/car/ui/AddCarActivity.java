package com.bangjiat.bangjiaapp.module.park.car.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bangjiat.bangjiaapp.R;
import com.bangjiat.bangjiaapp.common.FullImageActivity;
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
import fv.galois.wcbmenu.WCBMenu;

public class AddCarActivity extends BaseWhiteToolBarActivity {
    private GalleryConfig galleryConfig;
    private List<String> mList;
    private List<String> path;

    @BindView(R.id.iv_car)
    ImageView iv_car;
    @BindView(R.id.ll_add_photo)
    LinearLayout ll_add_photo;
    private List<String> mList1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @OnClick(R.id.ll_add_photo)
    public void clickAddPhoto(View view) {
        showChoosePicDialog();
    }

    @OnClick(R.id.iv_car)
    public void clickCar(View view) {
        showDia();
    }

    private void showDia() {
        final WCBMenu wcbMenu = new WCBMenu(mContext);
        wcbMenu.setCancel("取消")
                .setStringList(mList1)
                .setItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        wcbMenu.dismiss();
                        if (position == 0) {
                            Intent intent = new Intent(mContext, FullImageActivity.class);
                            intent.putExtra("path", path.get(0));
                            startActivity(intent);
                        } else {
                            path.remove(0);
                            ll_add_photo.setVisibility(View.VISIBLE);
                            iv_car.setVisibility(View.GONE);
                        }
                    }
                })
                .show();
    }

    private void initData() {
        path = new ArrayList<>();
        mList = new ArrayList<>();
        mList.add("从手机相册选择");
        mList.add("相机拍摄");

        mList1 = new ArrayList<>();
        mList1.add("查看原图");
        mList1.add("删除");

        galleryConfig = new GalleryConfig.Builder()
                .imageLoader(new GlideImageLoader())
                .iHandlerCallBack(iHandlerCallBack)
                .provider("com.bangjiat.bangjiaapp.fileprovider")
                .pathList(path)
                .filePath("/Gallery/Pictures")
                .build();
    }

    private void showChoosePicDialog() {
        final WCBMenu wcbMenu = new WCBMenu(mContext);
        wcbMenu.setCancel("取消")
                .setStringList(mList)
                .setItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        wcbMenu.dismiss();
                        if (position == 0) {
                            galleryConfig.getBuilder().isOpenCamera(false).isShowCamera(false).build();
                            GalleryPick.getInstance().setGalleryConfig(galleryConfig).open(AddCarActivity.this);
                        } else {
                            // 进行正常操作
                            GalleryPick.getInstance().setGalleryConfig(galleryConfig).openCamera(AddCarActivity.this);
                        }
                    }
                })
                .show();
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
                Glide.with(mContext).load(s).into(iv_car);
            }
            path.addAll(photoList);
            ll_add_photo.setVisibility(View.GONE);
            iv_car.setVisibility(View.VISIBLE);
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

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_add_car;
    }

    @Override
    protected String getTitleStr() {
        return "添加车辆";
    }
}
