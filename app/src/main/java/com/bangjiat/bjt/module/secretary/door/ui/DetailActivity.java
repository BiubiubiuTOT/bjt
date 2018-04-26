package com.bangjiat.bjt.module.secretary.door.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.main.ui.activity.BaseColorToolBarActivity;
import com.bangjiat.bjt.module.secretary.door.adapter.HandleHistoryAdapter;
import com.bangjiat.bjt.module.secretary.door.beans.ApplyHistoryBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 申请详情
 */
public class DetailActivity extends BaseColorToolBarActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private List<ApplyHistoryBean.HandleHistory> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        list = new ArrayList<>();
        ApplyHistoryBean.HandleHistory handleHistory = new ApplyHistoryBean.
                HandleHistory("张无忌", "2018.04.02  14:57", "发起申请", 0);
        ApplyHistoryBean.HandleHistory handleHistory1 = new ApplyHistoryBean.
                HandleHistory("陈小华", "2018.04.02  14:57", "已同意", 0);

        list.add(handleHistory);
        list.add(handleHistory1);

        setAdapter();
    }

    private void setAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setHasFixedSize(true);
        HandleHistoryAdapter adapter = new HandleHistoryAdapter(list, mContext);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new HandleHistoryAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_apply_deatil;
    }

    @Override
    protected String getTitleStr() {
        return "申请详情";
    }
}
