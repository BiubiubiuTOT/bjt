package com.bangjiat.bjt.module.home.work.kaoqin.ui;

import android.app.Dialog;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.common.ExcelUtil;
import com.bangjiat.bjt.common.TimeUtils;
import com.bangjiat.bjt.module.home.work.kaoqin.beans.CountBean;
import com.bangjiat.bjt.module.home.work.kaoqin.beans.DakaHistoryResult;
import com.bangjiat.bjt.module.home.work.kaoqin.beans.DakaTotalResult;
import com.bangjiat.bjt.module.home.work.kaoqin.beans.RuleInput;
import com.bangjiat.bjt.module.home.work.kaoqin.contract.ClockContract;
import com.bangjiat.bjt.module.home.work.kaoqin.presenter.ClockPresenter;
import com.bangjiat.bjt.module.main.ui.activity.BaseToolBarActivity;
import com.bangjiat.bjt.module.secretary.workers.beans.WorkersResult;
import com.bangjiat.bjt.module.secretary.workers.contract.CompanyUserContract;
import com.bangjiat.bjt.module.secretary.workers.presenter.CompanyUserPresenter;
import com.bin.david.form.core.SmartTable;
import com.bin.david.form.core.TableConfig;
import com.bin.david.form.data.CellInfo;
import com.bin.david.form.data.column.Column;
import com.bin.david.form.data.format.draw.IDrawFormat;
import com.bin.david.form.data.style.FontStyle;
import com.bin.david.form.data.style.LineStyle;
import com.bin.david.form.data.table.ArrayTableData;
import com.bin.david.form.utils.DensityUtils;
import com.dou361.dialogui.DialogUIUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class ExcelActivity extends BaseToolBarActivity implements ClockContract.View, CompanyUserContract.View {
    private ClockContract.Presenter presenter;
    private CompanyUserContract.Presenter userPresenter;
    private String token;
    private List<WorkersResult.RecordsBean> companyUsers;

    @BindView(R.id.table)
    SmartTable<String> table;
    private String[] columns;
    private String[][] rows;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        presenter = new ClockPresenter(this);
        token = DataUtil.getToken(mContext);
        userPresenter = new CompanyUserPresenter(this);
        userPresenter.getCompanyUser(token, 1, 100, 1);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_excel;
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        TextView title = toolbar.findViewById(R.id.toolbar_title);
        ImageView img = toolbar.findViewById(R.id.toolbar_image);
        title.setText("月统计");
        title.setTextColor(getResources().getColor(R.color.black));
        img.setImageResource(R.mipmap.share_1);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    boolean b = ExcelUtil.writeExcel(mContext, columns, rows);
                    if (b) {
                        ExcelUtil.shareFile(mContext);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        toolbar.setNavigationIcon(R.mipmap.back_black);
        toolbar.setBackgroundColor(getResources().getColor(R.color.white));
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
        if (result != null) {
            RuleInput rule = result.getCompanyClockRule();
            List<DakaHistoryResult> results = result.getCompanyClockList();
            int dayOfMonth = TimeUtils.getDayOfMonth();

            if (results != null && results.size() > 0) {
                List<DakaHistoryResult> lates = new ArrayList<>();//迟到
                List<DakaHistoryResult> leaves = new ArrayList<>();//早退
                List<DakaHistoryResult> fields = new ArrayList<>();//外勤
                Map<String, CountBean> mapAbsence = new HashMap<>();//出勤

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


                Logger.d(mapAbsence.toString());
                Logger.d(companyUsers.toString());
                Logger.d(results.toString());

                columns = new String[dayOfMonth + 6];
                columns[0] = "姓名";
                columns[dayOfMonth + 1] = "出勤";
                columns[dayOfMonth + 2] = "旷工";
                columns[dayOfMonth + 3] = "迟到";
                columns[dayOfMonth + 4] = "早退";
                columns[dayOfMonth + 5] = "外勤";
                rows = new String[columns.length][companyUsers.size()];
                String[] workDays = rule.getWorkDay().split(",");
                List<String> list = Arrays.asList(workDays);
                int reset = 0;//休息天数
                Calendar calendar1 = Calendar.getInstance();
                for (int i = 1; i <= dayOfMonth; i++) {
                    calendar1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), i, 0, 0, 0);
                    int i1 = calendar1.get(Calendar.DAY_OF_WEEK);
                    int i2 = i1 - 1;
                    if (i2 == 0) i2 = 7;
                    String s = String.valueOf(i2);
                    if (!list.contains(s)) {
                        reset++;
                    }
                }

                for (int i = 0; i < columns.length; i++) {
                    for (int j = 0; j < companyUsers.size(); j++) {
                        WorkersResult.RecordsBean bean = companyUsers.get(j);
                        if (i == 0) {
                            rows[i][j] = bean.getRealname();
                        } else if (i <= dayOfMonth) {
                            columns[i] = i + "号";

                            for (DakaHistoryResult r : results) {
                                if (r.getUserRealname().equals(bean.getRealname()) &&
                                        TimeUtils.getDay(r.getCtime()) == i) {
                                    int inType = r.getInType();
                                    int outType = r.getOutType();
                                    String des = "";
                                    switch (inType) {
                                        case 2:
                                            des = "迟到";
                                            break;
                                        case 3:
                                            des = "外勤";
                                            break;
                                    }
                                    switch (outType) {
                                        case 2:
                                            des += " 早退";
                                            break;
                                        case 3:
                                            des += " 外勤";
                                            break;
                                    }
                                    if (des.isEmpty()) des = "正常";

                                    rows[i][j] = des;
                                }
                            }
                        } else {
                            if (i == dayOfMonth + 1) {
                                CountBean countBean = mapAbsence.get(bean.getUserId());
                                if (countBean != null) {
                                    int work = countBean.getCounts();
                                    rows[i][j] = String.valueOf(work);
                                } else rows[i][j] = "0";
                            } else if (i == dayOfMonth + 2) {//旷工
                                int work = 0;
                                CountBean countBean = mapAbsence.get(bean.getUserId());
                                if (countBean != null) {
                                    work = countBean.getCounts();
                                }
                                rows[i][j] = String.valueOf(dayOfMonth - reset - work);
                            } else if (i == dayOfMonth + 3) {
                                CountBean countBean1 = mapLate.get(bean.getUserId());
                                if (countBean1 != null)
                                    rows[i][j] = String.valueOf(countBean1.getCounts());
                                else rows[i][j] = "0";
                            } else if (i == dayOfMonth + 4) {
                                CountBean countBean1 = mapLeave.get(bean.getUserId());
                                if (countBean1 != null)
                                    rows[i][j] = String.valueOf(countBean1.getCounts());
                                else rows[i][j] = "0";
                            } else if (i == dayOfMonth + 5) {
                                CountBean countBean1 = mapFiled.get(bean.getUserId());
                                if (countBean1 != null)
                                    rows[i][j] = String.valueOf(countBean1.getCounts());
                                else rows[i][j] = "0";
                            }
                        }
                    }
                }

                FontStyle fontStyle = new FontStyle(this, 10, ContextCompat.getColor(this, R.color.mine_bg));
                table.getConfig().setColumnTitleStyle(fontStyle);
                table.getConfig().setHorizontalPadding(0);

                table.getConfig().setVerticalPadding(0);
                table.getConfig().setContentGridStyle(new LineStyle());

                final ArrayTableData<String> tableData = ArrayTableData.create(TimeUtils.getTime(), columns, rows,
                        new IDrawFormat<String>() {

                            @Override
                            public int measureWidth(Column<String> column, int position, TableConfig config) {
                                return DensityUtils.dp2px(mContext, 50);
                            }

                            @Override
                            public int measureHeight(Column<String> column, int position, TableConfig config) {
                                return DensityUtils.dp2px(mContext, 50);
                            }

                            @Override
                            public void draw(Canvas c, Rect rect, CellInfo<String> cellInfo, TableConfig config) {
                                Paint paint = config.getPaint();
                                paint.setTextSize(20);
                                paint.setStyle(Paint.Style.FILL);
                                Paint.FontMetrics fontMetrics = paint.getFontMetrics();
                                float top = fontMetrics.top;//为基线到字体上边框的距离,即上图中的top
                                float bottom = fontMetrics.bottom;//为基线到字体下边框的距离,即上图中的bottom

                                int baseLineY = (int) (rect.centerY() - top / 2 - bottom / 2);//基线中间点的y轴计

                                String value = cellInfo.value;
                                if (!value.isEmpty()) {
                                    paint.setColor(ContextCompat.getColor(mContext, R.color.black));
                                    c.drawText(value, rect.centerX(), baseLineY, paint);
                                } else {
                                    paint.setColor(ContextCompat.getColor(mContext, R.color.red));
                                    c.drawText("未打卡", rect.centerX(), baseLineY, paint);
                                }
                            }
                        });
                table.setTableData(tableData);
            }
        }

    }

    @Override
    public void getUserClockTotalSuccess(DakaTotalResult result) {

    }
}
