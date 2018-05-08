package com.bangjiat.bjt.module.home.work.kaoqin.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.home.work.kaoqin.adapter.WorkDayAdapter;
import com.bangjiat.bjt.module.main.ui.activity.BaseToolBarActivity;
import com.githang.statusbar.StatusBarCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;

public class WorkDayActivity extends BaseToolBarActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private WorkDayAdapter mAdapter;
    private List<String> list;
    private String index = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.white));

        initData();
    }

    private void initData() {
        list = new ArrayList<>();
        list.add("周一");
        list.add("周二");
        list.add("周三");
        list.add("周四");
        list.add("周五");
        list.add("周六");
        list.add("周日");

        mAdapter = new WorkDayAdapter(list, mContext);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new WorkDayAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mAdapter.setCheck(position);
            }
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_work_day;
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        TextView tv_title = toolbar.findViewById(R.id.toolbar_title);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        tv_title.setText("工作日");
        TextView tv_done = toolbar.findViewById(R.id.toolbar_other);
        tv_done.setTextColor(getResources().getColor(R.color.black));
        tv_done.setText("完成");
        toolbar.setNavigationIcon(R.mipmap.back_black);
        toolbar.setBackgroundColor(getResources().getColor(R.color.white));

        tv_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = "";
                int i = 0;
                Set<Map.Entry<Integer, Boolean>> entries = mAdapter.getMap().entrySet();
                for (Map.Entry<Integer, Boolean> entry : entries) {
                    if (entry.getValue()) {
                        i++;
                        str += list.get(entry.getKey()) + "、";
                        index += (entry.getKey() + 1) + ",";
                    }
                }

                if (!str.isEmpty()) {
                    str = str.substring(0, str.length() - 1);
                    if (i == 7)
                        str = "每天";
                    index = index.substring(0, index.length() - 1);
                    Intent intent = new Intent();
                    intent.putExtra("data", str);
                    intent.putExtra("index", index);
                    setResult(RESULT_OK, intent);
                }
                finish();
            }
        });
    }
}
