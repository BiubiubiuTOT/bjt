package com.bangjiat.bjt.module.park.pay.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.module.main.ui.activity.BaseToolBarActivity;
import com.bangjiat.bjt.module.park.pay.adapter.PayAdapter;
import com.bangjiat.bjt.module.park.pay.beans.PayListResult;
import com.bangjiat.bjt.module.park.pay.contract.PayContract;
import com.bangjiat.bjt.module.park.pay.presenter.PayPresenter;
import com.dou361.dialogui.DialogUIUtils;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;

public class PayMainActivity extends BaseToolBarActivity implements PayContract.View {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private Dialog dialog;
    private PayContract.Presenter presenter;
    List<PayListResult.DataBean> beans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }

    private void initData() {
        presenter = new PayPresenter(this);
        presenter.getPayList(DataUtil.getToken(mContext));
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setHasFixedSize(true);
    }

    private void setAdapter(List<PayListResult.DataBean> beans) {
        PayAdapter mAdapter = new PayAdapter(beans, mContext);
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new PayAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(mContext, PayActivity.class));
            }
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_pay_main;
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        toolbar.setNavigationIcon(R.mipmap.back_black);
        TextView tv_title = toolbar.findViewById(R.id.toolbar_title);
        tv_title.setText("停车缴费");
        TextView tv_history = toolbar.findViewById(R.id.toolbar_other);
        tv_history.setText("缴费记录");

        tv_title.setTextColor(getResources().getColor(R.color.black));
        tv_history.setVisibility(View.VISIBLE);
        toolbar.setBackgroundColor(getResources().getColor(R.color.white));
        tv_history.setTextColor(getResources().getColor(R.color.black));

        tv_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, PayHistoryActivity.class));
            }
        });
    }

    @Override
    public void showDialog() {
        if (dialog != null) {
            if (!dialog.isShowing())
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
    public void getPayListSuccess(PayListResult str) {
        if (str != null) {
            beans = str.getData();
            if (beans != null)
                setAdapter(beans);
        }
    }

    @Override
    public void paySuccess(String str) {

    }

    @Override
    public void addPayInfoSuccess(String err) {

    }

    @Override
    public void fail(String err) {
        Logger.e(err);
    }
}
