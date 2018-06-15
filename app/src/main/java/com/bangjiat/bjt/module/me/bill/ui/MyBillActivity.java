package com.bangjiat.bjt.module.me.bill.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.common.RefreshViewHolder;
import com.bangjiat.bjt.common.ReplaceViewHelper;
import com.bangjiat.bjt.common.TimeUtils;
import com.bangjiat.bjt.module.main.ui.activity.BaseToolBarActivity;
import com.bangjiat.bjt.module.me.bill.adapter.BillAdapter;
import com.bangjiat.bjt.module.me.bill.beans.PageBillBean;
import com.bangjiat.bjt.module.me.bill.contract.QueryBillContract;
import com.bangjiat.bjt.module.me.bill.presenter.QueryBillPresenter;
import com.dou361.dialogui.DialogUIUtils;
import com.orhanobut.logger.Logger;
import com.squareup.timessquare.CalendarPickerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

public class MyBillActivity extends BaseToolBarActivity implements QueryBillContract.View
        , BGARefreshLayout.BGARefreshLayoutDelegate {
    private static final int PAY_SUCCESS = 2;
    private Dialog dialog;
    private QueryBillContract.Presenter presenter;
    private AlertDialog alertDialog;
    private List<PageBillBean.RecordsBean> list;
    private BillAdapter mAdapter;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.rl_refresh)
    BGARefreshLayout mRefreshLayout;
    private String start;
    private String end;
    private ReplaceViewHelper mReplaceViewHelper;
    private int current = 1;
    private int pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        mReplaceViewHelper = new ReplaceViewHelper(this);
        presenter = new QueryBillPresenter(this);
        initDia();
        presenter.getPageBill(DataUtil.getToken(mContext), current, 10);
        setAdapter();
        mRefreshLayout.setDelegate(this);
        mRefreshLayout.setRefreshViewHolder(new RefreshViewHolder(mContext, true));
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
                Logger.d("size: " + size + " onDateSelected = " + formatDate + " time: " + date.getTime());

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
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        start = tv_start.getText().toString();
                        end = tv_end.getText().toString();
                        if (!start.isEmpty() && !end.isEmpty()) {

                            Logger.d("start: " + start + " end: " + end);
                            presenter.getPageBill(DataUtil.getToken(mContext), 1, 10,
                                    TimeUtils.changeToLong(start),
                                    TimeUtils.changeToLong(end));
                        }
                    }
                }).create();
    }

    private void setAdapter() {
        list = new ArrayList<>();
        mAdapter = new BillAdapter(mContext, list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new BillAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                PageBillBean.RecordsBean bean = list.get(position);
                if (bean.getStatus() != 1) {
                    Intent intent = new Intent(mContext, PayBillActivity.class);
                    intent.putExtra("data", bean);
                    startActivityForResult(intent, PAY_SUCCESS);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PAY_SUCCESS) {
            current = 1;
            list = new ArrayList<>();
            presenter.getPageBill(DataUtil.getToken(mContext), current, 10,
                    TimeUtils.changeToLong(start),
                    TimeUtils.changeToLong(end));
        }
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
        mRefreshLayout.endRefreshing();
        mRefreshLayout.endLoadingMore();
        if (billBean != null) {
            pages = billBean.getPages();
            current = billBean.getCurrent();
            List<PageBillBean.RecordsBean> records = billBean.getRecords();
            if (records != null && records.size() > 0) {
                Logger.d(records.toString());

                list.addAll(records);
                if (current > 1) {
                    mAdapter.setLists(list);
                    recyclerView.smoothScrollToPosition(0);
                } else {
                    mAdapter.setLists(list);
                }

                mReplaceViewHelper.removeView();
                return;
            }
        }
        mReplaceViewHelper.toReplaceView(recyclerView, R.layout.no_data_page);
    }

    @Override
    public void getPageBillFail(String err) {
        mRefreshLayout.endRefreshing();
        mRefreshLayout.endLoadingMore();
        Logger.e(err);
        Constants.showErrorDialog(mContext, err);
    }


    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout bgaRefreshLayout) {
        current = 1;
        list = new ArrayList<>();
        bgaRefreshLayout.beginRefreshing();
        presenter.getPageBill(DataUtil.getToken(mContext), current, 10);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout bgaRefreshLayout) {
        if (current < pages) {
            current++;
            presenter.getPageBill(DataUtil.getToken(mContext), current, 10);
            return true;
        } else return false;
    }
}
