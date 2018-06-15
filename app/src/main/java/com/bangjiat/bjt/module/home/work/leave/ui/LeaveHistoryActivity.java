package com.bangjiat.bjt.module.home.work.leave.ui;

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
import com.bangjiat.bjt.module.home.work.leave.adapter.LeaveHistoryAdapter;
import com.bangjiat.bjt.module.home.work.leave.beans.CompanyLeaveResult;
import com.bangjiat.bjt.module.home.work.leave.contract.LeaveContract;
import com.bangjiat.bjt.module.home.work.leave.presenter.LeavePresenter;
import com.bangjiat.bjt.module.main.ui.activity.BaseWhiteToolBarActivity;
import com.dou361.dialogui.DialogUIUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

public class LeaveHistoryActivity extends BaseWhiteToolBarActivity implements LeaveContract.View, BGARefreshLayout.BGARefreshLayoutDelegate {
    private static final int DEAL_SUCCESS = 2;
    private Dialog dialog;
    private LeaveContract.Presenter presenter;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.rl_refresh)
    BGARefreshLayout mRefreshLayout;
    private LeaveHistoryAdapter mAdapter;
    private List<CompanyLeaveResult.RecordsBean> list;
    private String token;
    private ReplaceViewHelper mReplaceViewHelper;
    private int pages;
    private int current = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        presenter = new LeavePresenter(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        token = DataUtil.getToken(mContext);
        mRefreshLayout.setDelegate(this);
        mRefreshLayout.setRefreshViewHolder(new RefreshViewHolder(mContext, true));
        getData();
        setAdapter();
        mReplaceViewHelper = new ReplaceViewHelper(this);
    }

    private void getData() {
        if (Constants.isCompanyAdmin() || Constants.isWorkAdmin()) {//公司管理员或者工作台管理员可以查看公司请假信息
            presenter.getCompanyLeave(token, 2, current, 10);
        } else {
            presenter.getSelfLeave(token, 1, current, 10);
        }
    }

    private void setAdapter() {
        list = new ArrayList<>();
        mAdapter = new LeaveHistoryAdapter(list, mContext);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new LeaveHistoryAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                CompanyLeaveResult.RecordsBean recordsBean = list.get(position);
                Intent intent = new Intent(mContext, LeaveDetailActivity.class);
                intent.putExtra("data", recordsBean);
                startActivityForResult(intent, DEAL_SUCCESS);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == DEAL_SUCCESS) {
            current = 1;
            list = new ArrayList<>();
            getData();
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_leave_history;
    }

    @Override
    protected String getTitleStr() {
        return "请假申请";
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
        Constants.showErrorDialog(this, err);
    }

    @Override
    public void addLeaveSuccess() {

    }

    @Override
    public void getCompanyLeaveSuccess(CompanyLeaveResult result) {
        setData(result);
    }

    @Override
    public void getSelfLeaveSuccess(CompanyLeaveResult result) {
        setData(result);
    }

    private void setData(CompanyLeaveResult result) {
        mRefreshLayout.endRefreshing();
        mRefreshLayout.endLoadingMore();
        if (result != null) {
            pages = result.getPages();
            current = result.getCurrent();
            List<CompanyLeaveResult.RecordsBean> records = result.getRecords();
            if (records != null && records.size() > 0) {
                list.addAll(records);
                if (current > 1) {
                    mAdapter.setLists(list);
                    recyclerView.smoothScrollToPosition(0);
                } else {
                    mAdapter.setLists(list);
                }

                mReplaceViewHelper.removeView();
                return;
            }
        }
        mReplaceViewHelper.toReplaceView(recyclerView, R.layout.no_data_page);
    }

    @Override
    public void dealLeaveSuccess() {

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
