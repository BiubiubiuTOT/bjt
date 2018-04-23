package com.bangjiat.bangjiaapp.module.secretary.communication.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bangjiat.bangjiaapp.R;
import com.bangjiat.bangjiaapp.module.main.ui.activity.BaseToolBarActivity;
import com.bangjiat.bangjiaapp.module.secretary.communication.adpter.OutBoxAdapter;
import com.bangjiat.bangjiaapp.module.secretary.communication.beans.OutBoxBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 发件箱
 */
public class OutBoxActivity extends BaseToolBarActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }

    private void initData() {
        List<OutBoxBean> boxBeans = new ArrayList<>();
        boxBeans.add(new OutBoxBean());
        boxBeans.add(new OutBoxBean());
        boxBeans.add(new OutBoxBean());

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setHasFixedSize(true);
        OutBoxAdapter mAdapter = new OutBoxAdapter(boxBeans);

        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_out_box;
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setNavigationIcon(R.mipmap.back_black);
        toolbar.setBackgroundColor(getResources().getColor(R.color.white));
        toolbar.setTitle("");
        ImageView image = toolbar.findViewById(R.id.toolbar_image);
        TextView tv_title = toolbar.findViewById(R.id.toolbar_title);
        tv_title.setText("已发送");
        image.setImageResource(R.mipmap.email_color);
        tv_title.setTextColor(getResources().getColor(R.color.black));
    }
}
