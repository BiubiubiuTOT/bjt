package com.bangjiat.bjt.module.home.work.kaoqin.ui;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.BaseFragment;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;


public class SettingFragment extends BaseFragment {
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

    private OptionsPickerView<String> pvMonths;
    private List<String> ampm;
    private List<String> hours;
    private List<String> minutes;
    private boolean isStart;

    @Override
    protected void initView() {
        initData();
    }

    private void initData() {
        ampm = new ArrayList<>();
        ampm.add("上午");
        ampm.add("下午");

        hours = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
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
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_setting;
    }

    @OnClick(R.id.ll_days)
    public void clickDays(View view) {
        startActivityForResult(new Intent(mContext, WorkDayActivity.class), SELECT_WORK_DAY);
    }

    @OnClick(R.id.ll_time_start)
    public void clickTimeStart(View v) {
        isStart = true;
        pvMonths.show();
    }

    @OnClick(R.id.ll_time_end)
    public void clickTimeEnd(View view) {
        isStart = false;
        pvMonths.show();
    }

    @OnClick(R.id.ll_address)
    public void clickAddress(View view) {
        startActivityForResult(new Intent(mContext, LocationActivity.class), SELECT_LOCATION);
    }

    @OnClick(R.id.ll_range)
    public void clickRange(View view) {
        startActivityForResult(new Intent(mContext, AddWifiActivity.class), SELECT_WIFI);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_WORK_DAY) {
                String str = data.getStringExtra("data");
                tv_work_day.setText(str);
            } else if (requestCode == SELECT_WIFI) {
                String str = data.getStringExtra("data");
                tv_wifi.setText(str);
            }
        }
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
                } else tv_end_time.setText(str);
            }
        })
                .setSubmitColor(Color.BLACK)
                .setCancelColor(Color.BLACK).build();
        pvMonths.setNPicker(ampm, hours, minutes);
    }
}

