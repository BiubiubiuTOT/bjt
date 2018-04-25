package com.bangjiat.bangjiaapp.module.park.pay.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.bangjiat.bangjiaapp.R;
import com.bangjiat.bangjiaapp.module.main.ui.activity.BaseToolBarActivity;
import com.bangjiat.bangjiaapp.module.park.apply.beans.ApplyHistoryBean;
import com.bangjiat.bangjiaapp.module.park.pay.adapter.PayAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PayMainActivity extends BaseToolBarActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }

    private void initData() {
        List<ApplyHistoryBean> beans = new ArrayList<>();
        beans.add(new ApplyHistoryBean());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        PayAdapter mAdapter = new PayAdapter(beans, mContext);
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new PayAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(mContext, PayActivity.class));
            }
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_pay_main;
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        toolbar.setNavigationIcon(R.mipmap.back_black);
        TextView tv_title = toolbar.findViewById(R.id.toolbar_title);
        tv_title.setText("停车缴费");
        TextView tv_history = toolbar.findViewById(R.id.toolbar_other);
        tv_history.setText("缴费记录");

        tv_title.setTextColor(getResources().getColor(R.color.black));
        tv_history.setVisibility(View.VISIBLE);
        toolbar.setBackgroundColor(getResources().getColor(R.color.white));
        tv_history.setTextColor(getResources().getColor(R.color.black));

        tv_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, PayHistoryActivity.class));
            }
        });
    }
}
