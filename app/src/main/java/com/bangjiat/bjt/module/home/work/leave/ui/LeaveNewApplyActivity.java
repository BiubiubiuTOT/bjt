package com.bangjiat.bjt.module.home.work.leave.ui;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.common.TimeUtils;
import com.bangjiat.bjt.module.home.work.leave.beans.CompanyLeaveResult;
import com.bangjiat.bjt.module.home.work.leave.beans.LeaveBean;
import com.bangjiat.bjt.module.home.work.leave.beans.Progress;
import com.bangjiat.bjt.module.home.work.leave.contract.LeaveContract;
import com.bangjiat.bjt.module.home.work.leave.presenter.LeavePresenter;
import com.bangjiat.bjt.module.home.work.permission.ui.AdminSettingActivity;
import com.bangjiat.bjt.module.main.ui.activity.BaseWhiteToolBarActivity;
import com.bangjiat.bjt.module.secretary.workers.beans.WorkersResult;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.dou361.dialogui.DialogUIUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTouch;

import static com.bangjiat.bjt.module.secretary.service.ui.NewApplyActivity.GET_PERSON;

public class LeaveNewApplyActivity extends BaseWhiteToolBarActivity implements LeaveContract.View {
    private List<String> list;
    private OptionsPickerView<String> pvReasons;
    @BindView(R.id.tv_type)
    TextView tv_type;
    @BindView(R.id.et_start_time)
    EditText et_start_time;
    @BindView(R.id.et_end_time)
    EditText et_end_time;
    @BindView(R.id.tv_person)
    TextView tv_person;
    @BindView(R.id.et_reason)
    EditText et_reason;

    private TimePickerView pvStartTime;
    private TimePickerView pvEndTime;
    private long start;
    private long end;
    private int type = -1;
    private WorkersResult.RecordsBean result;
    private Dialog dialog;
    private LeaveContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        presenter = new LeavePresenter(this);
        list = new ArrayList<>();
        list.add("事假");
        list.add("病假");
        list.add("出差");
        list.add("年假");

        initPicker();
        initStartTimePicker();
        initEndTimePicker();
    }

    @OnTouch(R.id.et_start_time)
    public boolean onTouchStartTime(View v, MotionEvent event) {
        pvStartTime.show();
        return true;
    }

    @OnTouch(R.id.et_end_time)
    public boolean onTouchEndTime(View v, MotionEvent event) {
        pvEndTime.show();
        return true;
    }

    @OnClick(R.id.tv_person)
    public void onClickPerson(View v) {
        Intent intent = new Intent(mContext, AdminSettingActivity.class);
        intent.putExtra("type", 1);
        startActivityForResult(intent, GET_PERSON);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == GET_PERSON) {
                result = (WorkersResult.RecordsBean) data.getSerializableExtra("data");
                tv_person.setText(result.getRealname());
            }
        }
    }

    private void initStartTimePicker() {
        pvStartTime = new TimePickerBuilder(mContext, new OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date, View v) {
                start = date.getTime();
                et_start_time.setText(TimeUtils.changeToYMD(start));
            }
        }).setSubmitColor(Color.BLACK)
                .setCancelColor(Color.BLACK)
                .build();
    }

    private void initEndTimePicker() {
        pvEndTime = new TimePickerBuilder(mContext, new OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date, View v) {
                end = date.getTime();
                et_end_time.setText(TimeUtils.changeToYMD(end));
            }
        }).setSubmitColor(Color.BLACK)
                .setCancelColor(Color.BLACK)
                .build();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_leave_new_apply;
    }

    @Override
    protected String getTitleStr() {
        return "新的申请";
    }

    @OnClick(R.id.tv_type)
    public void clickReason(View view) {
        showDia();
    }

    private void showDia() {
        pvReasons.show();
    }

    @OnClick(R.id.btn_done)
    public void clickDone(View view) {
        if (type == -1) {
            error("请选择请假类型");
            return;
        }
        if (start == 0) {
            error("请选择开始时间");
            return;
        }
        if (end == 0) {
            error("请选择结束时间");
            return;
        }
        String reason = et_reason.getText().toString();
        if (reason.isEmpty()) {
            error("请输入请假事由");
            return;
        }

        if (result == null) {
            error("请选择审批人");
            return;
        }
        LeaveBean leaveBean = new LeaveBean();
        leaveBean.setBeginTime(start);
        leaveBean.setEndTime(end);
        leaveBean.setReason(reason);
        leaveBean.setType(type);
        leaveBean.setProgress(new Progress(result.getUserId(), result.getRealname(), result.getPhone()));

        Logger.d(leaveBean.toString());
        presenter.addLeave(DataUtil.getToken(mContext), leaveBean);
    }

    private void initPicker() {
        pvReasons = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int i, int i1, int i2, View view) {
                type = i + 1;
                tv_type.setText(list.get(i));
            }
        })
                .setSubmitColor(Color.BLACK)
                .setCancelColor(Color.BLACK).build();
        pvReasons.setPicker(list);
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
        Constants.showSuccessExitDialog(this, "申请成功");
    }

    @Override
    public void getCompanyLeaveSuccess(CompanyLeaveResult result) {

    }

    @Override
    public void getSelfLeaveSuccess(CompanyLeaveResult result) {

    }

    @Override
    public void dealLeaveSuccess() {

    }
}
