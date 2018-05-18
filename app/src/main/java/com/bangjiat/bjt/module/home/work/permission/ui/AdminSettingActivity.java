package com.bangjiat.bjt.module.home.work.permission.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.module.main.ui.activity.BaseToolBarActivity;
import com.bangjiat.bjt.module.secretary.workers.adapter.SelectPeopleAdapter;
import com.bangjiat.bjt.module.secretary.workers.beans.WorkersResult;
import com.bangjiat.bjt.module.secretary.workers.contract.CompanyUserContract;
import com.bangjiat.bjt.module.secretary.workers.presenter.CompanyUserPresenter;
import com.dou361.dialogui.DialogUIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AdminSettingActivity extends BaseToolBarActivity implements CompanyUserContract.View {
    private static final int UPDATE_ADMIN_SUCCESS = 1;
    private Dialog dialog;
    private CompanyUserContract.Presenter presenter;
    private String token;
    private List<WorkersResult.RecordsBean> beans;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.card)
    CardView card;
    private SelectPeopleAdapter adapter;
    private int type;
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        type = getIntent().getIntExtra("type", 0);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setHasFixedSize(true);
        beans = new ArrayList<>();
        presenter = new CompanyUserPresenter(this);
        token = DataUtil.getToken(mContext);
        presenter.getCompanyUser(token, 1, 10, 3);

        if (type == 0) {
            tv_title.setText("管理员设置");
            if (!Constants.hasPermission())
                card.setVisibility(View.GONE);
        } else {
            tv_title.setText("选择审批人");
            card.setVisibility(View.GONE);
        }
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
        Toast.makeText(mContext, err, Toast.LENGTH_SHORT).show();
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

    private void setAdapter() {
        adapter = new SelectPeopleAdapter(beans, mContext);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new SelectPeopleAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (type == 1) {
                    WorkersResult.RecordsBean bean = beans.get(position);
                    Intent intent = new Intent();
                    intent.putExtra("data", bean);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
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

    @OnClick(R.id.tv_delete_admin)
    public void clickDeleteAdmin(View view) {
        startActivityForResult(new Intent(mContext, DeleteAdminActivity.class), UPDATE_ADMIN_SUCCESS);
    }

    @OnClick(R.id.tv_add_admin)
    public void clickAddAdmin(View view) {
        startActivityForResult(new Intent(mContext, AddAdminActivity.class), UPDATE_ADMIN_SUCCESS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == UPDATE_ADMIN_SUCCESS) {
                presenter.getCompanyUser(token, 1, 10, 3);
            }
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_admin_setting;
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        tv_title = findViewById(R.id.toolbar_title);
        toolbar.setNavigationIcon(R.mipmap.back_white);
    }

}
