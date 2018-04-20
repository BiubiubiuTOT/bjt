package com.bangjiat.bangjiaapp.module.home.notice.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bangjiat.bangjiaapp.R;
import com.bangjiat.bangjiaapp.module.main.ui.activity.BaseWhiteToolBarActivity;
import com.bangjiat.bangjiaapp.module.home.notice.adapter.NoticeAdapter;
import com.bangjiat.bangjiaapp.module.home.notice.beans.NoticeBean;
import com.bangjiat.bangjiaapp.module.home.notice.contract.NoticeContract;
import com.bangjiat.bangjiaapp.module.home.notice.presenter.NoticePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class AllNoticeActivity extends BaseWhiteToolBarActivity implements NoticeContract.View {
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;

    private NoticeContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

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
    protected String getTitleStr() {
        return "全部公告";
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
