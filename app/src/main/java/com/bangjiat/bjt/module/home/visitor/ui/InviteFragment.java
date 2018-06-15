package com.bangjiat.bjt.module.home.visitor.ui;

import android.app.Dialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.BaseFragment;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.common.RefreshViewHolder;
import com.bangjiat.bjt.common.ReplaceViewHelper;
import com.bangjiat.bjt.module.home.visitor.adapter.VisitorAdapter;
import com.bangjiat.bjt.module.home.visitor.beans.DeleteHistory;
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
    @BindView(R.id.rl_refresh)
    BGARefreshLayout mRefreshLayout;
    private VisitorContract.Presenter presenter;
    private Dialog dialog;
    private List<VisitorBean.RecordsBean> list;
    private VisitorAdapter mAdapter;
    private ReplaceViewHelper mReplaceViewHelper;
    private int pages;
    private int current = 1;


    @Override
    protected void initView() {
        presenter = new VisitorPresenter(this);
        presenter.getVisitorHistory(DataUtil.getToken(mContext), 1, 10, 2);
        setAdapter();
        mRefreshLayout.setDelegate(this);
        mRefreshLayout.setRefreshViewHolder(new RefreshViewHolder(mContext, true));
        mReplaceViewHelper = new ReplaceViewHelper(mContext);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_invite;
    }

    private void setAdapter() {
        list = new ArrayList<>();
        recycler_view.setLayoutManager(new LinearLayoutManager(mContext));
        recycler_view.setHasFixedSize(true);
        mAdapter = new VisitorAdapter(list, 1);
        recycler_view.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new VisitorAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onAgreeClick(View view, int position) {

            }

            @Override
            public void onRefuseClick(View view, int position) {

            }

            @Override
            public void onDelete(View view, int pos) {
                String[] strings = new String[1];
                VisitorBean.RecordsBean recordsBean = list.get(pos);
                strings[0] = String.valueOf(recordsBean.getVisitorId());
                DeleteHistory history = new DeleteHistory(2, 1, strings);
                presenter.deleteHistory(DataUtil.getToken(mContext), history);

                list.remove(pos);
                mAdapter.notifyItemRemoved(pos);
                mAdapter.notifyItemRangeChanged(pos, list.size() - pos);

                if (list.size() == 0)
                    mReplaceViewHelper.toReplaceView(recycler_view, R.layout.no_data_page);
            }
        });
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
        mRefreshLayout.endLoadingMore();
        mRefreshLayout.endRefreshing();
        Logger.e(err);
        Constants.showErrorDialog(mContext, err);
    }

    @Override
    public void success(VisitorBean bean) {
        mRefreshLayout.endLoadingMore();
        mRefreshLayout.endRefreshing();
        if (bean != null) {
            pages = bean.getPages();
            current = bean.getCurrent();
            List<VisitorBean.RecordsBean> records = bean.getRecords();
            if (records != null && records.size() > 0) {
                list.addAll(records);
                if (current > 1) {
                    mAdapter.setLists(list);
                    recycler_view.smoothScrollToPosition(0);
                } else {
                    mAdapter.setLists(list);
                }

                mReplaceViewHelper.removeView();
                return;
            }
        }
        mReplaceViewHelper.toReplaceView(recycler_view, R.layout.no_data_page);
    }

    @Override
    public void dealSuccess(String str) {
    }

    @Override
    public void addInviteSuccess(String str) {

    }

    @Override
    public void getHistorySuccess(VisitorBean history) {

    }

    @Override
    public void deleteHistorySuccess(String string) {

    }


    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout bgaRefreshLayout) {
        current = 1;
        list = new ArrayList<>();
        bgaRefreshLayout.beginRefreshing();
        presenter.getVisitorHistory(DataUtil.getToken(mContext), current, 10, 2);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout bgaRefreshLayout) {
        if (current < pages) {
            current++;
            presenter.getVisitorHistory(DataUtil.getToken(mContext), current, 10, 2);
            return true;
        } else return false;
    }

}

