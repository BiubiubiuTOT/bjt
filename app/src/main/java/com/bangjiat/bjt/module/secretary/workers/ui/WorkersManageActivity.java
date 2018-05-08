package com.bangjiat.bjt.module.secretary.workers.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.adorkable.iosdialog.AlertDialog;
import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.module.main.ui.activity.BaseToolBarActivity;
import com.bangjiat.bjt.module.secretary.workers.adapter.SelectPeopleAdapter;
import com.bangjiat.bjt.module.secretary.workers.beans.WorkersResult;
import com.bangjiat.bjt.module.secretary.workers.ui.contract.CompanyUserContract;
import com.bangjiat.bjt.module.secretary.workers.ui.presenter.CompanyUserPresenter;
import com.dou361.dialogui.DialogUIUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 员工管理
 */
public class WorkersManageActivity extends BaseToolBarActivity implements CompanyUserContract.View {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.tv_delete)
    TextView tv_delete;
    @BindView(R.id.tv_delete_workers)
    TextView tv_delete_workers;
    @BindView(R.id.tv_add_workers)
    TextView tv_add_workers;
    @BindView(R.id.tv_company_name)
    TextView tv_company_name;

    private Toolbar toolbar;

    private List<WorkersResult.RecordsBean> beans;
    private SelectPeopleAdapter adapter;
    private TextView tv_all;
    private TextView tv_done;
    private Dialog dialog;
    private CompanyUserContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_workers_manage;
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
        toolbar.setTitle("");
        tv_all = toolbar.findViewById(R.id.toolbar_cancel);
        tv_done = toolbar.findViewById(R.id.toolbar_other);
        TextView tv_title = toolbar.findViewById(R.id.toolbar_title);

        tv_all.setText("全选");
        tv_done.setText("完成");
        tv_title.setText("员工管理");
        showCustom();

        tv_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.selectAll();
            }
        });
        tv_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.setDone();
                showCustom();
            }
        });
    }

    private void showCustom() {
        toolbar.setNavigationIcon(R.mipmap.back_white);
        tv_delete_workers.setVisibility(View.VISIBLE);
        tv_add_workers.setVisibility(View.VISIBLE);
        tv_delete.setVisibility(View.GONE);
        tv_done.setVisibility(View.GONE);
        tv_all.setVisibility(View.GONE);
    }

    private void showDelete() {
        tv_delete_workers.setVisibility(View.GONE);
        tv_add_workers.setVisibility(View.GONE);
        tv_delete.setVisibility(View.VISIBLE);
        tv_all.setVisibility(View.VISIBLE);

        toolbar.setNavigationIcon(null);
        tv_done.setVisibility(View.VISIBLE);
    }

    private void initData() {
        beans = new ArrayList<>();
        presenter = new CompanyUserPresenter(this);
        presenter.getCompanyUser(DataUtil.getToken(mContext), 1, 10, 1);
    }

    private void setAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setHasFixedSize(true);
        adapter = new SelectPeopleAdapter(beans, mContext);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new SelectPeopleAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mContext.startActivity(new Intent(mContext, UpdateWorkerActivity.class));
            }
        });
        adapter.setOnCheckChangedListener(new SelectPeopleAdapter.OnCheckListener() {
            @Override
            public void onCheckChanged() {
                int total = adapter.getItemCount();
                int select = getSelectCount();
                if (select == 0) {
                    tv_delete.setTextColor(getResources().getColor(R.color.red_1));
                    tv_all.setText("全选");
                } else {
                    if (select == total) {
                        tv_all.setText("取消全选");
                    } else {
                        tv_all.setText("全选");
                    }
                    tv_delete.setTextColor(getResources().getColor(R.color.red));
                }
            }
        });
    }

    private int getSelectCount() {
        int select = 0;
        HashMap<Integer, Boolean> map = adapter.getMap();
        Set<Map.Entry<Integer, Boolean>> entries = map.entrySet();
        for (Map.Entry<Integer, Boolean> entry : entries) {
            if (entry.getValue())
                select++;
        }
        return select;
    }

    @OnClick(R.id.tv_delete_workers)
    public void clickDeleteWorkers(View view) {
        adapter.setShowCheck(true);
        showDelete();
    }

    @OnClick(R.id.tv_add_workers)
    public void addWorkers(View view) {
        startActivity(new Intent(mContext, AddWorkersActivity.class));
    }

    @OnClick(R.id.tv_delete)
    public void clickDelete(View view) {
        showDeleteDialog();
    }

    private void showDeleteDialog() {
        new AlertDialog(this).builder()
                .setMsg("确认删除吗?")
                .setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }).setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }).show();
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
    public void getCompanyUserSuccess(WorkersResult result) {
        if (result != null) {
            List<WorkersResult.RecordsBean> records = result.getRecords();
            if (records != null) {
                beans = records;
                setAdapter();
            }
        }
    }
}
