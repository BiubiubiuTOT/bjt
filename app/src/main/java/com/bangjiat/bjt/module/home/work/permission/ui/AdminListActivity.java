package com.bangjiat.bjt.module.home.work.permission.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.module.main.ui.activity.BaseColorToolBarActivity;
import com.bangjiat.bjt.module.me.personaldata.beans.CompanyUserBean;
import com.bangjiat.bjt.module.secretary.workers.adapter.SelectPeopleAdapter;
import com.bangjiat.bjt.module.secretary.workers.beans.WorkersResult;
import com.bangjiat.bjt.module.secretary.workers.contract.CompanyUserContract;
import com.bangjiat.bjt.module.secretary.workers.presenter.CompanyUserPresenter;
import com.dou361.dialogui.DialogUIUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class AdminListActivity extends BaseColorToolBarActivity implements CompanyUserContract.View {
    @BindView(R.id.tv_company_name)
    TextView tv_company_name;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private List<WorkersResult.RecordsBean> beans;
    private SelectPeopleAdapter adapter;
    private Dialog dialog;
    private CompanyUserContract.Presenter presenter;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_admin_list;
    }

    @Override
    protected String getTitleStr() {
        return "管理员列表";
    }

    private void initData() {
        beans = new ArrayList<>();
        presenter = new CompanyUserPresenter(this);
        token = DataUtil.getToken(mContext);
        presenter.getCompanyUser(token, 1, 10, 4);
        CompanyUserBean first = CompanyUserBean.first(CompanyUserBean.class);
        if (first != null)
            tv_company_name.setText(first.getCompanyName());
    }

    private void setAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setHasFixedSize(true);
        adapter = new SelectPeopleAdapter(beans, mContext);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new SelectPeopleAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(mContext, AdminDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", beans.get(position));
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
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

}
