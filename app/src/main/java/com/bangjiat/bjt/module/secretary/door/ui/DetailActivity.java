package com.bangjiat.bjt.module.secretary.door.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.module.main.ui.activity.BaseColorToolBarActivity;
import com.bangjiat.bjt.module.secretary.door.adapter.DoorApplyUserAdapter;
import com.bangjiat.bjt.module.secretary.door.beans.DoorApplyDetailResult;
import com.bangjiat.bjt.module.secretary.door.contract.DoorApplyDetailContract;
import com.bangjiat.bjt.module.secretary.door.presenter.DoorApplyDetailPresenter;
import com.dou361.dialogui.DialogUIUtils;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;

/**
 * 申请详情
 */
public class DetailActivity extends BaseColorToolBarActivity implements DoorApplyDetailContract.View {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.tv_company_name)
    TextView tv_company_name;
    private List<DoorApplyDetailResult> list;
    private String name;
    private String id;
    private Dialog dialog;
    private DoorApplyDetailContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        presenter = new DoorApplyDetailPresenter(this);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        id = intent.getStringExtra("id");

        if (name != null) {
            tv_company_name.setText(name);
        }
        if (id != null) {
            presenter.getDetail(DataUtil.getToken(mContext), id);
        }
    }


    private void setAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setHasFixedSize(true);
        DoorApplyUserAdapter adapter = new DoorApplyUserAdapter(list, mContext);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_apply_deatil;
    }

    @Override
    protected String getTitleStr() {
        return "申请详情";
    }

    @Override
    public void success(List<DoorApplyDetailResult> results) {
        if (results != null) {
            list = results;
            Logger.d(results.toString());
            setAdapter();
        }
    }

    @Override
    public void error(String err) {
        Logger.e(err);
        Toast.makeText(mContext, err, Toast.LENGTH_SHORT).show();
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
}
