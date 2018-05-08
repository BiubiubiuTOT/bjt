package com.bangjiat.bjt.module.secretary.service.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.main.ui.activity.BaseColorToolBarActivity;
import com.bangjiat.bjt.module.secretary.door.beans.ApplyHistoryBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 审批记录
 */
public class ApprovalHistoryActivity extends BaseColorToolBarActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;

    private List<ApplyHistoryBean> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_approval_history;
    }

    @Override
    protected String getTitleStr() {
        return "审批记录";
    }

    private void initData() {
        list = new ArrayList<>();

        setAdapter();
    }

    private void setAdapter() {
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        recycler_view.setHasFixedSize(true);
//        ApplyHistoryAdapter mAdapter = new ApplyHistoryAdapter(list, mContext);
//        recycler_view.setAdapter(mAdapter);
//
//        mAdapter.setOnItemClickListener(new ApplyHistoryAdapter.OnRecyclerViewItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                startActivity(new Intent(mContext, DetailActivity.class));
//            }
//        });
    }
}
