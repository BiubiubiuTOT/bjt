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

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.module.home.scan.ui.CompanyCodeActivity;
import com.bangjiat.bjt.module.home.scan.ui.ScanActivity;
import com.bangjiat.bjt.module.main.ui.activity.BaseToolBarActivity;
import com.bangjiat.bjt.module.secretary.workers.adapter.AddWorkersAdapter;
import com.bangjiat.bjt.module.secretary.workers.beans.WorkersResult;
import com.bangjiat.bjt.module.secretary.workers.ui.contract.CompanyUserContract;
import com.bangjiat.bjt.module.secretary.workers.ui.presenter.CompanyUserPresenter;
import com.dou361.dialogui.DialogUIUtils;
import com.google.gson.JsonSyntaxException;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bertsir.zbar.QrConfig;
import cn.bertsir.zbar.QrManager;

/**
 * 新增员工
 */
public class AddWorkersActivity extends BaseToolBarActivity implements CompanyUserContract.View {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private AddWorkersAdapter mAdapter;
    private Dialog dialog;
    private CompanyUserContract.Presenter presenter;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        presenter = new CompanyUserPresenter(this);
        ArrayList<WorkersResult.RecordsBean> lists = new ArrayList<>();
        lists.add(new WorkersResult.RecordsBean());
        mAdapter = new AddWorkersAdapter(lists);

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_add_workers;
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        TextView tv_title = toolbar.findViewById(R.id.toolbar_title);
        TextView tv_done = toolbar.findViewById(R.id.toolbar_other);

        tv_title.setText("新增员工");
        tv_done.setText("完成");

        tv_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<WorkersResult.RecordsBean> lists = mAdapter.getLists();
                Logger.d(lists.toString());
                i = lists.size();
                for (WorkersResult.RecordsBean bean : lists) {
                    presenter.addCompanyUser(DataUtil.getToken(mContext), bean);
                }
            }
        });

        toolbar.setNavigationIcon(R.mipmap.back_white);
    }

    @OnClick(R.id.iv_add)
    public void clickAdd(View view) {
        mAdapter.add();
    }

    @OnClick(R.id.tv_invite)
    public void clickInvite(View view) {
        startActivity(new Intent(mContext, CompanyCodeActivity.class));
    }

    @OnClick(R.id.tv_add)
    public void clickAddUser(View view) {
        startScan();
    }

    private void startScan() {
        QrConfig options = new QrConfig.Builder().create();
        QrManager.getInstance().init(options).startScan(new QrManager.OnScanResultCallback() {
            @Override
            public void onScanSuccess(String result) {
                try {

                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                    Toast.makeText(mContext, "二维码解析失败", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Intent intent = new Intent(mContext, ScanActivity.class);
        intent.putExtra(QrConfig.EXTRA_THIS_CONFIG, options);
        startActivity(intent);
    }

    @Override
    public void showDialog() {
        if (dialog != null && !dialog.isShowing()) {
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
        Toast.makeText(mContext, err, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getCompanyUserSuccess(WorkersResult result) {

    }

    @Override
    public void deleteCompanyUserSuccess() {

    }

    @Override
    public void updateCompanyUserSuccess() {

    }

    @Override
    public void addCompanyUserSuccess() {
        i--;
        if (i == 0) {
            error("添加成功");
            EventBus.getDefault().post("");
            finish();
        }
    }
}
