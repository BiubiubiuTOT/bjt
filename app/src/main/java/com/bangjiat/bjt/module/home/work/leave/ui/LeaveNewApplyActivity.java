package com.bangjiat.bjt.module.home.work.leave.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.main.ui.activity.BaseWhiteToolBarActivity;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class LeaveNewApplyActivity extends BaseWhiteToolBarActivity {
    private List<String> list;
    private OptionsPickerView<String> pvReasons;
    @BindView(R.id.tv_type)
    TextView tv_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        list = new ArrayList<>();
        list.add("年假");
        list.add("事假");
        list.add("病假");
        list.add("出差");

        initPicker();
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

    private void initPicker() {
        pvReasons = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int i, int i1, int i2, View view) {
                tv_type.setText(list.get(i));
            }
        })
                .setSubmitColor(Color.BLACK)
                .setCancelColor(Color.BLACK).build();
        pvReasons.setPicker(list);
    }
}
