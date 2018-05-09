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
import com.bangjiat.bjt.module.secretary.service.adapter.AdminListAdapter;
import com.bangjiat.bjt.module.secretary.service.beans.BuildingAdminListResult;
import com.bangjiat.bjt.module.secretary.service.contract.BuildingAdminListContract;
import com.bangjiat.bjt.module.secretary.service.presenter.BuildingAdminListPresenter;
import com.dou361.dialogui.DialogUIUtils;

import java.util.List;

import butterknife.BindView;

public class BuildingAdminListActivity extends BaseColorToolBarActivity implements BuildingAdminListContract.View {
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    private Dialog dialog;
    private BuildingAdminListContract.Presenter presenter;
    private List<BuildingAdminListResult> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        presenter = new BuildingAdminListPresenter(this);
        presenter.getAdminList(DataUtil.getToken(mContext));
        recycler_view.setLayoutManager(new LinearLayoutManager(mContext));
        recycler_view.setHasFixedSize(true);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_building_admin_list;
    }

    @Override
    protected String getTitleStr() {
        return "选择审批人";
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
        Toast.makeText(mContext, err, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void success(List<BuildingAdminListResult> result) {
        if (result != null) {
            list = result;
            setAdapter();
        }
    }

    private void setAdapter() {
        AdminListAdapter mAdapter = new AdminListAdapter(list, mContext);
        recycler_view.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new AdminListAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                BuildingAdminListResult bean = list.get(position);
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", bean);
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
