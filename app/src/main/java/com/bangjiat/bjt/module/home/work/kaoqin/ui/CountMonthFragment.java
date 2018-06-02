package com.bangjiat.bjt.module.home.work.kaoqin.ui;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.BaseFragment;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.common.TimeUtils;
import com.bangjiat.bjt.module.home.work.kaoqin.adapter.CountAdapter;
import com.bangjiat.bjt.module.home.work.kaoqin.beans.CountBean;
import com.bangjiat.bjt.module.home.work.kaoqin.beans.DakaHistoryResult;
import com.bangjiat.bjt.module.home.work.kaoqin.beans.DakaTotalResult;
import com.bangjiat.bjt.module.home.work.kaoqin.beans.RuleInput;
import com.bangjiat.bjt.module.home.work.kaoqin.contract.ClockContract;
import com.bangjiat.bjt.module.home.work.kaoqin.presenter.ClockPresenter;
import com.bangjiat.bjt.module.secretary.workers.beans.WorkersResult;
import com.bangjiat.bjt.module.secretary.workers.contract.CompanyUserContract;
import com.bangjiat.bjt.module.secretary.workers.presenter.CompanyUserPresenter;
import com.dou361.dialogui.DialogUIUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;


public class CountMonthFragment extends BaseFragment implements ClockContract.View, CompanyUserContract.View {
    @BindView(R.id.recycler_view_late)
    RecyclerView recycler_view_late;
    @BindView(R.id.recycler_view_leave)
    RecyclerView recycler_view_leave;
    @BindView(R.id.recycler_view_absence)
    RecyclerView recycler_view_absence;
    @BindView(R.id.recycler_view_field)
    RecyclerView recycler_view_field;
    @BindView(R.id.iv1)
    ImageView iv1;
    @BindView(R.id.iv2)
    ImageView iv2;
    @BindView(R.id.iv3)
    ImageView iv3;
    @BindView(R.id.iv4)
    ImageView iv4;
    @BindView(R.id.tv_late)
    TextView tv_late;
    @BindView(R.id.tv_leave)
    TextView tv_leave;
    @BindView(R.id.tv_absence)
    TextView tv_absence;
    @BindView(R.id.tv_field)
    TextView tv_field;

    private Dialog dialog;
    private ClockContract.Presenter presenter;
    private CompanyUserContract.Presenter userPresenter;
    private String token;
    private List<WorkersResult.RecordsBean> companyUsers;


    @Override
    protected void initView() {
        recycler_view_late.setLayoutManager(new LinearLayoutManager(mContext));
        recycler_view_leave.setLayoutManager(new LinearLayoutManager(mContext));
        recycler_view_absence.setLayoutManager(new LinearLayoutManager(mContext));
        recycler_view_field.setLayoutManager(new LinearLayoutManager(mContext));
        recycler_view_absence.setHasFixedSize(true);
        recycler_view_field.setHasFixedSize(true);
        recycler_view_leave.setHasFixedSize(true);
        recycler_view_late.setHasFixedSize(true);


        presenter = new ClockPresenter(this);
        token = DataUtil.getToken(mContext);
        userPresenter = new CompanyUserPresenter(this);
        userPresenter.getCompanyUser(token, 1, 100, 1);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_count_month;
    }

    @OnClick(R.id.rl_1)
    public void clickRl1(View view) {
        down();
        int visibility = recycler_view_late.getVisibility();
        recycler_view_late.setVisibility(visibility == View.GONE ? View.VISIBLE : View.GONE);
        iv1.setImageResource(visibility == View.GONE ? R.mipmap.up : R.mipmap.down);

        recycler_view_leave.setVisibility(View.GONE);
        recycler_view_absence.setVisibility(View.GONE);
        recycler_view_field.setVisibility(View.GONE);
    }

    @OnClick(R.id.iv_excel)
    public void clickExcel(View view) {
        startActivity(new Intent(mContext, ExcelActivity.class));
    }

