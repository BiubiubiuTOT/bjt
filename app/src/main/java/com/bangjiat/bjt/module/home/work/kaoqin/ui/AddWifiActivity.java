package com.bangjiat.bjt.module.home.work.kaoqin.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.WifiUtil;
import com.bangjiat.bjt.module.home.work.kaoqin.adapter.WifiAdapter;
import com.bangjiat.bjt.module.home.work.kaoqin.beans.WifiBean;
import com.bangjiat.bjt.module.main.ui.activity.BaseToolBarActivity;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

public class AddWifiActivity extends BaseToolBarActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private WifiUtil wifiUtil;
    private WifiAdapter adapter;
    private List<WifiBean> wifiList;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        wifiUtil = new WifiUtil(mContext);
        wifiList = wifiUtil.getWifiList();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        setAdapter();
        startTimer();
    }

    private void setAdapter() {
        adapter = new WifiAdapter(wifiList, mContext);
        recyclerView.setAdapter(adapter);
    }

    private void startTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                wifiList = wifiUtil.getWifiList();
                if (wifiList.size() > 0) {
                    mHandler.sendEmptyMessage(1);
                }
            }
        }, 2000, 5000);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_add_wifi;
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setBackgroundColor(getResources().getColor(R.color.white));
        toolbar.setTitle("");

        TextView tv_cancel = toolbar.findViewById(R.id.toolbar_cancel);
        TextView tv_done = toolbar.findViewById(R.id.toolbar_other);
        TextView tv_title = toolbar.findViewById(R.id.toolbar_title);

        tv_cancel.setText("取消");
        tv_done.setText("完成");
        tv_title.setText("添加办公WIFI");
        tv_done.setVisibility(View.VISIBLE);
        tv_cancel.setTextColor(getResources().getColor(R.color.black));
        tv_done.setTextColor(getResources().getColor(R.color.black));
        tv_title.setTextColor(getResources().getColor(R.color.black));

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tv_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<Integer, Boolean> map = adapter.getMap();
                Set<Map.Entry<Integer, Boolean>> entries = map.entrySet();
                for (Map.Entry<Integer, Boolean> entry : entries) {
                    if (entry.getValue()) {
                        Intent intent = new Intent();
                        intent.putExtra("data", wifiList.get(entry.getKey()).getName());
                        setResult(RESULT_OK, intent);
                        finish();
                        break;
                    }
                }
            }
        });
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1)
                adapter.setLists(wifiList);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) timer.cancel();
    }
}
