package com.bangjiat.bangjiaapp.module.visitor.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bangjiat.bangjiaapp.R;
import com.bangjiat.bangjiaapp.module.main.ui.activity.BaseToolBarActivity;
import com.bangjiat.bangjiaapp.module.visitor.adapter.VisitorAdapter;
import com.bangjiat.bangjiaapp.module.visitor.beans.VisitorBean;
import com.githang.statusbar.StatusBarCompat;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class VisitorActivity extends BaseToolBarActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.white));
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_visitor;
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        TextView textView = findViewById(R.id.toolbar_title);
        textView.setText("访客");
        textView.setTextColor(getResources().getColor(R.color.black));
        toolbar.setBackgroundColor(getResources().getColor(R.color.white));
        toolbar.setNavigationIcon(R.mipmap.back_black);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initData() {
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        recycler_view.setHasFixedSize(true);
        VisitorAdapter mAdapter = new VisitorAdapter(getData());
        recycler_view.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new VisitorAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
    }

    private List<VisitorBean> getData() {
        List<VisitorBean> beanList = new ArrayList<>();
        beanList.add(new VisitorBean("拜访人姓名：王力",
                "访问事宜：面试", true, "已同意", "拜访时间：2018-03-24 16:54"));
        beanList.add(new VisitorBean("拜访人姓名：王一",
                "访问事宜：面试", true, "已拒绝", "拜访时间：2018-03-24 16:54"));
        beanList.add(new VisitorBean("拜访人姓名：王三",
                "访问事宜：面试", false, "", "拜访时间：2018-03-24 16:54"));
        return beanList;
    }
}
