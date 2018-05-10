package com.bangjiat.bjt.module.secretary.door.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.module.main.ui.activity.BaseColorToolBarActivity;
import com.bangjiat.bjt.module.secretary.door.adapter.ApplyHistoryAdapter;
import com.bangjiat.bjt.module.secretary.door.beans.ApplyHistoryBean;
import com.bangjiat.bjt.module.secretary.door.contract.DoorApplyHistoryContract;
import com.bangjiat.bjt.module.secretary.door.presenter.DoorApplyHistoryPresenter;
import com.dou361.dialogui.DialogUIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HistoryActivity extends BaseColorToolBarActivity implements DoorApplyHistoryContract.View {
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;

    private List<ApplyHistoryBean.RecordsBean> list;
    private Dialog dialog;
    private DoorApplyHistoryContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        list = new ArrayList<>();
        presenter = new DoorApplyHistoryPresenter(this);
        presenter.getDoorApplyHistory(DataUtil.getToken(mContext), 1, 10);
    }

    private void setAdapter() {
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        recycler_view.setHasFixedSize(true);
        ApplyHistoryAdapter mAdapter = new ApplyHistoryAdapter(list, mContext);
        recycler_view.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ApplyHistoryAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ApplyHistoryBean.RecordsBean recordsBean = list.get(position);
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("name", recordsBean.getCompanyName());
                intent.putExtra("id", recordsBean.getGuardMainId());
                startActivity(intent);
            }
        });
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
                setAdapter();
            }
        }
    }

    @Override
    public void error(String err) {
        Toast.makeText(mContext, err, Toast.LENGTH_SHORT).show();
    }
}
