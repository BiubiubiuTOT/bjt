package com.bangjiat.bjt.module.park.pay.ui;

import android.os.Bundle;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.TimeUtils;
import com.bangjiat.bjt.module.main.ui.activity.BaseWhiteToolBarActivity;
import com.bangjiat.bjt.module.park.pay.beans.ParkPayHistory;

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
    @BindView(R.id.tv_txt_1)
    TextView tv_txt_1;
    @BindView(R.id.tv_txt_2)
    TextView tv_txt_2;

    private ParkPayHistory.RecordsBean bean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView() {
        bean = (ParkPayHistory.RecordsBean) getIntent().getSerializableExtra("data");
        if (bean != null) {
            tv_parking.setText(bean.getSpaceName());
            int payWay = bean.getPayWay();
            String des = "";
            if (payWay == 1) {
                des = "支付宝";
            } else if (payWay == 2)
                des = "微信";
            tv_pay_mode.setText(des);
            tv_start_time.setText(TimeUtils.changeToTime(bean.getBeginTime()));
            tv_end_time.setText(TimeUtils.changeToTime(bean.getEndTime()));
            tv_car_number.setText(bean.getPlateNumber());

            int type = bean.getType();
            if (type == 1) {
                tv_txt_1.setText("每月费用");
                tv_txt_2.setText("缴纳月数");
            } else if (type == 2) {
                tv_txt_1.setText("每年费用");
                tv_txt_2.setText("缴纳年数");
            }
            tv_money_month.setText(bean.getFee());
            tv_total_month.setText(bean.getNumber() + "");
            tv_total_money.setText(bean.getTotalFee());
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_pay_detail;
    }

    @Override
    protected String getTitleStr() {
        return "详细信息";
    }
}
