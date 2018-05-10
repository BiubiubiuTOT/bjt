package com.bangjiat.bjt.module.me.personaldata.ui;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.common.TimeUtils;
import com.bangjiat.bjt.module.home.scan.ui.MyCodeActivity;
import com.bangjiat.bjt.module.main.ui.activity.BaseColorToolBarActivity;
import com.bangjiat.bjt.module.me.personaldata.beans.UserInfo;
import com.bangjiat.bjt.module.me.personaldata.contract.UpdateUserInfoContract;
import com.bangjiat.bjt.module.me.personaldata.presenter.UpdateUserInfoPresenter;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.dou361.dialogui.DialogUIUtils;
import com.orm.SugarRecord;

import org.greenrobot.eventbus.EventBus;

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
    public static final int TYPE_ID_NUMBER = 4;
    private UpdateUserInfoContract.Presenter presenter;

    @BindView(R.id.tv_nickname)
    TextView tv_nickname;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.tv_sex)
    TextView tv_sex;
    @BindView(R.id.tv_date)
    TextView tv_date;
    @BindView(R.id.tv_id_number)
    TextView tv_id_number;

    private TimePickerView pvTime;
    private Dialog dialog;

    private boolean isEdit;
    private UserInfo data;
    private String realName;
    private String idNumber;
    private String icon;

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

    @OnClick(R.id.ll_id_number)
    public void clickIdNumber(View view) {
        Intent intent = new Intent(mContext, IdNumberInfoActivity.class);
        if (data.getIdNumber() != null && !data.getIdNumber().isEmpty()) {
            intent.putExtra("name", data.getRealname());
            intent.putExtra("idNumber", data.getIdNumber());
        } else {
            intent.putExtra(TYPE, 1);
        }
        startActivityForResult(intent, TYPE_ID_NUMBER);
    }

    private void initDate() {
        data = UserInfo.first(UserInfo.class);
        if (data != null) {
            icon = data.getAvatar();
            tv_nickname.setText(data.getNickname());
            tv_phone.setText(data.getPhone());
            tv_sex.setText(data.getSex() == 0 ? "男" : "女");
            tv_date.setText(data.getBirthday());
            if (data.getIdNumber() != null && !data.getIdNumber().isEmpty()) {
                tv_id_number.setText("已验证");
            }
        }


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
                isEdit = true;
            }
        }).setSubmitColor(Color.BLACK)//确定按钮文字颜色
                .setCancelColor(Color.BLACK)//取消按钮文字颜色
                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .setRangDate(startDate, endDate)//起始终止年月日设定
                .build();

    }


    @OnClick(R.id.ll_code)
    public void clickCode(View view) {
        Intent intent = new Intent(mContext, MyCodeActivity.class);
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            isEdit = true;
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
                case TYPE_ID_NUMBER:
                    realName = data.getStringExtra("name");
                    idNumber = data.getStringExtra("idNumber");
                    tv_id_number.setText("已验证");
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
    public void updateUserInfoSuccess(UserInfo info) {
        SugarRecord.deleteAll(UserInfo.class);
        SugarRecord.save(info);

        EventBus.getDefault().post(info);
        finish();
    }

    @Override
    public void updateUserInfoFail(String err) {
        Toast.makeText(mContext, err, Toast.LENGTH_LONG).show();
    }

    @Override
    public void uploadUserHeadSuccess(String url) {

    }

    @Override
    public void uploadUserHeadFail(String err) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) { //按下的如果是BACK，同时没有重复
            saveDateAndExit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
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
        if (!isEdit) {
            finish();
            return;
        }

        int sex = 0;
        if (tv_sex.getText().toString().equals("女"))
            sex = 1;

        UserInfo userInfoBean = new UserInfo(tv_nickname.getText().toString(),
                sex, tv_date.getText().toString(), tv_phone.getText().toString());
        userInfoBean.setUsername(DataUtil.getAccount(mContext).getPhone());
        userInfoBean.setAvatar(icon);
        if (realName != null) {
            userInfoBean.setIdNumber(idNumber);
            userInfoBean.setRealname(realName);
        } else {
            userInfoBean.setRealname(data.getRealname());
            userInfoBean.setIdNumber(data.getIdNumber());
        }
        presenter.updateUserInfo(DataUtil.getToken(mContext), userInfoBean);
    }
}
