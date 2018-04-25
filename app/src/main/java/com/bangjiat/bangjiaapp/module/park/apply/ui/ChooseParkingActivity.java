package com.bangjiat.bangjiaapp.module.park.apply.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bangjiat.bangjiaapp.R;
import com.bangjiat.bangjiaapp.module.main.ui.activity.BaseWhiteToolBarActivity;
import com.bangjiat.bangjiaapp.module.park.apply.adapter.ParkingAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ChooseParkingActivity extends BaseWhiteToolBarActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        final List<String> list = new ArrayList<>();
        list.add("邦佳停车场");
        list.add("金融城停车场");
        list.add("会展城B6停车场");
        list.add("会展城B4停车场");

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setHasFixedSize(true);

        ParkingAdapter mAdapter = new ParkingAdapter(list, mContext);
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ParkingAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent();
                intent.putExtra("data", list.get(position));
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_choose_parking;
    }

    @Override
    protected String getTitleStr() {
        return "选择停车场";
    }
}
