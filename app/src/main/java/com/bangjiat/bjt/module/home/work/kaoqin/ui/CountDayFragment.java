package com.bangjiat.bjt.module.home.work.kaoqin.ui;

import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.BaseFragment;
import com.dinuscxj.progressbar.CircleProgressBar;

import butterknife.BindView;


public class CountDayFragment extends BaseFragment {
    @BindView(R.id.line_progress)
    CircleProgressBar circleProgressBar;
    @BindView(R.id.tv_count)
    TextView tv_count;


    @Override
    protected void initView() {
        tv_count.setText(getString(R.string.count_day_progress_message, 5, 11));

        circleProgressBar.setMax(11);
        circleProgressBar.setProgress(5);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_count_day;
    }


}

