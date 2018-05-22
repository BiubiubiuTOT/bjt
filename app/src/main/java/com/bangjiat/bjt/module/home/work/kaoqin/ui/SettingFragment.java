package com.bangjiat.bjt.module.home.work.kaoqin.ui;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.BaseFragment;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.module.home.work.kaoqin.beans.RuleInput;
import com.bangjiat.bjt.module.home.work.kaoqin.contract.RoleContract;
import com.bangjiat.bjt.module.home.work.kaoqin.presenter.RolePresenter;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.orhanobut.logger.Logger;
import com.orm.SugarRecord;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;


public class SettingFragment extends BaseFragment implements RoleContract.View {
    private static final int SELECT_WORK_DAY = 2;
    private static final int SELECT_WIFI = 3;
    private static final int SELECT_LOCATION = 4;

    @BindView(R.id.tv_work_day)
    TextView tv_work_day;
    @BindView(R.id.tv_start_time)
    TextView tv_start_time;
    @BindView(R.id.tv_end_time)
    TextView tv_end_time;
    @BindView(R.id.tv_wifi)
    TextView tv_wifi;
    @BindView(R.id.tv_location)
    TextView tv_location;
    @BindView(R.id.iv_wifi)
    ImageView iv_wifi;
    @BindView(R.id.iv_wifi_delete)
    ImageView iv_wifi_delete;
    @BindView(R.id.iv_location)
    ImageView iv_location;
    @BindView(R.id.iv_location_delete)
    ImageView iv_location_delete;
    private RoleContract.Presenter presenter;

    private OptionsPickerView<String> pvMonths;
    private List<String> ampm;
    private List<String> hours;
    private List<String> minutes;
    private boolean isStart;
    private RuleInput role;
    private RuleInput input;
    private String token;
    private boolean canEdit;

    @Override
    protected void initView() {
        canEdit = Constants.isCompanyAdmin() || Constants.isWorkAdmin();//公司管理员、工作台管理员可以设置考勤
        token = DataUtil.getToken(mContext);
        presenter = new RolePresenter(this);
        role = RuleInput.first(RuleInput.class);
        initData();
    }

    private void initData() {
        input = new RuleInput();
        ampm = new ArrayList<>();
        ampm.add("");

        hours = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            hours.add(String.valueOf(i));
        }

        minutes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            minutes.add("0" + i);
        }
        for (int i = 10; i < 60; i++) {
            minutes.add(String.valueOf(i));
        }

        initMothPicker();

        if (role != null) {
            set();
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_setting;
    }

    @OnClick(R.id.ll_days)
    public void clickDays(View view) {
        if (canEdit)
            startActivityForResult(new Intent(mContext, WorkDayActivity.class), SELECT_WORK_DAY);
    }

    @OnClick(R.id.ll_time_start)
    public void clickTimeStart(View v) {
        if (canEdit) {
            isStart = true;
            pvMonths.show();
        }
    }

    @OnClick(R.id.ll_time_end)
    public void clickTimeEnd(View view) {
        if (canEdit) {
            isStart = false;
            pvMonths.show();
        }
    }

    @OnClick(R.id.ll_address)
    public void clickAddress(View view) {
        if (canEdit)
            startActivityForResult(new Intent(mContext, LocationActivity.class), SELECT_LOCATION);
    }

    @OnClick(R.id.ll_range)
    public void clickRange(View view) {
        if (canEdit)
            startActivityForResult(new Intent(mContext, AddWifiActivity.class), SELECT_WIFI);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_WORK_DAY) {
                String str = data.getStringExtra("data");
                tv_work_day.setText(str);
                String index = data.getStringExtra("index");
                Logger.d(index);

                if (role != null) {
                    role.setWorkDay(index);
                    updateRole();
                } else {
                    input.setWorkDay(index);
                    saveRole();
                }

            } else if (requestCode == SELECT_WIFI) {
                String str = data.getStringExtra("data");
                setWifi(str);

                if (role != null) {
                    role.setWifiName(str);
                    updateRole();
                } else {
                    input.setWifiName(str);
                    saveRole();
                }

            } else if (requestCode == SELECT_LOCATION) {
                String name = data.getStringExtra("name");
                String weidu = String.valueOf(data.getDoubleExtra("weidu", 0));
                String jingdu = String.valueOf(data.getDoubleExtra("jingdu", 0));

                setAddress(name);

                if (role != null) {
                    role.setLatitude(weidu);
                    role.setLongitude(jingdu);
                    role.setAddress(name);
                    updateRole();
                } else {
                    input.setLatitude(weidu);
                    input.setLongitude(jingdu);
                    input.setAddress(name);
                    saveRole();
                }
            }
        }
    }

    private void updateRole() {
        presenter.updateRole(token, role);
    }

    private void saveRole() {
        presenter.saveRole(token, input);
    }

    private void initMothPicker() {
        pvMonths = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int i, int i1, int i2, View view) {
                String h = hours.get(i1);

                String mi = minutes.get(i2);
                String str = h + ":" + mi;
                if (isStart) {
                    tv_start_time.setText(str);
                    if (role != null) {
                        role.setInTime(str);
                        updateRole();
                    } else {
                        input.setInTime(str);
                        saveRole();
                    }
                } else {
                    tv_end_time.setText(str);
                    if (role != null) {
                        role.setOutTime(str);
                        updateRole();
                    } else {
                        input.setOutTime(str);
                        saveRole();
                    }
                }
            }
        })
                .setSubmitColor(Color.BLACK)
                .setCancelColor(Color.BLACK).build();
        pvMonths.setNPicker(ampm, hours, minutes);
    }


    @Override
    public void showErr(String err) {
        Toast.makeText(mContext, err, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getRoleSuccess(RuleInput result) {
        if (result != null) {
            this.role = result;
            role.save();
            set();
        }
    }

    private void set() {
        String workDay = role.getWorkDay();
        String address = role.getAddress();
        String wifi = role.getWifiName();
        String inTime = role.getInTime();
        String outTime = role.getOutTime();

        if (workDay != null && !workDay.equals("null")) {
            String[] split = workDay.split(",");
            String str = "";
            List<String> list = Arrays.asList(split);
            for (String s : list) {
                str += Constants.WEEK[Integer.parseInt(s) - 1] + ",";
            }
            str = str.substring(0, str.length() - 1);
            tv_work_day.setText(str);
        }
        if (inTime != null && !inTime.equals("null")) {
            tv_start_time.setText(inTime);
        }
        if (outTime != null && !outTime.equals("null")) {
            tv_end_time.setText(outTime);
        }

        if (address != null && !address.equals("null")) {
            setAddress(address);
        }
        if (wifi != null && !wifi.equals("null")) {
            setWifi(wifi);
        }
    }


    private void setWifi(String wifi) {
        iv_wifi.setImageResource(R.mipmap.ic_wifi);
        if (Constants.isCompanyAdmin())
            iv_wifi_delete.setVisibility(View.VISIBLE);
        tv_wifi.setText(wifi);
    }

    private void setAddress(String address) {
        iv_location.setImageResource(R.mipmap.ic_location);
        if (Constants.isCompanyAdmin())
            iv_location_delete.setVisibility(View.VISIBLE);
        tv_location.setText(address);
    }

    @Override
    public void saveRoleSuccess() {
        presenter.getRole(token);
    }

    @Override
    public void updateSuccess() {
        SugarRecord.deleteAll(RuleInput.class);
        role.save();

        EventBus.getDefault().post("");
    }
}

