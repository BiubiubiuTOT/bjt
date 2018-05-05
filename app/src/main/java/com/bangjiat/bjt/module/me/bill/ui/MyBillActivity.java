package com.bangjiat.bjt.module.me.bill.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.common.TimeUtils;
import com.bangjiat.bjt.module.main.ui.activity.BaseToolBarActivity;
import com.bangjiat.bjt.module.me.bill.beans.PageBillBean;
import com.bangjiat.bjt.module.me.bill.contract.QueryBillContract;
import com.bangjiat.bjt.module.me.bill.presenter.QueryBillPresenter;
import com.dou361.dialogui.DialogUIUtils;
import com.orhanobut.logger.Logger;
import com.squareup.timessquare.CalendarPickerView;

import java.util.Calendar;
import java.util.Date;

import butterknife.OnClick;

public class MyBillActivity extends BaseToolBarActivity implements QueryBillContract.View {
    private Dialog dialog;
    private QueryBillContract.Presenter presenter;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        presenter = new QueryBillPresenter(this);
        presenter.getPageBill(DataUtil.getToken(mContext), 1, 10);
        initDia();
    }

    @OnClick(R.id.ll_wuye)
    public void clickWuye(View view) {
        startActivity(new Intent(mContext, PayBillActivity.class));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_my_bill;
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");

        TextView tv_title = toolbar.findViewById(R.id.toolbar_title);
        TextView tv_query = toolbar.findViewById(R.id.toolbar_other);

        tv_title.setText("我的账单");
        tv_query.setText("查询");

        toolbar.setNavigationIcon(R.mipmap.back_white);
        tv_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!alertDialog.isShowing()) {
                    alertDialog.show();
                }
            }
        });
    }

    private void initDia() {
        Calendar lastYear = Calendar.getInstance();
        lastYear.add(Calendar.YEAR, -1);
        Date today = new Date();
        Calendar to = Calendar.getInstance();
        to.add(Calendar.DAY_OF_MONTH, 1);

        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_time, null);
        final TextView tv_start = view.findViewById(R.id.tv_start_time);
        final TextView tv_end = view.findViewById(R.id.tv_end_time);
        final CalendarPickerView calendar = view.findViewById(R.id.calendarView);

        calendar.init(lastYear.getTime(), to.getTime()).inMode(CalendarPickerView.SelectionMode.RANGE);
        calendar.scrollToDate(today);
        calendar.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(final Date date) {
                final int size = calendar.getSelectedDates().size();
                String formatDate = TimeUtils.formatDate(date);
                Logger.d("size: " + size + " onDateSelected = " + formatDate);

                if (size > 1) {
                    tv_end.setText(formatDate);
                } else {
                    tv_start.setText(formatDate);
                    tv_end.setText("");
                }
            }

            @Override
            public void onDateUnselected(Date date) {
                Logger.d("onDateUnselected = " + TimeUtils.formatDate(date));
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        alertDialog = builder.setView(view)
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", null).create();
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
    public void getPageBillSuccess(PageBillBean billBean) {

    }

    @Override
    public void getPageBillFail(String err) {
        Toast.makeText(mContext, err, Toast.LENGTH_SHORT).show();
    }
}
