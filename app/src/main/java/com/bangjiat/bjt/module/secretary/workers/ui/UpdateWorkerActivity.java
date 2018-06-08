package com.bangjiat.bjt.module.secretary.workers.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.adorkable.iosdialog.AlertDialog;
import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.ClearEditText;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.module.main.ui.activity.BaseToolBarActivity;
import com.bangjiat.bjt.module.me.personaldata.beans.UserInfo;
import com.bangjiat.bjt.module.secretary.workers.beans.WorkersResult;
import com.bangjiat.bjt.module.secretary.workers.contract.CompanyUserContract;
import com.bangjiat.bjt.module.secretary.workers.presenter.CompanyUserPresenter;
import com.dou361.dialogui.DialogUIUtils;
import com.githang.statusbar.StatusBarCompat;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

public class UpdateWorkerActivity extends BaseToolBarActivity implements CompanyUserContract.View {
    @BindView(R.id.et_name)
    ClearEditText et_name;
    @BindView(R.id.et_phone)
    TextView et_phone;
    @BindView(R.id.et_id_card)
    TextView et_card;
    @BindView(R.id.et_duty)
    ClearEditText et_duty;
    @BindView(R.id.et_department)
    ClearEditText et_department;
    @BindView(R.id.ll_delete)
    RelativeLayout rl_delete;

    WorkersResult.RecordsBean bean;
    private Dialog dialog;
    private CompanyUserContract.Presenter presenter;
    private String token;
    private UserInfo userInfo;
    private TextView tv_done;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.white));

        initData();
    }

    private void initData() {
        token = DataUtil.getToken(mContext);
        presenter = new CompanyUserPresenter(this);
        bean = (WorkersResult.RecordsBean) getIntent().getSerializableExtra("data");
        userInfo = UserInfo.first(UserInfo.class);
        if (bean != null) {
            if (Constants.isCompanyAdmin() && !userInfo.getUserId().equals(bean.getUserId())) {//没有公司管理员权限或者是本人信息则不能删除
                rl_delete.setVisibility(View.VISIBLE);
            }
            if (!userInfo.getUserId().equals(bean.getUserId()) && !Constants.isCompanyAdmin()) {//不是本人或者没有公司管理员权限的人不能编辑
                tv_done.setVisibility(View.GONE);

                et_phone.setEnabled(false);
                et_department.setEnabled(false);
                et_duty.setEnabled(false);

                et_phone.setFocusable(false);
                et_department.setFocusable(false);
                et_duty.setFocusable(false);
            }
            String idNumber = bean.getIdNumber().replaceAll("(\\d{5})\\d{11}(\\w{2})", "$1***********$2");
            et_name.setText(bean.getRealname());
            et_card.setText(idNumber);
            et_phone.setText(bean.getPhone());
            String department = bean.getDepartment();
            if (department != null && !department.equals("null"))
                et_department.setText(department);

            String job = bean.getJob();
            if (job != null && !job.equals("null"))
                et_duty.setText(job);
        }


    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_update_worker;
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        tv_done = toolbar.findViewById(R.id.toolbar_other);
        TextView tv_title = toolbar.findViewById(R.id.toolbar_title);
        tv_done.setText("保存");
        tv_title.setText("编辑资料");
        tv_done.setTextColor(getResources().getColor(R.color.black));
        tv_title.setTextColor(getResources().getColor(R.color.black));
        toolbar.setBackgroundColor(getResources().getColor(R.color.white));

        toolbar.setNavigationIcon(R.mipmap.back_black);
        tv_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bean.setPhone(et_phone.getText().toString());
                bean.setIdNumber(et_card.getText().toString());
                bean.setDepartment(et_department.getText().toString());
                bean.setJob(et_duty.getText().toString());
                bean.setRealname(et_name.getText().toString());

                presenter.updateCompanyUser(token, bean);
            }
        });
    }

    @OnClick(R.id.tv_delete)
    public void clickDelete(View view) {
        showDeleteDialog();
    }

    private void showDeleteDialog() {
        new AlertDialog(mContext).builder().setMsg("确认删除吗?").
                setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        presenter.deleteCompanyUser(token, bean.getUserId());
                    }
                }).setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }).show();
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
        Toast.makeText(mContext, err, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getCompanyUserSuccess(WorkersResult result) {

    }

    @Override
    public void deleteCompanyUserSuccess() {
        EventBus.getDefault().post("");
        error("删除成功");
        finish();
    }

    @Override
    public void updateCompanyUserSuccess() {
        EventBus.getDefault().post("");
        error("修改成功");
        finish();
    }

    @Override
    public void addCompanyUserSuccess() {

    }
}
