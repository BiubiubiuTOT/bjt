package com.bangjiat.bjt.module.me.personaldata.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.adorkable.iosdialog.AlertDialog;
import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.ClearEditText;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.common.ValidationIdCard;
import com.bangjiat.bjt.module.main.ui.activity.BaseToolBarActivity;
import com.bangjiat.bjt.module.me.personaldata.beans.UserInfo;
import com.bangjiat.bjt.module.me.personaldata.contract.UpdateUserInfoContract;
import com.bangjiat.bjt.module.me.personaldata.presenter.UpdateUserInfoPresenter;
import com.dou361.dialogui.DialogUIUtils;
import com.orm.SugarRecord;

import butterknife.BindView;

public class IdNumberInfoActivity extends BaseToolBarActivity implements UpdateUserInfoContract.View {
    @BindView(R.id.et_name)
    ClearEditText et_name;
    @BindView(R.id.et_id_number)
    ClearEditText et_id_number;

    private int type;
    private TextView tv_done;
    private UpdateUserInfoContract.Presenter presenter;
    private UserInfo data;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getIntent().getIntExtra("type", 0);
        presenter = new UpdateUserInfoPresenter(this);
        data = UserInfo.first(UserInfo.class);

        if (type != 1) {
            et_name.setFocusable(false);
            et_id_number.setFocusable(false);
            String name = getIntent().getStringExtra("name");
            String idNumber = getIntent().getStringExtra("idNumber");

            et_name.setText(name);
            et_id_number.setText(idNumber);
            tv_done.setVisibility(View.GONE);
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_id_number_info;
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setNavigationIcon(R.mipmap.back_white);
        toolbar.setTitle("");

        TextView tv_title = toolbar.findViewById(R.id.toolbar_title);
        tv_done = toolbar.findViewById(R.id.toolbar_other);
        tv_title.setText("身份信息");
        tv_done.setText("保存");

        tv_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string = et_name.getText().toString();
                if (string.isEmpty()) {
                    Constants.showErrorDialog(mContext, "姓名为空");
                    return;
                }
                if (string.length() > 16) {
                    Constants.showErrorDialog(mContext, "姓名错误");
                    return;
                }

                if (ValidationIdCard.verify(et_id_number.getText().toString()))
                    showDia();
                else Constants.showErrorDialog(mContext, "身份证号码错误");
            }
        });
    }

    private void showDia() {
        new AlertDialog(mContext).builder().setMsg("身份信息验证通过后不可修改，\n" +
                "是否确认？").
                setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        UserInfo userInfoBean = new UserInfo(data.getNickname(),
                                data.getSex(), data.getBirthday(), data.getPhone());
                        userInfoBean.setUsername(DataUtil.getAccount(mContext).getPhone());
                        userInfoBean.setAvatar(data.getAvatar());

                        userInfoBean.setRealname(et_name.getText().toString());
                        userInfoBean.setIdNumber(et_id_number.getText().toString());
                        presenter.updateUserInfo(DataUtil.getToken(mContext), userInfoBean);

                    }
                }).setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }).show();
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

        Intent intent = new Intent();
        intent.putExtra("name", et_name.getText().toString());
        intent.putExtra("idNumber", et_id_number.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void updateUserInfoFail(String err) {

    }

    @Override
    public void uploadUserHeadSuccess(String url) {

    }

    @Override
    public void uploadUserHeadFail(String err) {

    }
}
