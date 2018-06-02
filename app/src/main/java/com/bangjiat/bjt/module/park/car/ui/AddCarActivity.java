package com.bangjiat.bjt.module.park.car.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.adorkable.iosdialog.AlertDialog;
import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.ClearEditText;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.common.FullImageActivity;
import com.bangjiat.bjt.common.GlideImageLoader;
import com.bangjiat.bjt.common.WCBMenu;
import com.bangjiat.bjt.common.WcbBean;
import com.bangjiat.bjt.module.main.contract.UploadImageContract;
import com.bangjiat.bjt.module.main.presenter.UploadImagePresenter;
import com.bangjiat.bjt.module.main.ui.activity.BaseWhiteToolBarActivity;
import com.bangjiat.bjt.module.me.personaldata.beans.UserInfo;
import com.bangjiat.bjt.module.park.car.beans.CarBean;
import com.bangjiat.bjt.module.park.car.contract.CarListContract;
import com.bangjiat.bjt.module.park.car.presenter.CarListPresenter;
import com.bumptech.glide.Glide;
import com.dou361.dialogui.DialogUIUtils;
import com.orhanobut.logger.Logger;
import com.yancy.gallerypick.config.GalleryConfig;
import com.yancy.gallerypick.config.GalleryPick;
import com.yancy.gallerypick.inter.IHandlerCallBack;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AddCarActivity extends BaseWhiteToolBarActivity implements UploadImageContract.View, CarListContract.View {
    private GalleryConfig galleryConfig;
    private List<WcbBean> mList;
    private List<String> path;

    @BindView(R.id.iv_car)
    ImageView iv_car;
    @BindView(R.id.ll_add_photo)
    LinearLayout ll_add_photo;
    @BindView(R.id.et_name)
    ClearEditText et_name;
    @BindView(R.id.et_id_number)
    ClearEditText et_id_number;
    @BindView(R.id.et_driveNumber)
    ClearEditText et_driveNumber;
    @BindView(R.id.et_plateNumber)
    ClearEditText et_plateNumber;
    @BindView(R.id.et_brand)
    ClearEditText et_brand;
    @BindView(R.id.et_color)
    ClearEditText et_color;
    @BindView(R.id.et_model)
    ClearEditText et_model;
    @BindView(R.id.et_licenceNumber)
    ClearEditText er_licenceNumber;

    private List<WcbBean> mList1;
    private UploadImageContract.Presenter uploadImagePresenter;
    private CarListContract.Presenter addCarPresenter;
    private Dialog dialog;
    private String imageUrl;

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
        UserInfo first = UserInfo.first(UserInfo.class);
        String realname = first.getRealname();
        if (!realname.isEmpty()) {
            et_name.setText(realname);
        }
        et_id_number.setText(first.getIdNumber());

        uploadImagePresenter = new UploadImagePresenter(this);
        addCarPresenter = new CarListPresenter(this);
        path = new ArrayList<>();
        mList = new ArrayList<>();
        mList.add(new WcbBean("从手机相册选择"));
        mList.add(new WcbBean("相机拍摄"));

        mList1 = new ArrayList<>();
        mList1.add(new WcbBean("查看原图"));
        mList1.add(new WcbBean("删除"));

        galleryConfig = new GalleryConfig.Builder()
                .imageLoader(new GlideImageLoader())
                .iHandlerCallBack(iHandlerCallBack)
                .provider("com.bangjiat.bjt.fileprovider")
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

    @Override
    public void success(String url) {
        dismissDialog();
        imageUrl = url;

        addCar();
    }

    private void addCar() {
        CarBean bean = new CarBean();
        bean.setBrand(et_brand.getText().toString());
        bean.setColor(et_color.getText().toString());
        bean.setModel(et_model.getText().toString());
        bean.setDriveNumber(et_driveNumber.getText().toString());
        bean.setLicenceNumber(er_licenceNumber.getText().toString());
        bean.setPlateNumber(et_plateNumber.getText().toString());
        bean.setName(et_name.getText().toString());
        bean.setIdNumber(et_id_number.getText().toString());
        bean.setResource(imageUrl);
        addCarPresenter.addCar(DataUtil.getToken(mContext), bean);
    }

    @Override
    public void fail(String err) {
        dismissDialog();
        error("照片上传失败");
    }

    @OnClick(R.id.btn_submit)
    public void clickSubmit(View view) {
        if (path.size() > 0) {
            showDialog();
            uploadImagePresenter.uploadImage(path.get(0));
        } else {
            error("请选择车辆正面照片");
        }
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
    public void error(String err) {
        Constants.showErrorDialog(mContext, err);
    }

    @Override
    public void getCarListSuccess(List<CarBean> bean) {

    }

    @Override
    public void addCarSuccess() {
        showSuccessExitDialog("添加成功");
    }

    @Override
    public void deleteCarSuccess(String str) {

    }

    public void showSuccessExitDialog(String msg) {
        new AlertDialog(this).builder().setMsg(msg).
                setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setResult(RESULT_OK);
                        finish();
                    }
                }).show();
    }
}
