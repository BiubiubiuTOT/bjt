package com.bangjiat.bjt.module.home.visitor.ui;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.BaseFragment;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.common.RefreshViewHolder;
import com.bangjiat.bjt.common.ReplaceViewHelper;
import com.bangjiat.bjt.module.home.scan.ui.OpenDoorCodeActivity;
import com.bangjiat.bjt.module.home.visitor.adapter.VisitorAdapter;
import com.bangjiat.bjt.module.home.visitor.beans.DealVisitorInput;
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


public class HistoryFragment extends BaseFragment implements VisitorContract.View, BGARefreshLayout.BGARefreshLayoutDelegate {
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    @BindView(R.id.rl_refresh)
    BGARefreshLayout mRefreshLayout;


    private Dialog dialog;
    private VisitorContract.Presenter presenter;
    private List<VisitorBean.RecordsBean> list;
    private int type;
    private VisitorBean.RecordsBean recordsBean;
    private VisitorAdapter mAdapter;
    private ReplaceViewHelper mReplaceViewHelper;
    private int pages;
    private int current;


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_history;
    }

    @Override
    protected void initView() {
        mReplaceViewHelper = new ReplaceViewHelper(mContext);
        presenter = new VisitorPresenter(this);
        presenter.getHistory(DataUtil.getToken(mContext), 1, 10);
        setAdapter();
        mRefreshLayout.setDelegate(this);
        mRefreshLayout.setRefreshViewHolder(new RefreshViewHolder(mContext, true));
    }

    private void setAdapter() {
        list = new ArrayList<>();
        recycler_view.setLayoutManager(new LinearLayoutManager(mContext));
        recycler_view.setHasFixedSize(true);
        mAdapter = new VisitorAdapter(list, 2);
        recycler_view.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new VisitorAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(mContext, OpenDoorCodeActivity.class));
            }

            @Override
            public void onAgreeClick(View view, int position) {
                recordsBean = list.get(position);
                type = 2;

                presenter.dealVisitor(DataUtil.getToken(mContext), new DealVisitorInput(recordsBean.getVisitorId(), type));
            }

            @Override
            public void onRefuseClick(View view, int position) {
                recordsBean = list.get(position);
                type = 3;

                presenter.dealVisitor(DataUtil.getToken(mContext), new DealVisitorInput(recordsBean.getVisitorId(), type));
            }

            @Override
            public void onDelete(View view, int pos) {
                String[] strings = new String[1];
                VisitorBean.RecordsBean recordsBean = list.get(pos);
                strings[0] = String.valueOf(recordsBean.getVisitorId());
                DeleteHistory history = new DeleteHistory(1, 2, strings);
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
        Logger.e(err);
        mRefreshLayout.endRefreshing();
        Constants.showErrorDialog(mContext, err);
    }

    @Override
    public void success(VisitorBean bean) {

    }

    @Override
    public void dealSuccess(String str) {
        recordsBean.setStatus(type);
        mAdapter.setLists(list);
    }

    @Override
    public void addInviteSuccess(String str) {

    }

    @Override
    public void getHistorySuccess(VisitorBean history) {
        mRefreshLayout.endRefreshing();
        if (history != null) {
            pages = history.getPages();
            current = history.getCurrent();
            List<VisitorBean.RecordsBean> records = history.getRecords();
            if (records != null && records.size() > 0) {
                Logger.d(records.toString());
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
    public void deleteHistorySuccess(String string) {

    }


    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout bgaRefreshLayout) {
        current = 1;
        list = new ArrayList<>();
        bgaRefreshLayout.beginRefreshing();
        presenter.getHistory(DataUtil.getToken(mContext), current, 10);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout bgaRefreshLayout) {
        if (current < pages) {
            current++;
            presenter.getHistory(DataUtil.getToken(mContext), current, 10);
            return true;
        } else return false;
    }
}

