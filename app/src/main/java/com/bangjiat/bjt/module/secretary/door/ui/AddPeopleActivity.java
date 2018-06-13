package com.bangjiat.bjt.module.secretary.door.ui;

import android.app.Dialog;
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
import com.bangjiat.bjt.module.main.ui.activity.BaseToolBarActivity;
import com.bangjiat.bjt.module.me.personaldata.beans.CompanyUserBean;
import com.bangjiat.bjt.module.secretary.door.adapter.SelectPeopleAdapter;
import com.bangjiat.bjt.module.secretary.door.contract.DoorApplyContract;
import com.bangjiat.bjt.module.secretary.door.presenter.DoorApplyPresenter;
import com.bangjiat.bjt.module.secretary.workers.beans.WorkersResult;
import com.dou361.dialogui.DialogUIUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

public class AddPeopleActivity extends BaseToolBarActivity implements DoorApplyContract.View {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.tv_select_all)
    TextView tv_select_all;
    @BindView(R.id.tv_company_name)
    TextView tv_company_name;

    private TextView tv_submit;
    private List<WorkersResult.RecordsBean> beans;
    private SelectPeopleAdapter adapter;
    private Dialog dialog;
    private DoorApplyContract.Presenter presenter;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        presenter = new DoorApplyPresenter(this);
        token = DataUtil.getToken(mContext);
        presenter.getCompanyUser(token, 1, 10, 2);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setHasFixedSize(true);

        CompanyUserBean companyUserBean = CompanyUserBean.first(CompanyUserBean.class);
        if (companyUserBean != null)
            tv_company_name.setText(companyUserBean.getCompanyName());

        setAdapter();
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

    private String[] getSelectBeans() {
        List<String> list = new ArrayList<>();
        HashMap<Integer, Boolean> map = adapter.getMap();
        Set<Map.Entry<Integer, Boolean>> entries = map.entrySet();
        for (Map.Entry<Integer, Boolean> entry : entries) {
            if (entry.getValue()) {
                String userId = beans.get(entry.getKey()).getUserId();
                list.add(userId);
            }
        }
        String[] strings = new String[list.size()];
        return list.toArray(strings);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_add_people;
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        TextView textView = findViewById(R.id.toolbar_title);
        textView.setText("新增人员");
        tv_submit = findViewById(R.id.toolbar_other);
        tv_submit.setText("提交");
        tv_submit.setTextColor(getResources().getColor(R.color.add_people_submit));
        toolbar.setNavigationIcon(R.mipmap.back_white);

        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getSelectCount() > 0) {
                    String[] selectBeans = getSelectBeans();
                    if (selectBeans.length > 0) {
                        Logger.d(selectBeans);
                        presenter.addApply(token, selectBeans);
                    } else {
                        error("请选择非管理人员进行申请");
                    }
                }
            }
        });
    }

    @OnClick(R.id.tv_select_all)
    public void clickSelect(View view) {
        adapter.selectAll();
    }

    @Override
    public void showDialog() {
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        } else
            dialog = DialogUIUtils.showLoadingVertical(mContext, "加载中").show();
    }

    @Override
    public void dismissDialog() {
        if (dialog != null)
            dialog.dismiss();
    }

    @Override
    public void error(String err) {
        Logger.d(err);
        Constants.showErrorDialog(mContext, err);
    }

    @Override
    public void getCompanyUserSuccess(WorkersResult result) {
        if (result != null) {
            List<WorkersResult.RecordsBean> records = result.getRecords();
            if (records != null && records.size() > 0) {
                beans = records;
                adapter.setLists(records);
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

            }
        });
        adapter.setOnCheckChangedListener(new SelectPeopleAdapter.OnCheckListener() {
            @Override
            public void onCheckChanged() {
                int total = adapter.getItemCount();
                int select = getSelectCount();
                if (select == 0) {
                    tv_submit.setTextColor(getResources().getColor(R.color.add_people_submit));
                    tv_select_all.setText("全选");
                } else {
                    if (select == total) {
                        tv_select_all.setText("取消全选");
                    } else {
                        tv_select_all.setText("全选");
                    }
                    tv_submit.setTextColor(getResources().getColor(R.color.white));
                }
            }
        });
    }

    @Override
    public void addApplySuccess() {
//        adapter.selectAll();
//        presenter.getCompanyUser(token, 1, 10, 2);
        showSuccessDialog();
    }

    private void showSuccessDialog() {
        new AlertDialog(this).builder()
                .setMsg("提交成功，等待审核")
                .setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                }).show();
    }
}
