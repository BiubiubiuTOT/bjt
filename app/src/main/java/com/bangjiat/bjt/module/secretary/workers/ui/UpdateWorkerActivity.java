package com.bangjiat.bjt.module.secretary.workers.ui;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.adorkable.iosdialog.AlertDialog;
import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.module.main.ui.activity.BaseToolBarActivity;
import com.bangjiat.bjt.module.me.personaldata.beans.UserInfo;
import com.bangjiat.bjt.module.secretary.workers.beans.WorkersResult;
import com.bangjiat.bjt.module.secretary.workers.ui.contract.CompanyUserContract;
import com.bangjiat.bjt.module.secretary.workers.ui.presenter.CompanyUserPresenter;
import com.dou361.dialogui.DialogUIUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

public class UpdateWorkerActivity extends BaseToolBarActivity implements CompanyUserContract.View {
    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.et_id_card)
    EditText et_card;
    @BindView(R.id.et_duty)
    EditText et_duty;
    @BindView(R.id.et_department)
    EditText et_department;
    WorkersResult.RecordsBean bean;
    private Dialog dialog;
    private CompanyUserContract.Presenter presenter;
    private String token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }

    private void initData() {
        token = DataUtil.getToken(mContext);
        presenter = new CompanyUserPresenter(this);
        bean = (WorkersResult.RecordsBean) getIntent().getSerializableExtra("data");
        if (bean != null) {
            et_name.setText(bean.getRealname());
            et_card.setText(bean.getIdNumber());
            et_phone.setText(bean.getPhone());
            et_department.setText(bean.getDepartment());
            et_duty.setText(bean.getJob());
        }


        et_name.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Drawable drawable = et_name.getCompoundDrawables()[2];
                if (drawable == null)
                    return false;
                if (event.getAction() != MotionEvent.ACTION_UP)
                    return false;
                if (event.getX() > et_name.getWidth()
                        - et_name.getPaddingRight()
                        - drawable.getIntrinsicWidth()) {
                    et_name.setText("");
                }
                return false;
            }
        });

        et_card.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // et.getCompoundDrawables()得到一个长度为4的数组，分别表示左右上下四张图片
                Drawable drawable = et_card.getCompoundDrawables()[2];
                //如果右边没有图片，不再处理
                if (drawable == null)
                    return false;
                //如果不是按下事件，不再处理
                if (event.getAction() != MotionEvent.ACTION_UP)
                    return false;
                if (event.getX() > et_card.getWidth()
                        - et_card.getPaddingRight()
                        - drawable.getIntrinsicWidth()) {
                    et_card.setText("");
                }
                return false;
            }
        });

        et_department.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // et.getCompoundDrawables()得到一个长度为4的数组，分别表示左右上下四张图片
                Drawable drawable = et_department.getCompoundDrawables()[2];
                //如果右边没有图片，不再处理
                if (drawable == null)
                    return false;
                //如果不是按下事件，不再处理
                if (event.getAction() != MotionEvent.ACTION_UP)
                    return false;
                if (event.getX() > et_department.getWidth()
                        - et_department.getPaddingRight()
                        - drawable.getIntrinsicWidth()) {
                    et_department.setText("");
                }
                return false;
            }
        });
        et_duty.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // et.getCompoundDrawables()得到一个长度为4的数组，分别表示左右上下四张图片
                Drawable drawable = et_duty.getCompoundDrawables()[2];
                //如果右边没有图片，不再处理
                if (drawable == null)
                    return false;
                //如果不是按下事件，不再处理
                if (event.getAction() != MotionEvent.ACTION_UP)
                    return false;
                if (event.getX() > et_duty.getWidth()
                        - et_duty.getPaddingRight()
                        - drawable.getIntrinsicWidth()) {
                    et_duty.setText("");
                }
                return false;
            }
        });

        et_phone.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // et.getCompoundDrawables()得到一个长度为4的数组，分别表示左右上下四张图片
                Drawable drawable = et_phone.getCompoundDrawables()[2];
                //如果右边没有图片，不再处理
                if (drawable == null)
                    return false;
                //如果不是按下事件，不再处理
                if (event.getAction() != MotionEvent.ACTION_UP)
                    return false;
                if (event.getX() > et_phone.getWidth()
                        - et_phone.getPaddingRight()
                        - drawable.getIntrinsicWidth()) {
                    et_phone.setText("");
                }
                return false;
            }
        });


    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_update_worker;
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        TextView tv_done = toolbar.findViewById(R.id.toolbar_other);
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
        UserInfo userInfo = UserInfo.first(UserInfo.class);
        if (!userInfo.getUserId().equals(bean.getUserId())) {
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
