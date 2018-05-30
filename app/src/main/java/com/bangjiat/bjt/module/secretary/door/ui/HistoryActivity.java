package com.bangjiat.bjt.module.secretary.door.ui;

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

public class HistoryActivity extends BaseColorToolBarActivity implements DoorApplyHistoryContract.View {
    private static final int DEAL = 2;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    @BindView(R.id.ll_none)
    LinearLayout ll_none;

    private List<ApplyHistoryBean.RecordsBean> list;
    private Dialog dialog;
    private DoorApplyHistoryContract.Presenter presenter;
    private String token;
    private ApplyHistoryAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        list = new ArrayList<>();
        presenter = new DoorApplyHistoryPresenter(this);
        token = DataUtil.getToken(mContext);

        getData();
        setAdapter();
    }

    private void getData() {
        if (Constants.isCompanyAdmin()) {
            presenter.getDoorApplyHistory(token, 1, 10);
        } else if (Constants.isBuildingAdmin()) {
            BuildUser first = BuildUser.first(BuildUser.class);
            Logger.d(first.toString());
            presenter.getAdminDoorApplyHistory(token, first.getBuildId(), 1, 10);
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
                intent.putExtra("name", recordsBean.getCompanyName());
                intent.putExtra("id", recordsBean.getGuardMainId());
                intent.putExtra("status", recordsBean.getType());
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
        if (bean != null) {
            List<ApplyHistoryBean.RecordsBean> records = bean.getRecords();
            if (records != null) {
                list = records;
                mAdapter.setLists(list);
                ll_none.setVisibility(View.GONE);
                return;
            }
        }
        ll_none.setVisibility(View.VISIBLE);
    }

    @Override
    public void error(String err) {
        Logger.e(err);
        Constants.showErrorDialog(mContext, err);
    }

    @Override
    public void getAdminDoorApplyHistorySuccess(ApplyHistoryBean bean) {
        if (bean != null) {
            List<ApplyHistoryBean.RecordsBean> records = bean.getRecords();
            if (records != null) {
                list = records;
                mAdapter.setLists(list);

                ll_none.setVisibility(View.GONE);
                return;
            }
        }
        ll_none.setVisibility(View.VISIBLE);
    }
}
