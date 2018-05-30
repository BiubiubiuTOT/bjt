package com.bangjiat.bjt.module.home.visitor.ui;

import android.app.Dialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.BaseFragment;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.common.RefreshViewHolder;
import com.bangjiat.bjt.module.home.visitor.adapter.VisitorAdapter;
import com.bangjiat.bjt.module.home.visitor.beans.VisitorBean;
import com.bangjiat.bjt.module.home.visitor.contract.VisitorContract;
import com.bangjiat.bjt.module.home.visitor.presenter.VisitorPresenter;
import com.dou361.dialogui.DialogUIUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;


public class InviteFragment extends BaseFragment implements VisitorContract.View, BGARefreshLayout.BGARefreshLayoutDelegate {
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    @BindView(R.id.ll_none)
    LinearLayout ll_none;
    @BindView(R.id.rl_refresh)
    BGARefreshLayout mRefreshLayout;
    private VisitorContract.Presenter presenter;
    private Dialog dialog;
    private List<VisitorBean.RecordsBean> list;
    private VisitorAdapter mAdapter;


    @Override
    protected void initView() {
        presenter = new VisitorPresenter(this);
        presenter.getVisitorHistory(DataUtil.getToken(mContext), 1, 10, 2);
        setAdapter();
        mRefreshLayout.setDelegate(this);
        mRefreshLayout.setRefreshViewHolder(new RefreshViewHolder(mContext, false));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_invite;
    }

    private void setAdapter() {
        list = new ArrayList<>();
        recycler_view.setLayoutManager(new LinearLayoutManager(mContext));
        recycler_view.setHasFixedSize(true);
        mAdapter = new VisitorAdapter(list);
        recycler_view.setAdapter(mAdapter);
    }


    @Override
    public void showDialog() {
        dialog = DialogUIUtils.showLoadingVertical(mContext, "加载中").show();
    }

    @Override
    public void dismissDialog() {
        if (dialog != null)
            dialog.dismiss();
    }

    @Override
    public void error(String err) {
        mRefreshLayout.endRefreshing();
        Logger.e(err);
        Constants.showErrorDialog(mContext, err);
    }

    @Override
    public void success(VisitorBean bean) {
        mRefreshLayout.endRefreshing();
        if (bean != null) {
            List<VisitorBean.RecordsBean> records = bean.getRecords();
            if (records != null && records.size() > 0) {
                list = records;
                mAdapter.setLists(list);
                ll_none.setVisibility(View.GONE);
                return;
            }
        }
        ll_none.setVisibility(View.VISIBLE);
    }

    @Override
    public void dealSuccess(String str) {
    }

    @Override
    public void addInviteSuccess(String str) {

    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout bgaRefreshLayout) {
        presenter.getVisitorHistory(DataUtil.getToken(mContext), 1, 10, 2);
        bgaRefreshLayout.beginRefreshing();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout bgaRefreshLayout) {
        return false;
    }

}

