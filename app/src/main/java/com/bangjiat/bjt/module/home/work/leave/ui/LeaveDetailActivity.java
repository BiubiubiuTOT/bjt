package com.bangjiat.bjt.module.home.work.leave.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adorkable.iosdialog.AlertDialog;
import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.common.TimeUtils;
import com.bangjiat.bjt.module.home.work.leave.adapter.HistoryDetailAdapter;
import com.bangjiat.bjt.module.home.work.leave.beans.CompanyLeaveResult;
import com.bangjiat.bjt.module.home.work.leave.beans.DealLeaveInput;
import com.bangjiat.bjt.module.home.work.leave.beans.Progress;
import com.bangjiat.bjt.module.home.work.leave.contract.LeaveContract;
import com.bangjiat.bjt.module.home.work.leave.presenter.LeavePresenter;
import com.bangjiat.bjt.module.home.work.permission.ui.AdminSettingActivity;
import com.bangjiat.bjt.module.main.ui.activity.BaseWhiteToolBarActivity;
import com.bangjiat.bjt.module.secretary.workers.beans.ApproverBean;
import com.bangjiat.bjt.module.secretary.workers.beans.WorkersResult;
import com.dou361.dialogui.DialogUIUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.bangjiat.bjt.module.secretary.service.ui.NewApplyActivity.GET_PERSON;

public class LeaveDetailActivity extends BaseWhiteToolBarActivity implements LeaveContract.View {
    private CompanyLeaveResult.RecordsBean bean;
    @BindView(R.id.tv_type)
    TextView tv_type;
    @BindView(R.id.tv_start_time)
    TextView tv_start_time;
    @BindView(R.id.tv_end_time)
    TextView tv_end_time;
    @BindView(R.id.tv_reason)
    TextView tv_reason;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    @BindView(R.id.rl_btn)
    RelativeLayout rl_btn;
    private Dialog dialog;
    private int type;
    private WorkersResult.RecordsBean result;
    private Progress progress;
    private LeaveContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        presenter = new LeavePresenter(this);
        recycler_view.setLayoutManager(new LinearLayoutManager(mContext));
        recycler_view.setHasFixedSize(true);
        bean = (CompanyLeaveResult.RecordsBean) getIntent().getSerializableExtra("data");
        if (bean != null) {
            String leaveTypeStr = Constants.getLeaveTypeStr(mContext, bean.getType());
            tv_type.setText("请假类型：" + leaveTypeStr);
            tv_start_time.setText("开始时间：" + TimeUtils.changeToTime(bean.getBeginTime()));
            tv_end_time.setText("结束时间：" + TimeUtils.changeToTime(bean.getEndTime()));
            tv_reason.setText("请假事由：" + bean.getReason());

            String approver = bean.getApprover();
            List<ApproverBean> list = new Gson().fromJson(approver, new TypeToken<List<ApproverBean>>() {
            }.getType());
            list.add(0, new ApproverBean(bean.getBeginTime(), 4, bean.getApplyer()));
            HistoryDetailAdapter mAdapter = new HistoryDetailAdapter(list, mContext);
            recycler_view.setAdapter(mAdapter);
            Logger.d(list.toString());

            if (Constants.isCompanyAdmin() || Constants.isWorkAdmin()) {//公司管理员或者工作台管理员可以审批请假信息
                ApproverBean bean = list.get(list.size() - 1);
                int status = bean.getStatus();
                if (status != 1 && status != 2) {
                    if (bean.getUserId().equals(DataUtil.getUserId(mContext))) {
                        rl_btn.setVisibility(View.VISIBLE);
                    }
                }
            }

        }
    }

    @OnClick(R.id.btn_agree)
    public void clickAgree(View view) {
        type = 1;
        deal();
    }

    private void deal() {
        DealLeaveInput input = new DealLeaveInput();
        input.setLeaveId(bean.getLeaveId());
        input.setType(type);
        if (progress != null)
            input.setProgress(progress);

        Logger.d(input.toString());
        presenter.dealLeave(DataUtil.getToken(mContext), input);
    }

    @OnClick(R.id.btn_refuse)
    public void clickRefuse(View view) {
        type = 2;
        deal();
    }


    @OnClick(R.id.btn_to)
    public void clickTo(View view) {
        type = 3;
        Intent intent = new Intent(mContext, AdminSettingActivity.class);
        intent.putExtra("type", 1);
        startActivityForResult(intent, GET_PERSON);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_leave_detail;
    }

    @Override
    protected String getTitleStr() {
        return "申请记录";
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
    public void error(String err) {
        Logger.e(err);
        Constants.showErrorDialog(this, err);
    }

    @Override
    public void addLeaveSuccess() {

    }

    @Override
    public void getCompanyLeaveSuccess(CompanyLeaveResult result) {

    }

    @Override
    public void getSelfLeaveSuccess(CompanyLeaveResult result) {

    }

    @Override
    public void dealLeaveSuccess() {
        new AlertDialog(mContext).builder().setMsg("处理成功").setCancelable(false).
                setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setResult(RESULT_OK);
                        finish();
                    }
                }).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == GET_PERSON) {
                result = (WorkersResult.RecordsBean) data.getSerializableExtra("data");
                progress = new Progress(result.getUserId(), result.getRealname(), result.getPhone());
                deal();
            }
        }
    }
}
