package com.bangjiat.bjt.module.secretary.door.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.common.RefreshViewHolder;
import com.bangjiat.bjt.common.ReplaceViewHelper;
import com.bangjiat.bjt.module.main.ui.activity.BaseColorToolBarActivity;
import com.bangjiat.bjt.module.me.personaldata.beans.BuildUser;
import com.bangjiat.bjt.module.secretary.door.adapter.ApplyHistoryAdapter;
import com.bangjiat.bjt.module.secretary.door.beans.ApplyHistoryBean;
import com.bangjiat.bjt.module.secretary.door.contract.DoorApplyHistoryContract;
import com.bangjiat.bjt.module.secretary.door.presenter.DoorApplyHistoryPresenter;
import com.dou361.dialogui.DialogUIUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

public class HistoryActivity extends BaseColorToolBarActivity implements DoorApplyHistoryContract.View
        , BGARefreshLayout.BGARefreshLayoutDelegate {
    private static final int DEAL = 2;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    @BindView(R.id.rl_refresh)
    BGARefreshLayout mRefreshLayout;

    private List<ApplyHistoryBean.RecordsBean> list;
    private Dialog dialog;
    private DoorApplyHistoryContract.Presenter presenter;
    private String token;
    private ApplyHistoryAdapter mAdapter;
    private int pages;
    private int current = 1;
    private BuildUser first;
    private ReplaceViewHelper mReplaceViewHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        mReplaceViewHelper = new ReplaceViewHelper(this);
        list = new ArrayList<>();
        presenter = new DoorApplyHistoryPresenter(this);
        token = DataUtil.getToken(mContext);
        first = BuildUser.first(BuildUser.class);

        getData();
        setAdapter();
        mRefreshLayout.setDelegate(this);
        mRefreshLayout.setRefreshViewHolder(new RefreshViewHolder(mContext, true));
    }

    private void getData() {
        if (Constants.isCompanyAdmin()) {
            presenter.getDoorApplyHistory(token, current, 10);
        } else if (Constants.isBuildingAdmin()) {
            presenter.getAdminDoorApplyHistory(token, first.getBuildId(), current, 10);
        }
    }

    private void setAdapter() {
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        recycler_view.setHasFixedSize(true);
        mAdapter = new ApplyHistoryAdapter(list, mContext);
        recycler_view.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ApplyHistoryAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ApplyHistoryBean.RecordsBean recordsBean = list.get(position);
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("data", recordsBean);
                startActivityForResult(intent, DEAL);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == DEAL) {
            getData();
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_apply_history;
    }

    @Override
    protected String getTitleStr() {
        return "申请记录";
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
    public void getDoorApplyHistorySuccess(ApplyHistoryBean bean) {
        setData(bean);
    }

    @Override
    public void error(String err) {
        mRefreshLayout.endLoadingMore();
        mRefreshLayout.endRefreshing();
        Logger.e(err);
        Constants.showErrorDialog(mContext, err);
    }

    @Override
    public void getAdminDoorApplyHistorySuccess(ApplyHistoryBean bean) {
        setData(bean);
    }

    private void setData(ApplyHistoryBean bean) {
        mRefreshLayout.endRefreshing();
        mRefreshLayout.endLoadingMore();
        if (bean != null) {
            pages = bean.getPages();
            current = bean.getCurrent();
            List<ApplyHistoryBean.RecordsBean> records = bean.getRecords();
            if (records != null && records.size() > 0) {
                list = records;
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
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout bgaRefreshLayout) {
        current = 1;
        list = new ArrayList<>();
        bgaRefreshLayout.beginRefreshing();
        getData();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout bgaRefreshLayout) {
        if (current < pages) {
            current++;
            getData();
            return true;
        } else return false;
    }
}
