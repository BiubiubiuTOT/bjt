package com.bangjiat.bjt.module.secretary.door.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.main.ui.activity.BaseToolBarActivity;
import com.bangjiat.bjt.module.secretary.door.adapter.SelectPeopleAdapter;
import com.bangjiat.bjt.module.secretary.door.beans.PeopleBean;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

public class AddPeopleActivity extends BaseToolBarActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.tv_select_all)
    TextView tv_select_all;
    private TextView tv_submit;
    private List<PeopleBean> beans;
    private SelectPeopleAdapter adapter;

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
        adapter = new SelectPeopleAdapter(beans, mContext);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new SelectPeopleAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
        adapter.setOnCheckChangedListener(new SelectPeopleAdapter.OnCheckListener() {
            @Override
            public void onCheckChanged() {
                int total = adapter.getItemCount();
                int select = getSelectCount();
                if (select == 0) {
                    tv_submit.setTextColor(getResources().getColor(R.color.add_people_submit));
                    tv_select_all.setText("全选");
                } else {
                    if (select == total) {
                        tv_select_all.setText("取消全选");
                    } else {
                        tv_select_all.setText("全选");
                    }
                    tv_submit.setTextColor(getResources().getColor(R.color.white));
                }
            }
        });
    }

    private int getSelectCount() {
        int select = 0;
        HashMap<Integer, Boolean> map = adapter.getMap();
        Set<Map.Entry<Integer, Boolean>> entries = map.entrySet();
        for (Map.Entry<Integer, Boolean> entry : entries) {
            if (entry.getValue())
                select++;
        }
        return select;
    }

    private List<PeopleBean> getSelectBeans() {
        List<PeopleBean> list = new ArrayList<>();
        HashMap<Integer, Boolean> map = adapter.getMap();
        Set<Map.Entry<Integer, Boolean>> entries = map.entrySet();
        for (Map.Entry<Integer, Boolean> entry : entries) {
            if (entry.getValue())
                list.add(beans.get(entry.getKey()));
        }
        return list;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_add_people;
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        TextView textView = findViewById(R.id.toolbar_title);
        textView.setText("新增人员");
        tv_submit = findViewById(R.id.toolbar_other);
        tv_submit.setText("提交");
        tv_submit.setTextColor(getResources().getColor(R.color.add_people_submit));
        toolbar.setNavigationIcon(R.mipmap.back_white);

        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logger.d(adapter.getMap().toString());
            }
        });
    }

    @OnClick(R.id.tv_select_all)
    public void clickSelect(View view) {
        adapter.selectAll();
    }
}
