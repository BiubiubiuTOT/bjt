package com.bangjiat.bjt.module.park;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.BaseFragment;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.module.park.apply.ui.ApplyMainActivity;
import com.bangjiat.bjt.module.park.car.ui.MyCarActivity;
import com.bangjiat.bjt.module.park.pay.ui.PayMainActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class ParkFragment extends BaseFragment {
    @BindView(R.id.tv_apply_name)
    TextView tv_apply_name;
    @BindView(R.id.ll_apply)
    LinearLayout ll_apply;

    protected void initView() {
        if (Constants.isParkAdmin()) {
            ll_apply.setVisibility(View.VISIBLE);
        } else if (Constants.isCompanyAdmin()) {
            ll_apply.setVisibility(View.VISIBLE);
        }
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

