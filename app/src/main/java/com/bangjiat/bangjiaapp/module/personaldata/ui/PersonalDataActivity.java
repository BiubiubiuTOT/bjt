package com.bangjiat.bangjiaapp.module.personaldata.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bangjiat.bangjiaapp.R;
import com.bangjiat.bangjiaapp.common.TimeUtils;
import com.bangjiat.bangjiaapp.module.main.ui.activity.BaseColorToolBarActivity;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import butterknife.BindView;
import butterknife.OnClick;

public class PersonalDataActivity extends BaseColorToolBarActivity implements OnDateSetListener {
    public static final String TITLE = "title";
    public static final String TYPE = "type";
    public static final String MESSAGE = "message";
    public static final int TYPE_NICKNAME = 1;
    public static final int TYPE_PHONE = 2;
    public static final int TYPE_SEX = 3;

    @BindView(R.id.tv_nickname)
    TextView tv_nickname;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.tv_sex)
    TextView tv_sex;
    @BindView(R.id.tv_date)
    TextView tv_date;
    private TimePickerDialog mDialogYearMonthDay;

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
        mDialogYearMonthDay.show(getSupportFragmentManager(), "year_month_day");
    }

    private void initDate() {
        mDialogYearMonthDay = new TimePickerDialog.Builder()
                .setType(Type.YEAR_MONTH_DAY)
                .setCallBack(this)
                .setYearText("年")
                .setMonthText("月")
                .setThemeColor(getResources().getColor(R.color.mine_bg))
                .setDayText("日")
                .setTitleStringId("")
                .setSureStringId("完成")
                .setCancelStringId("取消")
                .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.black))
                .setMinMillseconds(TimeUtils.changeToLong("1987-1-1"))
                .setMaxMillseconds(System.currentTimeMillis())
                .setCurrentMillseconds(TimeUtils.changeToLong("1990-1-30"))
                .build();
    }

    @Override
    public void onDateSet(TimePickerDialog timePickerDialog, long l) {
        tv_date.setText(TimeUtils.changeToYMD(l));
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

}
