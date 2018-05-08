package com.bangjiat.bjt.module.secretary.door.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.main.ui.activity.BaseColorToolBarActivity;
import com.bangjiat.bjt.module.secretary.door.adapter.HandleHistoryAdapter;
import com.bangjiat.bjt.module.secretary.door.beans.HandleHistoryDetail;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 申请详情
 */
public class DetailActivity extends BaseColorToolBarActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private List<HandleHistoryDetail> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        list = new ArrayList<>();


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
