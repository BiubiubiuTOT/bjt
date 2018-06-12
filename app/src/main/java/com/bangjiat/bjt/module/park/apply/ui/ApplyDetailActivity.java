package com.bangjiat.bjt.module.park.apply.ui;

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

import com.adorkable.iosdialog.AlertDialog;
import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.ClearEditText;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.common.TimeUtils;
import com.bangjiat.bjt.module.main.ui.activity.BaseWhiteToolBarActivity;
import com.bangjiat.bjt.module.park.apply.adapter.CarDetailAdapter;
import com.bangjiat.bjt.module.park.apply.beans.DealParkApplyInput;
import com.bangjiat.bjt.module.park.apply.beans.LotResult;
import com.bangjiat.bjt.module.park.apply.beans.ParkApplyDetail;
import com.bangjiat.bjt.module.park.apply.beans.ParkApplyHistoryResult;
import com.bangjiat.bjt.module.park.apply.beans.ParkingResult;
import com.bangjiat.bjt.module.park.apply.contract.ParkApplyContract;
import com.bangjiat.bjt.module.park.apply.presenter.ParkApplyPresenter;
import com.bangjiat.bjt.module.park.car.beans.CarBean;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.dou361.dialogui.DialogUIUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ApplyDetailActivity extends BaseWhiteToolBarActivity implements ParkApplyContract.View {
    private static final int SELECT = 2;
    @BindView(R.id.recycler_view_car)
    RecyclerView recyclerViewCar;
    @BindView(R.id.tv_company_name)
    TextView tv_company_name;
    @BindView(R.id.rl_btn)
    RelativeLayout rl_btn;
    @BindView(R.id.ll_reason)
    LinearLayout ll_reason;
    @BindView(R.id.tv_reason)
    TextView tv_reason;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.card_time)
    CardView card_time;
    @BindView(R.id.tv_start_time)
    TextView tv_start_time;

    ParkApplyHistoryResult.RecordsBean bean;
    private List<ParkApplyDetail> details;
    int applyId;
    int type;
    private CarDetailAdapter mAdapter;
    private Dialog dialog;
    private ParkApplyContract.Presenter presenter;
    private ParkApplyDetail detail;
    private android.app.AlertDialog alertDialog;
    private String remark;
    private long terminalTime;
    private TimePickerView pvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        presenter = new ParkApplyPresenter(this);
        recyclerViewCar.setHasFixedSize(true);
        recyclerViewCar.setLayoutManager(new LinearLayoutManager(mContext));
        bean = (ParkApplyHistoryResult.RecordsBean) getIntent().getSerializableExtra("data");
        if (bean != null) {
            applyId = bean.getApplyId();
            tv_company_name.setText(bean.getCompany());
            details = new Gson().fromJson(bean.getDetail(), new TypeToken<List<ParkApplyDetail>>() {
            }.getType());

            if (details != null && details.size() > 0) {
                setAdapter();
            }
            int status = bean.getStatus();
            if (Constants.isParkAdmin() && status == 1) {
                rl_btn.setVisibility(View.VISIBLE);
            }
            if (status != 3) {
                card_time.setVisibility(View.VISIBLE);
            }
            if (status == 3) {
                ll_reason.setVisibility(View.VISIBLE);
                tv_reason.setText(bean.getOpinion());
            }
            if (status == 2) {
                tv_time.setText(TimeUtils.changeToYMD(bean.getTerminalTime()));
            }
        }
        tv_start_time.setText(TimeUtils.getTime());

        initDia();
        initDate();
    }

    private void initDate() {
        Calendar startDate = Calendar.getInstance();
        startDate.set(startDate.get(Calendar.YEAR), startDate.get(Calendar.MONTH) + 1, startDate.get(Calendar.DAY_OF_MONTH));
        Calendar endDate = Calendar.getInstance();
        endDate.set(2030, 0, 1);

        pvTime = new TimePickerBuilder(mContext, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                long time = date.getTime();
                terminalTime = time;
                tv_time.setText(TimeUtils.changeToYMD(time));
            }
        }).setSubmitColor(Color.BLACK)//确定按钮文字颜色
                .setCancelColor(Color.BLACK)//取消按钮文字颜色
                .setDate(startDate)// 如果不设置的话，默认是系统时间*/
                .setRangDate(startDate, endDate)//起始终止年月日设定
                .build();
    }

    private void setAdapter() {
        mAdapter = new CarDetailAdapter(details, mContext, bean.getStatus());
        recyclerViewCar.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new CarDetailAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onLotClick(View view, int position) {
                detail = details.get(position);
                Intent intent = new Intent(mContext, ChooseLotActivity.class);
                intent.putExtra("data", detail.getSpaceId());
                startActivityForResult(intent, SELECT);
            }
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_apply_ditail;
    }

    @Override
    protected String getTitleStr() {
        return "审批详情";
    }

    @OnClick(R.id.btn_refuse)
    public void clickRefuse(View view) {
        type = 2;
        alertDialog.show();
    }

    @OnClick(R.id.tv_time)
    public void clickTime(View view) {
        int status = bean.getStatus();
        if (Constants.isParkAdmin() && status == 1) {
            pvTime.show();
        }
    }

    private void initDia() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(mContext);
        View view = LayoutInflater.from(mContext).inflate(R.layout.refluse_message, null);
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
                remark = et_msg.getText().toString();
                if (remark.isEmpty()) {
                    error("请填写拒绝原因");
                    return;
                }
                if (alertDialog.isShowing())
                    alertDialog.dismiss();

                deal();
            }
        });
    }

    private void deal() {
        DealParkApplyInput input = new DealParkApplyInput();
        input.setApplyId(applyId);
        input.setType(type);

        if (type == 2) {
            input.setOpinion(remark);
        }

        List<DealParkApplyInput.Detail> details = new ArrayList<>();
        List<ParkApplyDetail> lists = mAdapter.getLists();
        for (ParkApplyDetail detail : lists) {
            int detailType = detail.getType();
            if (detailType == 2) {//临时停车
                details.add(new DealParkApplyInput.Detail(detail.getUserId(), detailType, detail.getDetailId()));
            } else {
                details.add(new DealParkApplyInput.Detail(detail.getUserId(), detailType, detail.getLotNumber(), terminalTime, detail.getDetailId()));
            }
        }

        input.setDetailList(details);
        presenter.dealParkApply(DataUtil.getToken(mContext), input);
    }

    @OnClick(R.id.btn_agree)
    public void clickAgree(View view) {
        type = 1;
        if (terminalTime == 0) {
            error("请选择截止日期");
            return;
        }
        deal();
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
    public void getWorkersCarSuccess(List<CarBean> list) {

    }

    @Override
    public void getParkSpaceSuccess(ParkingResult s) {

    }

    @Override
    public void parkApplySuccess() {

    }

    @Override
    public void dealParkApplySuccess() {
        showSuccessExitDialog();
    }

    public void showSuccessExitDialog() {
        new AlertDialog(mContext).builder().setMsg("审批成功").setCancelable(false).
                setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setResult(RESULT_OK);
                        finish();
                    }
                }).show();
    }

    @Override
    public void getParkApplyHistorySuccess(ParkApplyHistoryResult result) {

    }

    @Override
    public void getLotListSuccess(List<LotResult> results) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == SELECT) {
            LotResult result = (LotResult) data.getSerializableExtra("data");
            detail.setLotNumber(result.getNumber());

            mAdapter.setLists(details);
        }
    }
}