    @OnClick(R.id.rl_2)
    public void clickRl2(View view) {
        down();
        int visibility = recycler_view_leave.getVisibility();
        recycler_view_leave.setVisibility(visibility == View.GONE ? View.VISIBLE : View.GONE);
        iv2.setImageResource(visibility == View.GONE ? R.mipmap.up : R.mipmap.down);

        recycler_view_late.setVisibility(View.GONE);
        recycler_view_absence.setVisibility(View.GONE);
        recycler_view_field.setVisibility(View.GONE);
    }

    private void down() {
        iv1.setImageResource(R.mipmap.down);
        iv2.setImageResource(R.mipmap.down);
        iv3.setImageResource(R.mipmap.down);
        iv4.setImageResource(R.mipmap.down);
    }

    @OnClick(R.id.rl_3)
    public void clickRl3(View view) {
        down();
        int visibility = recycler_view_absence.getVisibility();
        recycler_view_absence.setVisibility(visibility == View.GONE ? View.VISIBLE : View.GONE);
        iv3.setImageResource(visibility == View.GONE ? R.mipmap.up : R.mipmap.down);

        recycler_view_leave.setVisibility(View.GONE);
        recycler_view_late.setVisibility(View.GONE);
        recycler_view_field.setVisibility(View.GONE);
    }

