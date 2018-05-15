package com.bangjiat.bjt.module.home.work.leave.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.common.TimeUtils;
import com.bangjiat.bjt.module.home.work.leave.beans.CompanyLeaveResult;
import com.bangjiat.bjt.module.home.work.leave.beans.DealLeaveInput;
import com.bangjiat.bjt.module.home.work.leave.contract.LeaveContract;
import com.bangjiat.bjt.module.main.ui.activity.BaseWhiteToolBarActivity;
import com.dou361.dialogui.DialogUIUtils;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnClick;

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
    private Dialog dialog;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        bean = (CompanyLeaveResult.RecordsBean) getIntent().getSerializableExtra("data");
        if (bean != null) {
            String leaveTypeStr = Constants.getLeaveTypeStr(mContext, bean.getType());
            tv_type.setText(leaveTypeStr);
            tv_start_time.setText(TimeUtils.changeToTime(bean.getBeginTime()));
            tv_end_time.setText(TimeUtils.changeToTime(bean.getEndTime()));
            tv_reason.setText(bean.getReason());
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
//        Progress progress=new Progress();
    }

    @OnClick(R.id.btn_refuse)
    public void clickRefuse(View view) {
        type = 2;
        deal();
    }

    @OnClick(R.id.btn_to)
    public void clickTo(View view) {
        type = 3;
        deal();
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
        Constants.showSuccessExitDialog(this, "审批成功");
    }
}
