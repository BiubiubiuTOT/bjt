package com.bangjiat.bjt.module.home.work.kaoqin.ui;

import android.app.Dialog;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.BaseFragment;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.common.TimeUtils;
import com.bangjiat.bjt.module.home.work.kaoqin.beans.DakaHistoryResult;
import com.bangjiat.bjt.module.home.work.kaoqin.beans.DakaTotalResult;
import com.bangjiat.bjt.module.home.work.kaoqin.beans.RuleInput;
import com.bangjiat.bjt.module.home.work.kaoqin.contract.ClockContract;
import com.bangjiat.bjt.module.home.work.kaoqin.presenter.ClockPresenter;
import com.dou361.dialogui.DialogUIUtils;
import com.orhanobut.logger.Logger;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;


public class CountMyFragment extends BaseFragment implements ClockContract.View {
    @BindView(R.id.tv_work)
    TextView tv_work;
    @BindView(R.id.tv_late)
    TextView tv_late;
    @BindView(R.id.tv_leave)
    TextView tv_leave;
    @BindView(R.id.tv_absence)
    TextView tv_absence;
    @BindView(R.id.tv_field)
    TextView tv_field;
    @BindView(R.id.tv_reset)
    TextView tv_reset;

    private Dialog dialog;
    private ClockContract.Presenter presenter;
    private long startTime;
    private long endTime;
    private int late;
    private int leave;
    private int absence;//旷工
    private int field;//外勤
    private int reset;//休息
    private int work;//上班天数

    @Override
    protected void initView() {
        presenter = new ClockPresenter(this);
        presenter.getUserClockTotal(DataUtil.getToken(mContext), TimeUtils.getBeginOfMonth(),
                System.currentTimeMillis(), DataUtil.getUserId(mContext));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_count_my;
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
        Constants.showErrorDialog(mContext, err);
    }

    @Override
    public void getAllClockListSuccess(List<DakaHistoryResult> results) {

    }

    @Override
    public void getClockListSuccess(List<DakaHistoryResult> results) {

    }

    @Override
    public void getUserClockListSuccess(List<DakaHistoryResult> results) {

    }

    @Override
    public void getClockTotalSuccess(DakaTotalResult result) {

    }

    @Override
    public void getUserClockTotalSuccess(DakaTotalResult result) {
        if (result != null) {
            RuleInput companyClockRule = result.getCompanyClockRule();
            String workDay = companyClockRule.getWorkDay();
            String[] workDays = workDay.split(",");
            List<String> list = Arrays.asList(workDays);
            List<DakaHistoryResult> companyClockList = result.getCompanyClockList();
            Logger.d(companyClockRule.toString());
            Logger.d(companyClockList.size());
            int dayOfMonth = TimeUtils.getDayOfMonth();
            Logger.d("dayOfMonth: " + dayOfMonth);
            Logger.d("dayOfWeek: " + Constants.dayOfWeek());

            work = companyClockList.size();
            absence = dayOfMonth - work;

            for (DakaHistoryResult b : companyClockList) {
                int inType = b.getInType();
                int outType = b.getOutType();
                if (inType == 2) {//1、正常；2、迟到；3、外勤打卡
                    late++;
                } else if (inType == 3) {
                    field++;
                }
                if (outType == 2) {//1、正常；2、迟到；3、外勤打卡
                    leave++;
                } else if (outType == 3) {
                    field++;
                }
            }

            for (int i = 1; i <= dayOfMonth; i++) {
                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), i, 0, 0, 0);
                int i1 = calendar1.get(Calendar.DAY_OF_WEEK);
                Logger.d("dayOfWeek: " + i1);
                int i2 = i1 - 1;
                if (i2 == 0) i2 = 7;
                String s = String.valueOf(i2);
                if (!list.contains(s)) {
                    reset++;
                }
            }

            tv_work.setText(getString(R.string.day, work));
            tv_reset.setText(getString(R.string.day, reset));
            tv_absence.setText(getString(R.string.count, absence));
            tv_field.setText(getString(R.string.count, field));
            tv_late.setText(getString(R.string.count, late));
            tv_leave.setText(getString(R.string.count, leave));
        }
    }
}

