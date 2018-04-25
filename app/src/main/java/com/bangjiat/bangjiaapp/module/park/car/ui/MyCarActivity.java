package com.bangjiat.bangjiaapp.module.park.car.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bangjiat.bangjiaapp.R;
import com.bangjiat.bangjiaapp.module.main.ui.activity.BaseWhiteToolBarActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MyCarActivity extends BaseWhiteToolBarActivity {
    @BindView(R.id.tv_number)
    TextView tv_number;
    @BindView(R.id.tv_type)
    TextView tv_type;
    @BindView(R.id.tv_color)
    TextView tv_color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        startActivity(new Intent(mContext, MyCarDetailActivity.class));
    }

    @OnClick(R.id.ll_add)
    public void clickAddCar(View view) {
        startActivity(new Intent(mContext, AddCarActivity.class));
    }
}
