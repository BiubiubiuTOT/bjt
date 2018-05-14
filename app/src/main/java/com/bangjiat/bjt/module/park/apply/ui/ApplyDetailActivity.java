package com.bangjiat.bjt.module.park.apply.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.main.ui.activity.BaseWhiteToolBarActivity;
import com.bangjiat.bjt.module.park.apply.adapter.CarDetailAdapter;
import com.bangjiat.bjt.module.park.apply.adapter.HistoryDetailAdapter;
import com.bangjiat.bjt.module.park.car.beans.CarBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ApplyDetailActivity extends BaseWhiteToolBarActivity {
    @BindView(R.id.recycler_view_car)
    RecyclerView recyclerViewCar;
    @BindView(R.id.recycler_view_history)
    RecyclerView recyclerViewHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        List<CarBean> carInfoBeans = new ArrayList<>();
        carInfoBeans.add(new CarBean());
        carInfoBeans.add(new CarBean());
        recyclerViewCar.setHasFixedSize(true);
        recyclerViewCar.setLayoutManager(new LinearLayoutManager(mContext));

        CarDetailAdapter mAdapter = new CarDetailAdapter(carInfoBeans, mContext);
        HistoryDetailAdapter adapter = new HistoryDetailAdapter(carInfoBeans, mContext);
        recyclerViewCar.setAdapter(mAdapter);

        recyclerViewHistory.setHasFixedSize(true);
        recyclerViewHistory.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerViewHistory.setAdapter(adapter);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_apply_ditail;
    }

    @Override
    protected String getTitleStr() {
        return "审批详情";
    }
}
