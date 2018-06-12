package com.bangjiat.bjt.module.secretary.door.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.ClearEditText;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.common.TimeUtils;
import com.bangjiat.bjt.module.main.ui.activity.BaseColorToolBarActivity;
import com.bangjiat.bjt.module.me.personaldata.beans.BuildUser;
import com.bangjiat.bjt.module.secretary.door.adapter.DoorApplyUserAdapter;
import com.bangjiat.bjt.module.secretary.door.beans.ApplyHistoryBean;
import com.bangjiat.bjt.module.secretary.door.beans.DoorApplyDetailResult;
import com.bangjiat.bjt.module.secretary.door.contract.DoorApplyDetailContract;
import com.bangjiat.bjt.module.secretary.door.presenter.DoorApplyDetailPresenter;
import com.bangjiat.bjt.module.secretary.service.beans.ApprovalDoorInput;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.dou361.dialogui.DialogUIUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 申请详情
 */
public class DetailActivity extends BaseColorToolBarActivity implements DoorApplyDetailContract.View {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.tv_company_name)
    TextView tv_company_name;
    @BindView(R.id.rl_btn)
    RelativeLayout rl_btn;
    @BindView(R.id.card_time)
    CardView card_time;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_reason)
    TextView tv_reason;
    @BindView(R.id.ll_reason)
    LinearLayout ll_reason;

    private List<DoorApplyDetailResult> list;
    private Dialog dialog;
    private DoorApplyDetailContract.Presenter presenter;
    private String token;
    private TimePickerView pvTime;
    private int type;
    private AlertDialog alertDialog;
    private BuildUser first;
    ApplyHistoryBean.RecordsBean recordsBean;
    private int status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        presenter = new DoorApplyDetailPresenter(this);
        Intent intent = getIntent();
        recordsBean = (ApplyHistoryBean.RecordsBean) intent.getSerializableExtra("data");
        status = recordsBean.getType();
        String detail = recordsBean.getDetail();
        if (detail != null) {
            list = new Gson().fromJson(detail, new TypeToken<List<DoorApplyDetailResult>>() {
            }.getType());
            if (list != null && list.size() > 0)
                setAdapter();
        }
        token = DataUtil.getToken(mContext);
        first = BuildUser.first(BuildUser.class);

        tv_company_name.setText(recordsBean.getCompanyName());

        if (status == 2) {
            card_time.setVisibility(View.VISIBLE);
            tv_time.setText(TimeUtils.changeToYMD(recordsBean.getEndTime()));
        } else if (status == 3) {
            ll_reason.setVisibility(View.VISIBLE);
            tv_reason.setText(recordsBean.getOpinion());
        }

        if (Constants.isBuildingAdmin()) {
            if (status == 1)
                rl_btn.setVisibility(View.VISIBLE);

            Calendar startDate = Calendar.getInstance();
            Calendar endDate = Calendar.getInstance();
            endDate.set(2019, 11, 30);

            pvTime = new TimePickerBuilder(mContext, new OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date, View v) {
                    long time = date.getTime();
                    ApprovalDoorInput input = new ApprovalDoorInput(recordsBean.getGuardMainId(), time, type);
                    presenter.approvalDoor(token, first.getBuildId(), input);
                }
            }).setSubmitColor(Color.BLACK)//确定按钮文字颜色
                    .setCancelColor(Color.BLACK)//取消按钮文字颜色
                    .setDate(startDate)// 如果不设置的话，默认是系统时间*/
                    .setRangDate(startDate, endDate)//起始终止年月日设定
                    .build();

            initDia();
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

    }

    @Override
    public void error(String err) {
        Logger.e(err);
        Constants.showErrorDialog(mContext, err);
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
    public void approvalDoorSuccess() {
        new com.adorkable.iosdialog.AlertDialog(mContext).builder().setMsg("审批成功").setCancelable(false).
                setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setResult(RESULT_OK);
                        finish();
                    }
                }).show();
    }

    @Override
    public void getAdminDetailSuccess(List<DoorApplyDetailResult> results) {
        if (results != null) {
            list = results;
            Logger.d(results.toString());
            setAdapter();
        }
    }

    @OnClick(R.id.btn_agree)
    public void clickAgree(View view) {
        type = 2;
        pvTime.show();
    }

    @OnClick(R.id.btn_refuse)
    public void clickRefuse(View view) {
        type = 1;
        alertDialog.show();
    }

    private void initDia() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        View view = LayoutInflater.from(mContext).inflate(R.layout.refluse_message_layout, null);
        Button btn_cancel = view.findViewById(R.id.btn_cancel);
        Button btn_submit = view.findViewById(R.id.btn_submit);
        final ClearEditText et_msg = view.findViewById(R.id.et_msg);


        builder.setCancelable(false)
                .setView(view);
        alertDialog = builder.create();

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (alertDialog.isShowing())
                    alertDialog.dismiss();
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string = et_msg.getText().toString();
                if (string.isEmpty()) {
                    error("请填写审批信息");
                    return;
                }
                if (alertDialog.isShowing())
                    alertDialog.dismiss();

                ApprovalDoorInput input = new ApprovalDoorInput(recordsBean.getGuardMainId(), string, type);
                presenter.approvalDoor(token, first.getBuildId(), input);
            }
        });
    }
}
