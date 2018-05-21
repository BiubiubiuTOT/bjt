package com.bangjiat.bjt.module.home.work.permission.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.adorkable.iosdialog.AlertDialog;
import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.module.home.work.permission.contract.PermissionContract;
import com.bangjiat.bjt.module.home.work.permission.presenter.PermissionPresenter;
import com.bangjiat.bjt.module.main.ui.activity.BaseToolBarActivity;
import com.bangjiat.bjt.module.me.personaldata.beans.UserInfo;
import com.bangjiat.bjt.module.secretary.workers.adapter.SelectPeopleAdapter;
import com.bangjiat.bjt.module.secretary.workers.beans.WorkersResult;
import com.bangjiat.bjt.module.secretary.workers.contract.CompanyUserContract;
import com.bangjiat.bjt.module.secretary.workers.presenter.CompanyUserPresenter;
import com.bangjiat.bjt.module.secretary.workers.ui.UpdateWorkerActivity;
import com.dou361.dialogui.DialogUIUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;

public class DeleteAdminActivity extends BaseToolBarActivity implements CompanyUserContract.View, PermissionContract.View {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private List<WorkersResult.RecordsBean> beans;
    private SelectPeopleAdapter adapter;
    private TextView tv_done;
    private Dialog dialog;
    private CompanyUserContract.Presenter presenter;
    private String token;
    private PermissionContract.Presenter permissionPresenter;
    private UserInfo info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_delete_admin;
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        tv_done = toolbar.findViewById(R.id.toolbar_other);
        tv_done.setVisibility(View.VISIBLE);
        TextView tv_title = toolbar.findViewById(R.id.toolbar_title);

        tv_done.setText("完成");
        tv_title.setText("删除管理员");

        tv_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getSelectCount() > 0) {
                    showAddDialog();
                }

            }
        });
    }


    private void initData() {
        info = UserInfo.first(UserInfo.class);
        beans = new ArrayList<>();
        presenter = new CompanyUserPresenter(this);
        permissionPresenter = new PermissionPresenter(this);
        token = DataUtil.getToken(mContext);
        presenter.getCompanyUser(token, 1, 10, 3);
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
//                mContext.startActivity(intent);
            }
        });
        adapter.setOnCheckChangedListener(new SelectPeopleAdapter.OnCheckListener() {
            @Override
            public void onCheckChanged() {
                int select = getSelectCount();
                if (select == 0) {
                    tv_done.setText("完成");
                } else {
                    tv_done.setText("完成（" + select + "）");
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

    private String[] getSelectItem() {
        List<String> select = new ArrayList<>();
        HashMap<Integer, Boolean> map = adapter.getMap();
        Set<Map.Entry<Integer, Boolean>> entries = map.entrySet();
        for (Map.Entry<Integer, Boolean> entry : entries) {
            if (entry.getValue()) {
                String userId = beans.get(entry.getKey()).getUserId();
                select.add(userId);
            }
        }
        return select.toArray(new String[select.size()]);
    }


    private void showAddDialog() {
        new AlertDialog(this).builder()
                .setMsg("确认删除吗?")
                .setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String[] selectItem = getSelectItem();
                        Logger.d(selectItem);
                        if (adapter.getItemCount() == 1) {
                            if (DataUtil.isIntoBuilding(mContext)) {
                                error("请联系楼宇管理员");
                                return;
                            }
                        }
                        permissionPresenter.deleteAdmin(token, selectItem);
                    }
                }).setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }).show();
    }


    @Override
    public void showDialog() {
        if (dialog != null) {
            if (!dialog.isShowing())
                dialog.show();
        } else {
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
    public void getCompanyUserSuccess(WorkersResult result) {
        if (result != null) {
            List<WorkersResult.RecordsBean> records = result.getRecords();
            if (records != null) {
                beans = records;
                setAdapter();
                adapter.setShowCheck(true);
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
    public void deleteAdminSuccess() {
        showDia();
    }

    private void showDia() {
        new AlertDialog(this).builder().setMsg("删除成功").setCancelable(false).
                setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setResult(RESULT_OK);
                        finish();
                    }
                }).show();
    }

    @Override
    public void addAdminSuccess() {

    }

    @Override
    public void updateAdminSuccess() {

    }
}
