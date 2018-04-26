package com.bangjiat.bjt.module.park.pay.ui;

import android.os.Bundle;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.main.ui.activity.BaseWhiteToolBarActivity;

import butterknife.BindView;

public class PayDetailActivity extends BaseWhiteToolBarActivity {
    @BindView(R.id.tv_parking)
    TextView tv_parking;
    @BindView(R.id.tv_pay_mode)
    TextView tv_pay_mode;
    @BindView(R.id.tv_end_time)
    TextView tv_end_time;
    @BindView(R.id.tv_start_time)
    TextView tv_start_time;
    @BindView(R.id.tv_money_month)
    TextView tv_money_month;
    @BindView(R.id.tv_total_month)
    TextView tv_total_month;
    @BindView(R.id.tv_total_money)
    TextView tv_total_money;
    @BindView(R.id.tv_car_number)
    TextView tv_car_number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_pay_detail;
    }

    @Override
    protected String getTitleStr() {
        return "详细详细";
    }
}
