package com.bangjiat.bjt.module.home.work.permission.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.common.RefreshViewHolder;
import com.bangjiat.bjt.module.main.ui.activity.BaseToolBarActivity;
import com.bangjiat.bjt.module.secretary.workers.adapter.SelectPeopleAdapter;
import com.bangjiat.bjt.module.secretary.workers.adapter.TextAdapter;
import com.bangjiat.bjt.module.secretary.workers.beans.WorkersResult;
import com.bangjiat.bjt.module.secretary.workers.contract.CompanyUserContract;
import com.bangjiat.bjt.module.secretary.workers.presenter.CompanyUserPresenter;
import com.dou361.dialogui.DialogUIUtils;
import com.jiang.android.indicatordialog.IndicatorBuilder;
import com.jiang.android.indicatordialog.IndicatorDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

public class AdminSettingActivity extends BaseToolBarActivity implements CompanyUserContract.View, BGARefreshLayout.BGARefreshLayoutDelegate {
    private static final int UPDATE_ADMIN_SUCCESS = 1;
    private Dialog dialog;
    private CompanyUserContract.Presenter presenter;
    private String token;
    private List<WorkersResult.RecordsBean> beans;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.rl_refresh)
    BGARefreshLayout mRefreshLayout;

    private SelectPeopleAdapter adapter;
    private int type;
    private TextView tv_title;
    private ImageView img;
    private IndicatorDialog addDialog;
    private List<String> texts;
    private TextAdapter textAdapter;

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
        } else {
            tv_title.setText("选择审批人");
            img.setVisibility(View.GONE);
        }

        texts = new ArrayList<>();
        texts.add("添加管理员");
        texts.add("删除管理员");
        textAdapter = new TextAdapter(texts);
        textAdapter.setOnItemClickListener(new TextAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                addDialog.dismiss();
                if (position != 0) {
                    startActivityForResult(new Intent(mContext, DeleteAdminActivity.class), UPDATE_ADMIN_SUCCESS);
                } else {
                    startActivityForResult(new Intent(mContext, AddAdminActivity.class), UPDATE_ADMIN_SUCCESS);
                }
            }
        });
        initDialog();
        mRefreshLayout.setDelegate(this);
        mRefreshLayout.setRefreshViewHolder(new RefreshViewHolder(mContext, false));
        setAdapter();
    }

    private void initDialog() {
        addDialog = new IndicatorBuilder(this)
                .width(350)
                .height(-1)
                .ArrowDirection(IndicatorBuilder.TOP)
                .bgColor(getResources().getColor(R.color.white))
                .radius(8)
                .gravity(IndicatorBuilder.GRAVITY_RIGHT)
                .ArrowRectage(0.9f)
                .layoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false))
                .adapter(textAdapter).create();
        addDialog.setCanceledOnTouchOutside(true);
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
        mRefreshLayout.endRefreshing();
        Constants.showErrorDialog(mContext, err);
    }

    @Override
    public void getCompanyUserSuccess(WorkersResult result) {
        mRefreshLayout.endRefreshing();
        if (result != null) {
            List<WorkersResult.RecordsBean> records = result.getRecords();
            if (records != null) {
                beans = records;
                adapter.setLists(beans);
            }
        }
    }

    private void setAdapter() {
        beans = new ArrayList<>();
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

        img = toolbar.findViewById(R.id.toolbar_image);
        img.setImageResource(R.mipmap.add);

        if (Constants.isCompanyAdmin() || Constants.isWorkAdmin()) {//公司管理员、工作台管理员才能设置管理员
            img.setVisibility(View.VISIBLE);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addDialog.show(img);
                }
            });
        }
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout bgaRefreshLayout) {
        bgaRefreshLayout.beginRefreshing();
        presenter.getCompanyUser(token, 1, 10, 3);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout bgaRefreshLayout) {
        return false;
    }
}
