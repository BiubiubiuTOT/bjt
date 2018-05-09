package com.bangjiat.bjt.module.secretary.service.ui;

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
import com.bangjiat.bjt.module.secretary.service.adapter.HistoryAdapter;
import com.bangjiat.bjt.module.secretary.service.beans.ServiceApplyHistoryResult;
import com.bangjiat.bjt.module.secretary.service.contract.ServiceApplyHistoryContract;
import com.bangjiat.bjt.module.secretary.service.presenter.ServiceApplyHistoryPresenter;
import com.dou361.dialogui.DialogUIUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HistoryActivity extends BaseColorToolBarActivity implements ServiceApplyHistoryContract.View {
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;

    private List<ServiceApplyHistoryResult.RecordsBean> list;
    private Dialog dialog;
    private ServiceApplyHistoryContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_history;
    }

    private void initData() {
        list = new ArrayList<>();
        presenter = new ServiceApplyHistoryPresenter(this);
        presenter.getHistory(DataUtil.getToken(mContext), 1, 10);

        setAdapter();
    }

    private void setAdapter() {
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        recycler_view.setHasFixedSize(true);
        HistoryAdapter mAdapter = new HistoryAdapter(list, mContext);
        recycler_view.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new HistoryAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                Bundle extras = new Bundle();
                extras.putSerializable("data", list.get(position));
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
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
    public void success(ServiceApplyHistoryResult result) {
        if (result != null) {
            list = result.getRecords();
            Logger.d(list.toString());
            setAdapter();
        }
    }

    @Override
    public void error(String err) {
        Toast.makeText(mContext, err, Toast.LENGTH_SHORT).show();
    }
}
