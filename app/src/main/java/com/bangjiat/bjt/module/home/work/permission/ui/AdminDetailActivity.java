package com.bangjiat.bjt.module.home.work.permission.ui;

import android.os.Bundle;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.main.ui.activity.BaseColorToolBarActivity;
import com.bangjiat.bjt.module.secretary.workers.beans.WorkersResult;

import butterknife.BindView;

public class AdminDetailActivity extends BaseColorToolBarActivity {
    @BindView(R.id.et_name)
    TextView et_name;
    @BindView(R.id.et_phone)
    TextView et_phone;
    @BindView(R.id.et_id_card)
    TextView et_card;
    @BindView(R.id.et_duty)
    TextView et_duty;
    @BindView(R.id.et_department)
    TextView et_department;
    WorkersResult.RecordsBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }

    private void initData() {
        bean = (WorkersResult.RecordsBean) getIntent().getSerializableExtra("data");
        if (bean != null) {
            et_name.setText(bean.getRealname());
            String idNumber = bean.getIdNumber().replaceAll("(\\d{5})\\d{11}(\\w{2})", "$1***********$2");
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
        return R.layout.activity_admin_detail;
    }

    @Override
    protected String getTitleStr() {
        return "管理员详情";
    }
}
