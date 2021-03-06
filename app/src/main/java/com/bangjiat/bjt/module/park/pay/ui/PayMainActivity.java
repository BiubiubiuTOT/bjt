package com.bangjiat.bjt.module.park.pay.ui;

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
import com.bangjiat.bjt.common.ReplaceViewHelper;
import com.bangjiat.bjt.module.main.ui.activity.BaseToolBarActivity;
import com.bangjiat.bjt.module.park.pay.adapter.PayAdapter;
import com.bangjiat.bjt.module.park.pay.beans.ParkPayHistory;
import com.bangjiat.bjt.module.park.pay.beans.ParkingDetail;
import com.bangjiat.bjt.module.park.pay.beans.PayListResult;
import com.bangjiat.bjt.module.park.pay.contract.PayContract;
import com.bangjiat.bjt.module.park.pay.presenter.PayPresenter;
import com.dou361.dialogui.DialogUIUtils;
import com.githang.statusbar.StatusBarCompat;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;

public class PayMainActivity extends BaseToolBarActivity implements PayContract.View {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private Dialog dialog;
    private PayContract.Presenter presenter;
    List<PayListResult> beans;
    private ReplaceViewHelper mReplaceViewHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.white));

        initData();
    }

    private void initData() {
        presenter = new PayPresenter(this);
        presenter.getPayList(DataUtil.getToken(mContext));
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setHasFixedSize(true);
        mReplaceViewHelper = new ReplaceViewHelper(this);
    }

    private void setAdapter(final List<PayListResult> beans) {
        PayAdapter mAdapter = new PayAdapter(beans, mContext);
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new PayAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(mContext, PayActivity.class);
                intent.putExtra("data", beans.get(position));
                startActivity(intent);
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
    public void getPayListSuccess(List<PayListResult> str) {
        if (str != null && str.size() > 0) {
            beans = str;
            setAdapter(beans);
            mReplaceViewHelper.removeView();
            return;
        }
        mReplaceViewHelper.toReplaceView(recyclerView, R.layout.no_data_page);
    }

    @Override
    public void paySuccess(String str) {

    }

    @Override
    public void addPayInfoSuccess(String err) {

    }

    @Override
    public void getParkingDetailSuccess(ParkingDetail detail) {

    }

    @Override
    public void fail(String err) {
        Logger.e(err);
        Toast.makeText(mContext, err, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void payBillSuccess(String string) {

    }

    @Override
    public void getParkPayHistorySuccess(ParkPayHistory history) {

    }
}
