package com.bangjiat.bjt.module.park.apply.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.common.RefreshViewHolder;
import com.bangjiat.bjt.module.main.ui.activity.BaseWhiteToolBarActivity;
import com.bangjiat.bjt.module.me.personaldata.beans.SpaceUser;
import com.bangjiat.bjt.module.park.apply.adapter.ApplyHistoryAdapter;
import com.bangjiat.bjt.module.park.apply.beans.LotResult;
import com.bangjiat.bjt.module.park.apply.beans.ParkApplyHistoryResult;
import com.bangjiat.bjt.module.park.apply.beans.ParkingResult;
import com.bangjiat.bjt.module.park.apply.contract.ParkApplyContract;
import com.bangjiat.bjt.module.park.apply.presenter.ParkApplyPresenter;
import com.bangjiat.bjt.module.park.car.beans.CarBean;
import com.dou361.dialogui.DialogUIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

public class ApplyHistoryActivity extends BaseWhiteToolBarActivity implements ParkApplyContract.View
        , BGARefreshLayout.BGARefreshLayoutDelegate {
    private static final int DEAL_SUCCESS = 2;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    @BindView(R.id.ll_none)
    LinearLayout ll_none;
    @BindView(R.id.rl_refresh)
    BGARefreshLayout mRefreshLayout;
    private List<ParkApplyHistoryResult.RecordsBean> list;
    private Dialog dialog;
    private ParkApplyContract.Presenter presenter;
    private ApplyHistoryAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }


    private void initData() {
        presenter = new ParkApplyPresenter(this);

        getData();
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        recycler_view.setHasFixedSize(true);
        mRefreshLayout.setDelegate(this);
        mRefreshLayout.setRefreshViewHolder(new RefreshViewHolder(mContext, false));
        setAdapter();
    }

    private void getData() {
        boolean parkAdmin = Constants.isParkAdmin();
        SpaceUser user = SpaceUser.first(SpaceUser.class);
        if (!parkAdmin)
            presenter.getParkApplyHistory(DataUtil.getToken(mContext), 1, 10, 0);
        else
            presenter.getParkApplyHistory(DataUtil.getToken(mContext), 1, 10, user.getSpaceId());
    }

    private void setAdapter() {
        list = new ArrayList<>();
        mAdapter = new ApplyHistoryAdapter(list, mContext);
        recycler_view.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ApplyHistoryAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(mContext, ApplyDetailActivity.class);
                intent.putExtra("data", list.get(position));
                startActivityForResult(intent, DEAL_SUCCESS);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == DEAL_SUCCESS) {
                getData();
            }
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_apply_history2;
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
    public void error(String err) {
        mRefreshLayout.endRefreshing();
        Toast.makeText(mContext, err, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getWorkersCarSuccess(List<CarBean> list) {

    }

    @Override
    public void getParkSpaceSuccess(ParkingResult s) {

    }

    @Override
    public void parkApplySuccess() {

    }

    @Override
    public void dealParkApplySuccess() {

    }

    @Override
    public void getParkApplyHistorySuccess(ParkApplyHistoryResult result) {
        mRefreshLayout.endRefreshing();
        if (result != null) {
            List<ParkApplyHistoryResult.RecordsBean> records = result.getRecords();
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
    public void getLotListSuccess(List<LotResult> results) {

    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout bgaRefreshLayout) {
        bgaRefreshLayout.beginRefreshing();
        getData();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout bgaRefreshLayout) {
        return false;
    }
}
