package com.bangjiat.bjt.module.home.work.kaoqin.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;


public class CountMonthFragment extends BaseFragment {
    @BindView(R.id.ll_1)
    LinearLayout ll_1;
    @BindView(R.id.ll_2)
    LinearLayout ll_2;
    @BindView(R.id.ll_3)
    LinearLayout ll_3;
    @BindView(R.id.ll_4)
    LinearLayout ll_4;
    @BindView(R.id.iv1)
    ImageView iv1;
    @BindView(R.id.iv2)
    ImageView iv2;
    @BindView(R.id.iv3)
    ImageView iv3;
    @BindView(R.id.iv4)
    ImageView iv4;


    @Override
    protected void initView() {
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_count_month;
    }

    @OnClick(R.id.rl_1)
    public void clickRl1(View view) {
        int visibility = ll_1.getVisibility();
        ll_1.setVisibility(visibility == View.GONE ? View.VISIBLE : View.GONE);
        iv1.setImageResource(visibility == View.GONE ? R.mipmap.up : R.mipmap.down);

        ll_2.setVisibility(View.GONE);
        ll_3.setVisibility(View.GONE);
        ll_4.setVisibility(View.GONE);
    }

    @OnClick(R.id.rl_2)
    public void clickRl2(View view) {
        int visibility = ll_2.getVisibility();
        ll_2.setVisibility(visibility == View.GONE ? View.VISIBLE : View.GONE);
        iv2.setImageResource(visibility == View.GONE ? R.mipmap.up : R.mipmap.down);

        ll_1.setVisibility(View.GONE);
        ll_3.setVisibility(View.GONE);
        ll_4.setVisibility(View.GONE);
    }

    @OnClick(R.id.rl_3)
    public void clickRl3(View view) {
        int visibility = ll_3.getVisibility();
        ll_3.setVisibility(visibility == View.GONE ? View.VISIBLE : View.GONE);
        iv3.setImageResource(visibility == View.GONE ? R.mipmap.up : R.mipmap.down);

        ll_2.setVisibility(View.GONE);
        ll_1.setVisibility(View.GONE);
        ll_4.setVisibility(View.GONE);
    }

    @OnClick(R.id.rl_4)
    public void clickRl4(View view) {
        int visibility = ll_4.getVisibility();
        ll_4.setVisibility(visibility == View.GONE ? View.VISIBLE : View.GONE);
        iv4.setImageResource(visibility == View.GONE ? R.mipmap.up : R.mipmap.down);

        ll_2.setVisibility(View.GONE);
        ll_3.setVisibility(View.GONE);
        ll_1.setVisibility(View.GONE);
    }

}

