package com.bangjiat.bjt.module.park.pay.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.common.RefreshViewHolder;
import com.bangjiat.bjt.module.main.ui.activity.BaseWhiteToolBarActivity;
import com.bangjiat.bjt.module.park.pay.adapter.PayHistoryAdapter;
import com.bangjiat.bjt.module.park.pay.beans.ParkPayHistory;
import com.bangjiat.bjt.module.park.pay.beans.ParkingDetail;
import com.bangjiat.bjt.module.park.pay.beans.PayListResult;
import com.bangjiat.bjt.module.park.pay.contract.PayContract;
import com.bangjiat.bjt.module.park.pay.presenter.PayPresenter;
import com.dou361.dialogui.DialogUIUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

public class PayHistoryActivity extends BaseWhiteToolBarActivity implements PayContract.View
        , BGARefreshLayout.BGARefreshLayoutDelegate {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.ll_none)
    LinearLayout ll_none;
    @BindView(R.id.rl_refresh)
    BGARefreshLayout mRefreshLayout;
    private Dialog dialog;
    private PayContract.Presenter presenter;
    private PayHistoryAdapter mAdapter;
    private List<ParkPayHistory.RecordsBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }

    private void initData() {
        presenter = new PayPresenter(this);
        presenter.getParkPayHistory(DataUtil.getToken(mContext), "", 1, 10);

        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setHasFixedSize(true);

        mAdapter = new PayHistoryAdapter(list, mContext);
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new PayHistoryAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(mContext, PayDetailActivity.class);
                intent.putExtra("data", list.get(position));
                startActivity(intent);
            }
        });
        mRefreshLayout.setDelegate(this);
        mRefreshLayout.setRefreshViewHolder(new RefreshViewHolder(mContext, false));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_pay_history;
    }

    @Override
    protected String getTitleStr() {
        return "缴费记录";
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
    public void getPayListSuccess(List<PayListResult> str) {

    }

    @Override
    public void paySuccess(String str) {

    }

    @Override
    public void addPayInfoSuccess(String err) {

    }

    @Override
    public void getParkingDetailSuccess(ParkingDetail detail) {

    }

    @Override
    public void fail(String err) {
        Logger.e(err);
        mRefreshLayout.endRefreshing();
        Constants.showErrorDialog(mContext, err);
    }

    @Override
    public void payBillSuccess(String string) {

    }

    @Override
    public void getParkPayHistorySuccess(ParkPayHistory history) {
        mRefreshLayout.endRefreshing();
        if (history != null) {
            List<ParkPayHistory.RecordsBean> records = history.getRecords();
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
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout bgaRefreshLayout) {
        bgaRefreshLayout.beginRefreshing();
        presenter.getParkPayHistory(DataUtil.getToken(mContext), "", 1, 10);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout bgaRefreshLayout) {
        return false;
    }
}
