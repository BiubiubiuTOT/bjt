package com.bangjiat.bjt.module.home.visitor.ui;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.ClearEditText;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.common.KeyboardUtil;
import com.bangjiat.bjt.common.TimeUtils;
import com.bangjiat.bjt.module.home.visitor.beans.InviteBean;
import com.bangjiat.bjt.module.home.visitor.beans.VisitorBean;
import com.bangjiat.bjt.module.home.visitor.contract.VisitorContract;
import com.bangjiat.bjt.module.home.visitor.presenter.VisitorPresenter;
import com.bangjiat.bjt.module.main.ui.activity.BaseToolBarActivity;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.dou361.dialogui.DialogUIUtils;
import com.orhanobut.logger.Logger;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

public class InviteActivity extends BaseToolBarActivity implements VisitorContract.View {
    @BindView(R.id.et_name)
    ClearEditText et_name;
    @BindView(R.id.et_reason)
    ClearEditText et_reason;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.et_phone)
    ClearEditText et_phone;
    private Dialog dialog;
    private VisitorContract.Presenter presenter;
    private long time;
    private TimePickerView pvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new VisitorPresenter(this);
        initTimePicker();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_invite;
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        TextView textView = toolbar.findViewById(R.id.toolbar_title);
        textView.setText("邀请访问");
        textView.setTextColor(getResources().getColor(R.color.black));
        toolbar.setBackgroundColor(getResources().getColor(R.color.white));
        toolbar.setNavigationIcon(R.mipmap.back_black);
        TextView other = toolbar.findViewById(R.id.toolbar_other);

        other.setText("完成");
        other.setTextColor(getResources().getColor(R.color.black));
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string = tv_time.getText().toString();

                if (string.isEmpty()) {
                    error("请选择访问时间");
                    return;
                }
                InviteBean bean = new InviteBean(et_phone.getText().toString(),
                        et_name.getText().toString(), et_reason.getText().toString(), TimeUtils.changeToLong(string));
                presenter.addInvite(DataUtil.getToken(mContext), bean);
            }
        });
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

    private void initTimePicker() {
        pvTime = new TimePickerBuilder(mContext, new OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date, View v) {
                time = date.getTime();
                tv_time.setText(TimeUtils.changeToTime(time));
            }
        }).setSubmitColor(Color.BLACK)
                .setCancelColor(Color.BLACK)
                .setType(new boolean[]{true, true, true, true, true, false})
                .setLabel("年", "月", "日", "时", "分", "")
                .build();

    }

    @Override
    public void error(String err) {
        Logger.e(err);
        Constants.showErrorDialog(mContext, err);
    }

    @OnClick(R.id.tv_time)
    public void clickTime(View view) {
        if (!pvTime.isShowing()) {
            KeyboardUtil.HideKeyboard(et_phone);
            pvTime.show();
        }
    }

    @Override
    public void success(VisitorBean bean) {

    }

    @Override
    public void dealSuccess(String str) {

    }

    @Override
    public void addInviteSuccess(String str) {
        Constants.showSuccessExitDialog(this, "邀请访问成功");
    }
}
