package com.bangjiat.bjt.module.secretary.service.ui;

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
import com.bangjiat.bjt.module.secretary.service.adapter.HistoryAdapter;
import com.bangjiat.bjt.module.secretary.service.beans.ServiceApplyHistoryResult;
import com.bangjiat.bjt.module.secretary.service.contract.ServiceApplyHistoryContract;
import com.bangjiat.bjt.module.secretary.service.presenter.ServiceApplyHistoryPresenter;
import com.dou361.dialogui.DialogUIUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ServiceHistoryActivity extends BaseColorToolBarActivity implements ServiceApplyHistoryContract.View {
    private static final int DEAL = 2;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    @BindView(R.id.ll_none)
    LinearLayout ll_none;

    private List<ServiceApplyHistoryResult.RecordsBean> list;
    private Dialog dialog;
    private ServiceApplyHistoryContract.Presenter presenter;
    private HistoryAdapter mAdapter;
    private String token;

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
        presenter = new ServiceApplyHistoryPresenter(this);
        token = DataUtil.getToken(mContext);
        getData();
        setAdapter();
    }

    private void getData() {
        if (Constants.isCompanyAdmin()) {
            presenter.getHistory(token, 1, 10);
        } else if (Constants.isBuildingAdmin()) {
            BuildUser first = BuildUser.first(BuildUser.class);
            Logger.d(first.toString());
            presenter.getAdminHistory(token, first.getBuildId(), 1, 10, 2);
        }
    }

    private void setAdapter() {
        list = new ArrayList<>();
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        recycler_view.setHasFixedSize(true);
        mAdapter = new HistoryAdapter(list, mContext);
        recycler_view.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new HistoryAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                Bundle extras = new Bundle();
                extras.putSerializable("data", list.get(position));
                intent.putExtras(extras);
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
            if (list != null && list.size() > 0) {
                Logger.d(list.toString());
                mAdapter.setLists(list);
                ll_none.setVisibility(View.GONE);
                return;
            }
        }
        ll_none.setVisibility(View.VISIBLE);
    }

    @Override
    public void error(String err) {
        Constants.showErrorDialog(mContext, err);
    }

    @Override
    public void approvalServiceSuccess() {

    }

    @Override
    public void getAdminHistorySuccess(ServiceApplyHistoryResult result) {
        if (result != null) {
            list = result.getRecords();
            if (list != null && list.size() > 0) {
                Logger.d(list.toString());
                mAdapter.setLists(list);
                ll_none.setVisibility(View.GONE);
                return;
            }
        }
        ll_none.setVisibility(View.VISIBLE);
    }
}
