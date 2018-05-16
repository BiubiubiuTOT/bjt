package com.bangjiat.bjt.module.home.work.permission.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.adorkable.iosdialog.AlertDialog;
import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.module.home.work.permission.adapter.WorkersAdapter;
import com.bangjiat.bjt.module.home.work.permission.contract.PermissionContract;
import com.bangjiat.bjt.module.home.work.permission.presenter.PermissionPresenter;
import com.bangjiat.bjt.module.main.ui.activity.BaseWhiteToolBarActivity;
import com.bangjiat.bjt.module.secretary.workers.beans.WorkersResult;
import com.bangjiat.bjt.module.secretary.workers.contract.CompanyUserContract;
import com.bangjiat.bjt.module.secretary.workers.presenter.CompanyUserPresenter;
import com.dou361.dialogui.DialogUIUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PermissionTransferActivity extends BaseWhiteToolBarActivity implements CompanyUserContract.View, PermissionContract.View {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private List<WorkersResult.RecordsBean> beans;
    private WorkersAdapter adapter;
    private CompanyUserContract.Presenter presenter;
    private PermissionContract.Presenter permissionPresenter;
    private String token;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        beans = new ArrayList<>();
        presenter = new CompanyUserPresenter(this);
        permissionPresenter = new PermissionPresenter(this);
        token = DataUtil.getToken(mContext);
        presenter.getCompanyUser(token, 1, 10, 1);

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setHasFixedSize(true);
    }

    private void setAdapter() {
        adapter = new WorkersAdapter(beans, mContext);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new WorkersAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                showDialog(beans.get(position));
            }
        });
    }

    private void showDialog(final WorkersResult.RecordsBean bean) {
        new AlertDialog(mContext).builder().setMsg("确认进行权限转交吗?").
                setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        permissionPresenter.updateAdmin(token, bean.getUserId());
                    }
                }).
                setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }).show();
    }

    @Override
    public void showDialog() {
        if (dialog != null && !dialog.isShowing())
            dialog.show();
        else {
            dialog = DialogUIUtils.showLoadingVertical(mContext, "加载中").show();
        }
    }

    @Override
    public void dismissDialog() {
        if (dialog != null)
            dialog.dismiss();
    }

    @Override
    public void error(String err) {
        Constants.showErrorDialog(mContext, err);
        Logger.e(err);
    }

    @Override
    public void deleteAdminSuccess() {

    }

    @Override
    public void addAdminSuccess() {

    }

    @Override
    public void updateAdminSuccess() {
        Constants.showSuccessExitDialog(this, "权限转交成功");
    }

    @Override
    public void getCompanyUserSuccess(WorkersResult result) {
        if (result != null) {
            List<WorkersResult.RecordsBean> records = result.getRecords();
            if (records != null) {
                beans = records;
                setAdapter();
            }
        }
    }

    @Override
    public void deleteCompanyUserSuccess() {
    }

    @Override
    public void updateCompanyUserSuccess() {

    }

    @Override
    public void addCompanyUserSuccess() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_permission_transfer;
    }

    @Override
    protected String getTitleStr() {
        return "权限转交";
    }
}
