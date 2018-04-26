package com.bangjiat.bjt.module.park.apply.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.main.ui.activity.BaseToolBarActivity;
import com.bangjiat.bjt.module.park.apply.adapter.ChooseCarAdapter;
import com.bangjiat.bjt.module.park.apply.beans.CarInfoBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;

public class ChooseCarActivity extends BaseToolBarActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private List<CarInfoBean> list;
    private ChooseCarAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }

    private void initData() {
        list = new ArrayList<>();
        list.add(new CarInfoBean("张三", "贵A53216", "奇瑞QQ", "红色"));
        list.add(new CarInfoBean("李四", "贵C5564V", "上海大众", "白色"));
        list.add(new CarInfoBean("王五", "贵J23w23", "五菱宏光", "白色"));

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setHasFixedSize(true);
        mAdapter = new ChooseCarAdapter(list, mContext);
        recyclerView.setAdapter(mAdapter);

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_choose_car;
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        TextView tv_done = toolbar.findViewById(R.id.toolbar_other);
        tv_done.setText("完成");
        tv_done.setVisibility(View.VISIBLE);
        TextView tv_title = toolbar.findViewById(R.id.toolbar_title);
        tv_title.setText("选择车辆");
        toolbar.setNavigationIcon(R.mipmap.back_black);

        toolbar.setBackgroundColor(getResources().getColor(R.color.white));
        tv_title.setTextColor(getResources().getColor(R.color.black));
        tv_done.setTextColor(getResources().getColor(R.color.black));

        tv_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", (Serializable) getData());
                intent.putExtras(bundle);

                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private List<CarInfoBean> getData() {
        List<CarInfoBean> beans = new ArrayList<>();
        HashMap<Integer, Boolean> map = mAdapter.getMap();
        Set<Map.Entry<Integer, Boolean>> entries = map.entrySet();
        for (Map.Entry<Integer, Boolean> entry : entries) {
            if (entry.getValue()) {
                beans.add(list.get(entry.getKey()));
            }
        }
        return beans;
    }

}