    @OnClick(R.id.rl_4)
    public void clickRl4(View view) {
        down();
        int visibility = recycler_view_field.getVisibility();
        recycler_view_field.setVisibility(visibility == View.GONE ? View.VISIBLE : View.GONE);
        iv4.setImageResource(visibility == View.GONE ? R.mipmap.up : R.mipmap.down);

        recycler_view_leave.setVisibility(View.GONE);
        recycler_view_absence.setVisibility(View.GONE);
        recycler_view_late.setVisibility(View.GONE);
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
    public void getCompanyUserSuccess(WorkersResult result) {
        if (result != null) {
            List<WorkersResult.RecordsBean> records = result.getRecords();
            if (records != null) {
                this.companyUsers = records;
                presenter.getClockTotal(token, TimeUtils.getBeginOfMonth(), System.currentTimeMillis());
            }
        }
    }

    @Override
    public void deleteCompanyUserSuccess() {

    }

    @Override
    public void updateCompanyUserSuccess() {

    }

    @Override
    public void addCompanyUserSuccess() {

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
        if (result != null) {
            RuleInput rule = result.getCompanyClockRule();
            List<DakaHistoryResult> results = result.getCompanyClockList();

            if (results != null && results.size() > 0) {
                Logger.d(results.toString());
                List<DakaHistoryResult> lates = new ArrayList<>();//迟到
                List<DakaHistoryResult> leaves = new ArrayList<>();//早退
                List<DakaHistoryResult> fields = new ArrayList<>();//外勤
                Map<String, CountBean> mapAbsence = new HashMap<>();

                for (DakaHistoryResult r : results) {
                    int inType = r.getInType();
                    int outType = r.getOutType();

                    if (inType == 2) {
                        lates.add(r);
                    } else if (inType == 3) {
                        fields.add(r);
                    }

                    if (outType == 2) {
                        leaves.add(r);
                    } else if (outType == 3) {
                        fields.add(r);
                    }
                    String userId = r.getUserId();
                    if (!mapAbsence.containsKey(userId)) {
                        mapAbsence.put(userId, new CountBean
                                (r.getUserRealname(), 1, 0));
                    } else {
                        CountBean countBean = mapAbsence.get(userId);
                        int counts = countBean.getCounts();
                        countBean.setCounts(counts + 1);
                    }
                }
                Map<String, CountBean> mapLate = new HashMap<>();
                Map<String, CountBean> mapLeave = new HashMap<>();
                Map<String, CountBean> mapFiled = new HashMap<>();

                for (DakaHistoryResult r : lates) {
                    String userId = r.getUserId();
                    int minutes = TimeUtils.getMinutes(rule.getInTime(), r.getInTime());
                    if (!mapLate.containsKey(userId)) {
                        mapLate.put(userId, new CountBean
                                (r.getUserRealname(), 1, minutes));
                    } else {
                        CountBean countBean = mapLate.get(userId);
                        int time = countBean.getTime();
                        int counts = countBean.getCounts();
                        countBean.setCounts(counts + 1);
                        countBean.setTime(time + minutes);

                    }
                }

                for (DakaHistoryResult r : leaves) {
                    String userId = r.getUserId();
                    int minutes = TimeUtils.getMinutes(r.getOutTime(), rule.getOutTime());
                    if (!mapLeave.containsKey(userId)) {
                        mapLeave.put(userId, new CountBean
                                (r.getUserRealname(), 1, minutes));
                    } else {
                        CountBean countBean = mapLeave.get(userId);
                        int time = countBean.getTime();
                        int counts = countBean.getCounts();
                        countBean.setCounts(counts + 1);
                        countBean.setTime(time + minutes);

                    }
                }

                for (DakaHistoryResult r : fields) {
                    String userId = r.getUserId();
                    int minutes = TimeUtils.getMinutes(rule.getInTime(), r.getInTime());
                    if (!mapFiled.containsKey(userId)) {
                        mapFiled.put(userId, new CountBean
                                (r.getUserRealname(), 1, minutes));
                    } else {
                        CountBean countBean = mapFiled.get(userId);
                        int time = countBean.getTime();
                        int counts = countBean.getCounts();
                        countBean.setCounts(counts + 1);
                        countBean.setTime(time + minutes);

                    }
                }

                List<CountBean> late = new ArrayList<>();
                List<CountBean> leave = new ArrayList<>();
                List<CountBean> field = new ArrayList<>();
                List<CountBean> absence = new ArrayList<>();

                Set<String> stringsLate = mapLate.keySet();
                for (String s : stringsLate) {
                    late.add(mapLate.get(s));
                }
                Set<String> stringsLeave = mapLeave.keySet();
                for (String s : stringsLeave) {
                    leave.add(mapLeave.get(s));
                }
                Set<String> stringsField = mapFiled.keySet();
                for (String s : stringsField) {
                    field.add(mapLate.get(s));
                }

                String[] workDays = rule.getWorkDay().split(",");
                List<String> list = Arrays.asList(workDays);
                int dayOfMonth = TimeUtils.getDayOfMonth();
                int reset = 0;
                for (int i = 1; i <= dayOfMonth; i++) {
                    Calendar calendar1 = Calendar.getInstance();
                    calendar1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), i, 0, 0, 0);
                    int i1 = calendar1.get(Calendar.DAY_OF_WEEK);
                    int i2 = i1 - 1;
                    if (i2 == 0) i2 = 7;
                    String s = String.valueOf(i2);
                    if (!list.contains(s)) {
                        reset++;
                    }
                }
                Set<String> stringsAbsence = mapAbsence.keySet();
                List<String> list1 = new ArrayList<>();
                for (String s : stringsAbsence) {
                    CountBean e = mapAbsence.get(s);
                    int counts = e.getCounts();
                    int counts1 = dayOfMonth - reset - counts;
                    e.setCounts(counts1);
                    if (counts1 != 0) {
                        absence.add(e);
                    }

                    list1.add(s);
                }

                for (WorkersResult.RecordsBean bean : companyUsers) {
                    if (!list1.contains(bean.getUserId())) {
                        absence.add(new CountBean(bean.getRealname(), dayOfMonth, 0));
                    }
                }

                Logger.d("late: " + late.toString());
                Logger.d("leave: " + leave.toString());
                Logger.d("field: " + field.toString());
                Logger.d("absence: " + absence.toString());

                tv_late.setText(getString(R.string.person, late.size()));
                tv_absence.setText(getString(R.string.person, absence.size()));
                tv_leave.setText(getString(R.string.person, leave.size()));
                tv_field.setText(getString(R.string.person, field.size()));

                recycler_view_late.setAdapter(new CountAdapter(late, mContext));
                recycler_view_leave.setAdapter(new CountAdapter(leave, mContext));
                recycler_view_absence.setAdapter(new CountAdapter(absence, mContext));
                recycler_view_field.setAdapter(new CountAdapter(field, mContext));
            }
        }
    }

    @Override
    public void getUserClockTotalSuccess(DakaTotalResult result) {

    }
}

