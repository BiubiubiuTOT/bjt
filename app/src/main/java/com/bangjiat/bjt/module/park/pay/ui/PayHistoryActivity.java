package com.bangjiat.bjt.module.park.pay.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.main.ui.activity.BaseWhiteToolBarActivity;
import com.bangjiat.bjt.module.park.apply.beans.ApplyHistoryBean;
import com.bangjiat.bjt.module.park.pay.adapter.PayHistoryAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PayHistoryActivity extends BaseWhiteToolBarActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }

    private void initData() {
        List<ApplyHistoryBean> list = new ArrayList<>();
        list.add(new ApplyHistoryBean());
        list.add(new ApplyHistoryBean());
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setHasFixedSize(true);

        PayHistoryAdapter mAdapter = new PayHistoryAdapter(list, mContext);
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new PayHistoryAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(mContext, PayDetailActivity.class));
            }
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_pay_history;
    }

    @Override
    protected String getTitleStr() {
        return "缴费记录";
    }
}
