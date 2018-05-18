package com.bangjiat.bjt.module.home.work.worker.ui;

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

import com.adorkable.iosdialog.AlertDialog;
import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.module.main.ui.activity.BaseToolBarActivity;
import com.bangjiat.bjt.module.me.personaldata.beans.UserInfo;
import com.bangjiat.bjt.module.secretary.workers.adapter.SelectPeopleAdapter;
import com.bangjiat.bjt.module.secretary.workers.beans.WorkersResult;
import com.bangjiat.bjt.module.secretary.workers.contract.CompanyUserContract;
import com.bangjiat.bjt.module.secretary.workers.presenter.CompanyUserPresenter;
import com.bangjiat.bjt.module.secretary.workers.ui.AddWorkersActivity;
import com.bangjiat.bjt.module.secretary.workers.ui.UpdateWorkerActivity;
import com.dou361.dialogui.DialogUIUtils;
import com.githang.statusbar.StatusBarCompat;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

public class WorkerListActivity extends BaseToolBarActivity implements CompanyUserContract.View {
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
    @BindView(R.id.card)
    CardView card;

    private Toolbar toolbar;

    private List<WorkersResult.RecordsBean> beans;
    private SelectPeopleAdapter adapter;
    private TextView tv_all;
    private TextView tv_done;
    private Dialog dialog;
    private CompanyUserContract.Presenter presenter;
    private String token;
    private int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.white));
        initData();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_worker_list;
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
        toolbar.setTitle("");
        toolbar.setBackgroundColor(getResources().getColor(R.color.white));
        tv_all = toolbar.findViewById(R.id.toolbar_cancel);
        tv_done = toolbar.findViewById(R.id.toolbar_other);
        TextView tv_title = toolbar.findViewById(R.id.toolbar_title);

        tv_all.setText("全选");
        tv_done.setText("完成");
        tv_title.setText("员工列表");
        tv_all.setTextColor(getResources().getColor(R.color.black));
        tv_done.setTextColor(getResources().getColor(R.color.black));
        tv_title.setTextColor(getResources().getColor(R.color.black));
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
        tv_all.setText("全选");
        toolbar.setNavigationIcon(R.mipmap.back_black);
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
        if (Constants.hasPermission()) card.setVisibility(View.VISIBLE);

        EventBus.getDefault().register(this);
        beans = new ArrayList<>();
        presenter = new CompanyUserPresenter(this);
        token = DataUtil.getToken(mContext);
        presenter.getCompanyUser(token, 1, 10, 1);
    }

    private void setAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setHasFixedSize(true);
        adapter = new SelectPeopleAdapter(beans, mContext);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new SelectPeopleAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(mContext, UpdateWorkerActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", beans.get(position));
                intent.putExtras(bundle);
                mContext.startActivity(intent);
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

    private List<String> getSelectItem() {
        List<String> select = new ArrayList<>();
        UserInfo userInfo = UserInfo.first(UserInfo.class);
        HashMap<Integer, Boolean> map = adapter.getMap();
        Set<Map.Entry<Integer, Boolean>> entries = map.entrySet();
        for (Map.Entry<Integer, Boolean> entry : entries) {
            if (entry.getValue()) {
                String userId = beans.get(entry.getKey()).getUserId();
                if (!userInfo.getUserId().equals(userId))
                    select.add(userId);
            }
        }
        return select;
    }

    @OnClick(R.id.tv_delete_workers)
    public void clickDeleteWorkers(View view) {
        if (adapter != null) {
            adapter.setShowCheck(true);
            showDelete();
        }
    }

    @OnClick(R.id.tv_add_workers)
    public void addWorkers(View view) {
        startActivity(new Intent(mContext, AddWorkersActivity.class));
    }

    @OnClick(R.id.tv_delete)
    public void clickDelete(View view) {
        if (getSelectCount() != 0)
            showDeleteDialog();
    }

    private void showDeleteDialog() {
        new AlertDialog(this).builder()
                .setMsg("确认删除吗?")
                .setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showCustom();
                        List<String> selectItem = getSelectItem();
                        size = selectItem.size();
                        Logger.d(selectItem.toString());
                        for (String s : selectItem) {
                            String token = DataUtil.getToken(mContext);
                            presenter.deleteCompanyUser(token, s);
                        }
                    }
                }).setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String s) {
        presenter.getCompanyUser(DataUtil.getToken(mContext), 1, 10, 1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
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
                if (beans.size() > 0)
                    tv_company_name.setText(beans.get(0).getCompanyName());
            }
        }
    }

    @Override
    public void deleteCompanyUserSuccess() {
        size--;
        if (size == 0) {
            error("删除成功");
            presenter.getCompanyUser(token, 1, 10, 1);
        }
    }

    @Override
    public void updateCompanyUserSuccess() {

    }

    @Override
    public void addCompanyUserSuccess() {

    }
}
