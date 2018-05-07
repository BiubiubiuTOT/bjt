package com.bangjiat.bjt.module.home.visitor.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.BaseFragment;
import com.bangjiat.bjt.module.home.visitor.adapter.VisitorAdapter;
import com.bangjiat.bjt.module.home.visitor.beans.VisitorBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class VisitorFragment extends BaseFragment {
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_visitor;
    }

    @Override
    protected void initView() {
        recycler_view.setLayoutManager(new LinearLayoutManager(mContext));
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

