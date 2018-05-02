package com.bangjiat.bjt.module.home.work.permission.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.main.ui.activity.BaseWhiteToolBarActivity;
import com.bangjiat.bjt.module.secretary.door.beans.PeopleBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PermissionTransferActivity extends BaseWhiteToolBarActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private List<PeopleBean> beans;
    private WorkersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        beans = new ArrayList<>();
        beans.add(new PeopleBean("张三", "17685302679"));
        beans.add(new PeopleBean("李四", "18083608929"));
        beans.add(new PeopleBean("王五", "18785166716"));
        beans.add(new PeopleBean("王五", "18785166716"));
        beans.add(new PeopleBean("王五", "18785166716"));
        beans.add(new PeopleBean("王五", "18785166716"));
        beans.add(new PeopleBean("王五", "18785166716"));
        beans.add(new PeopleBean("王五", "18785166716"));
        beans.add(new PeopleBean("王五", "18785166716"));
        beans.add(new PeopleBean("王五", "18785166716"));
        beans.add(new PeopleBean("王五", "18785166716"));
        beans.add(new PeopleBean("王五", "18785166716"));
        beans.add(new PeopleBean("王五", "18785166716"));
        beans.add(new PeopleBean("王五", "18785166716"));
        beans.add(new PeopleBean("王五", "18785166716"));
        beans.add(new PeopleBean("王五", "18785166716"));
        beans.add(new PeopleBean("王五", "18785166716"));
        beans.add(new PeopleBean("王五", "18785166716"));
        beans.add(new PeopleBean("王五", "18785166716"));
        beans.add(new PeopleBean("王五", "18785166716"));

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setHasFixedSize(true);
        adapter = new WorkersAdapter(beans, mContext);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new WorkersAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_permission_transfer;
    }

    @Override
    protected String getTitleStr() {
        return "权限转交";
    }
}
