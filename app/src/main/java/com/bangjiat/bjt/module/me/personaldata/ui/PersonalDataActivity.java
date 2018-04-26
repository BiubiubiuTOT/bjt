package com.bangjiat.bjt.module.me.personaldata.ui;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.common.TimeUtils;
import com.bangjiat.bjt.module.main.ui.activity.BaseColorToolBarActivity;
import com.bangjiat.bjt.module.me.personaldata.beans.UserInfoBean;
import com.bangjiat.bjt.module.me.personaldata.contract.UpdateUserInfoContract;
import com.bangjiat.bjt.module.me.personaldata.presenter.UpdateUserInfoPresenter;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.dou361.dialogui.DialogUIUtils;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

public class PersonalDataActivity extends BaseColorToolBarActivity implements UpdateUserInfoContract.View {
    public static final String TITLE = "title";
    public static final String TYPE = "type";
    public static final String MESSAGE = "message";
    public static final int TYPE_NICKNAME = 1;
    public static final int TYPE_PHONE = 2;
    public static final int TYPE_SEX = 3;
    private UpdateUserInfoContract.Presenter presenter;

    @BindView(R.id.tv_nickname)
    TextView tv_nickname;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.tv_sex)
    TextView tv_sex;
    @BindView(R.id.tv_date)
    TextView tv_date;
    private TimePickerView pvTime;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDate();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_edit_personal_data;
    }

    @Override
    protected String getTitleStr() {
        return "编辑资料";
    }

    @OnClick(R.id.ll_nickname)
    public void clickNickName(View v) {
        editData("昵称", 1, tv_nickname.getText().toString());
    }

    private void editData(String title, int type, String message) {
        Intent intent = new Intent(mContext, EditDataActivity.class);
        intent.putExtra(TITLE, title);
        intent.putExtra(TYPE, type);
        intent.putExtra(MESSAGE, message);
        startActivityForResult(intent, type);
    }

    @OnClick(R.id.ll_phone)
    public void clickPhone(View view) {
        editData("手机号", 2, tv_phone.getText().toString());
    }

    @OnClick(R.id.ll_sex)
    public void clickSex(View view) {
        editData("性别", 3, tv_sex.getText().toString());
    }

    @OnClick(R.id.ll_birthday)
    public void clickBirthday(View view) {
        pvTime.show();
    }

    private void initDate() {
        presenter = new UpdateUserInfoPresenter(this);

        Calendar selectedDate = Calendar.getInstance();
        selectedDate.set(1900, 0, 1);
        Calendar startDate = Calendar.getInstance();
        startDate.set(1987, 0, 30);
        Calendar endDate = Calendar.getInstance();

        pvTime = new TimePickerBuilder(mContext, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                tv_date.setText(TimeUtils.changeToYMD(date.getTime()));
            }
        }).setSubmitColor(Color.BLACK)//确定按钮文字颜色
                .setCancelColor(Color.BLACK)//取消按钮文字颜色
                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .setRangDate(startDate, endDate)//起始终止年月日设定
                .build();

    }


    @OnClick(R.id.ll_code)
    public void clickCode(View view) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String message = data.getStringExtra(MESSAGE);
            switch (requestCode) {
                case TYPE_NICKNAME:
                    tv_nickname.setText(message);
                    break;
                case TYPE_PHONE:
                    tv_phone.setText(message);
                    break;
                case TYPE_SEX:
                    tv_sex.setText(message);
                    break;
            }
        }
    }

    @Override
    public void showDialog() {
        dialog = DialogUIUtils.showLoadingVertical(this, "加载中...").show();
    }

    @Override
    public void dismissDialog() {
        if (dialog != null)
            dialog.dismiss();
    }

    @Override
    public void updateUserInfoSuccess() {
        finish();
    }

    @Override
    public void updateUserInfoFail(String err) {
        Toast.makeText(mContext, err, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            saveDateAndExit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveDateAndExit() {
        int sex = 0;
        if (tv_sex.getText().toString().equals("女"))
            sex = 1;

        UserInfoBean userInfoBean = new UserInfoBean(tv_nickname.getText().toString(),
                sex, tv_date.getText().toString(), tv_phone.getText().toString());
        userInfoBean.setUsername(DataUtil.getAccount(mContext).getPhone());
        userInfoBean.setPassword("123456");
        presenter.updateUserInfo(DataUtil.getToken(mContext), userInfoBean);
    }
}
