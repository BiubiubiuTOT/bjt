package com.bangjiat.bangjiaapp.module.notice.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bangjiat.bangjiaapp.R;
import com.bangjiat.bangjiaapp.module.main.ui.activity.BaseToolBarActivity;
import com.bangjiat.bangjiaapp.module.notice.adapter.NoticeAdapter;
import com.bangjiat.bangjiaapp.module.notice.beans.NoticeBean;
import com.bangjiat.bangjiaapp.module.notice.contract.NoticeContract;
import com.bangjiat.bangjiaapp.module.notice.presenter.NoticePresenter;
import com.githang.statusbar.StatusBarCompat;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class AllNoticeActivity extends BaseToolBarActivity implements NoticeContract.View {
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;

    private NoticeContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.white));
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        presenter = new NoticePresenter(this);
//        presenter.getAllNotice(DataUtil.getToken(mContext));

        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        recycler_view.setHasFixedSize(true);
        NoticeAdapter mAdapter = new NoticeAdapter(getData());
        recycler_view.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new NoticeAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                NoticeBean bean = getData().get(position);
                Intent intent = new Intent(mContext, NoticeItemActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", bean);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private List<NoticeBean> getData() {
        List<NoticeBean> beanList = new ArrayList<>();
        beanList.add(new NoticeBean(false, "国庆节放假",
                "国庆七天假期，国际互联网金融特区大厦国庆七天假期，国际互联网金融特区大厦国庆七天假期，" +
                        "国际互联网金融特区大厦国庆七天假期，国际互联网金融特区大厦国...", "2018-03-24 16:54"));
        beanList.add(new NoticeBean(true, "周末加班", "由于项目需要尽快上线，本周六加班", "2018-03-24 16:54"));
        return beanList;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_all_notice;
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        TextView textView = findViewById(R.id.toolbar_title);
        textView.setText("全部公告");
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

    @Override
    public void showDialog() {

    }

    @Override
    public void dismissDialog() {

    }

    @Override
    public void getAllNoticeResult() {

    }

    @Override
    public void showError(String err) {

    }
}
