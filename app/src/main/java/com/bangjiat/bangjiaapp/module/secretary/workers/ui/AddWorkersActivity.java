package com.bangjiat.bangjiaapp.module.secretary.workers.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.bangjiat.bangjiaapp.R;
import com.bangjiat.bangjiaapp.module.main.ui.activity.BaseToolBarActivity;
import com.bangjiat.bangjiaapp.module.secretary.door.beans.PeopleBean;
import com.bangjiat.bangjiaapp.module.secretary.workers.adapter.AddWorkersAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 新增员工
 */
public class AddWorkersActivity extends BaseToolBarActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private AddWorkersAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        ArrayList<PeopleBean> lists = new ArrayList<>();
        lists.add(new PeopleBean());
        mAdapter = new AddWorkersAdapter(lists);

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_add_workers;
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        TextView tv_title = toolbar.findViewById(R.id.toolbar_title);
        TextView tv_done = toolbar.findViewById(R.id.toolbar_other);

        tv_title.setText("新增员工");
        tv_done.setText("完成");

        tv_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logger.d(mAdapter.getLists().toString());
            }
        });

        toolbar.setNavigationIcon(R.mipmap.back_white);
    }

    @OnClick(R.id.iv_add)
    public void clickAdd(View view) {
        mAdapter.add();
    }
}
