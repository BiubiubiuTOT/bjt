package com.bangjiat.bjt.module.park.car.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.module.main.ui.activity.BaseWhiteToolBarActivity;
import com.bangjiat.bjt.module.park.car.beans.CarBean;
import com.bangjiat.bjt.module.park.car.contract.CarListContract;
import com.bangjiat.bjt.module.park.car.presenter.CarListPresenter;
import com.bumptech.glide.Glide;
import com.dou361.dialogui.DialogUIUtils;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MyCarActivity extends BaseWhiteToolBarActivity implements CarListContract.View {
    private static final int ADD_CAR = 3;
    @BindView(R.id.tv_number)
    TextView tv_number;
    @BindView(R.id.tv_model)
    TextView tv_model;
    @BindView(R.id.tv_color)
    TextView tv_color;
    @BindView(R.id.iv_car)
    ImageView iv_car;
    @BindView(R.id.ll_add)
    LinearLayout ll_add;
    @BindView(R.id.ll_detail)
    LinearLayout ll_detail;
    private Dialog dialog;
    private CarListContract.Presenter presenter;
    private CarBean carBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new CarListPresenter(this);
        presenter.getCarList(DataUtil.getToken(mContext));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_my_car;
    }

    @Override
    protected String getTitleStr() {
        return "我的车辆";
    }

    @OnClick(R.id.ll_detail)
    public void clickDetail(View view) {
        Intent intent = new Intent(mContext, MyCarDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", carBean);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @OnClick(R.id.ll_add)
    public void clickAddCar(View view) {
        startActivityForResult(new Intent(mContext, AddCarActivity.class), ADD_CAR);
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
        Toast.makeText(mContext, err, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getCarListSuccess(List<CarBean> bean) {
        if (bean != null && bean.size() > 0) {
            carBean = bean.get(0);
            ll_detail.setVisibility(View.VISIBLE);
            ll_add.setVisibility(View.GONE);
            Logger.d(bean.toString());
            setText(carBean);
        } else {
            ll_detail.setVisibility(View.GONE);
            ll_add.setVisibility(View.VISIBLE);
        }
    }

    private void setText(CarBean bean) {
        Glide.with(mContext).load(bean.getResource()).centerCrop().into(iv_car);
        tv_number.setText("车牌：" + bean.getPlateNumber());
        tv_color.setText("颜色：" + bean.getColor());
        tv_model.setText("车型：" + bean.getModel());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == ADD_CAR) {
                presenter.getCarList(DataUtil.getToken(mContext));
            }
        }
    }

    @Override
    public void addCarSuccess() {

    }
}
