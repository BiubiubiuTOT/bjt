package com.bangjiat.bjt.module.home.notice.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.module.home.notice.adapter.NoticeAdapter;
import com.bangjiat.bjt.module.home.notice.beans.NoticeBean;
import com.bangjiat.bjt.module.home.notice.contract.NoticeContract;
import com.bangjiat.bjt.module.home.notice.presenter.NoticePresenter;
import com.bangjiat.bjt.module.main.ui.activity.BaseWhiteToolBarActivity;

import java.util.List;

import butterknife.BindView;

public class AllNoticeActivity extends BaseWhiteToolBarActivity implements NoticeContract.View {
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    @BindView(R.id.ll_none)
    LinearLayout ll_none;

    private NoticeContract.Presenter presenter;
    private List<NoticeBean.SysNoticeListBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        presenter = new NoticePresenter(this);
        presenter.getAllNotice(DataUtil.getToken(mContext));

        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        recycler_view.setHasFixedSize(true);

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
    public void getAllNoticeResult(NoticeBean noticeBean) {
        if (noticeBean != null) {
            List<NoticeBean.SysNoticeListBean> sysNoticeList = noticeBean.getSysNoticeList();
            if (sysNoticeList != null && sysNoticeList.size() > 0) {
                list = sysNoticeList;
                setAdapter();
                ll_none.setVisibility(View.GONE);
                return;
            }
        }
        ll_none.setVisibility(View.VISIBLE);

    }

    private void setAdapter() {
        NoticeAdapter mAdapter = new NoticeAdapter(list);
        recycler_view.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new NoticeAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                NoticeBean.SysNoticeListBean bean = list.get(position);
                Intent intent = new Intent(mContext, NoticeItemActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", bean);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void showError(String err) {
        Toast.makeText(mContext, err, Toast.LENGTH_SHORT).show();
    }
}
