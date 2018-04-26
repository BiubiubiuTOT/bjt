package com.bangjiat.bjt.module.park;

import android.content.Intent;
import android.view.View;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.BaseFragment;
import com.bangjiat.bjt.module.park.apply.ui.ApplyMainActivity;
import com.bangjiat.bjt.module.park.car.ui.MyCarActivity;
import com.bangjiat.bjt.module.park.pay.ui.PayMainActivity;

import butterknife.OnClick;

public class ParkFragment extends BaseFragment {
    protected void initView() {
    }

    @OnClick(R.id.ll_my_car)
    public void clickMyCar(View view) {
        startActivity(new Intent(mContext, MyCarActivity.class));
    }

    @OnClick(R.id.ll_apply)
    public void clickApply(View view) {
        startActivity(new Intent(mContext, ApplyMainActivity.class));
    }

    @OnClick(R.id.ll_pay)
    public void clickPay(View view) {
        startActivity(new Intent(mContext, PayMainActivity.class));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_park;
    }

}

