package com.bangjiat.bjt.module.me.personaldata.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.adorkable.iosdialog.AlertDialog;
import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.common.ValidationIdCard;
import com.bangjiat.bjt.module.main.ui.activity.BaseToolBarActivity;

import butterknife.BindView;

public class IdNumberInfoActivity extends BaseToolBarActivity {
    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.et_id_number)
    EditText et_id_number;

    private int type;
    private TextView tv_done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getIntent().getIntExtra("type", 0);

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
                if (ValidationIdCard.verify(et_id_number.getText().toString()))
                    showDia();
                else Constants.showErrorDialog(mContext, "请输入正确的身份证号码");
            }
        });
    }

    private void showDia() {
        new AlertDialog(mContext).builder().setMsg("身份信息验证通过后不可修改，\n" +
                "是否确认？").
                setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.putExtra("name", et_name.getText().toString());
                        intent.putExtra("idNumber", et_id_number.getText().toString());
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }).setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }).show();
    }
}
