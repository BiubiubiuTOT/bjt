package com.bangjiat.bjt.module.park.car.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.main.ui.activity.BaseWhiteToolBarActivity;
import com.bangjiat.bjt.module.park.car.beans.CarBean;
import com.bumptech.glide.Glide;

import butterknife.BindView;

public class MyCarDetailActivity extends BaseWhiteToolBarActivity {
    CarBean carBean;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_id_number)
    TextView tv_id_number;
    @BindView(R.id.tv_driveNumber)
    TextView tv_driveNumber;
    @BindView(R.id.tv_licenceNumber)
    TextView tv_licenceNumber;
    @BindView(R.id.tv_plateNumber)
    TextView tv_plateNumber;
    @BindView(R.id.tv_model)
    TextView tv_model;
    @BindView(R.id.tv_brand)
    TextView tv_brand;
    @BindView(R.id.tv_color)
    TextView tv_color;
    @BindView(R.id.iv_car)
    ImageView iv_car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        carBean = (CarBean) getIntent().getSerializableExtra("data");
        if (carBean != null) {
            tv_brand.setText(carBean.getBrand());
            tv_color.setText(carBean.getColor());
            tv_driveNumber.setText(carBean.getDriveNumber());
            tv_model.setText(carBean.getModel());
            tv_name.setText(carBean.getName());
            tv_plateNumber.setText(carBean.getPlateNumber());
            tv_licenceNumber.setText(carBean.getLicenceNumber());
            tv_id_number.setText(carBean.getIdNumber());

            Glide.with(mContext).load(carBean.getResource()).centerCrop().into(iv_car);
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_my_car_detail;
    }

    @Override
    protected String getTitleStr() {
        return "车辆信息";
    }
}
