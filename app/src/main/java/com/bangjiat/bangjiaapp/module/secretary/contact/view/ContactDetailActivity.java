package com.bangjiat.bangjiaapp.module.secretary.contact.view;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bangjiat.bangjiaapp.R;
import com.bangjiat.bangjiaapp.module.main.ui.activity.BaseToolBarActivity;

import butterknife.BindView;

public class ContactDetailActivity extends BaseToolBarActivity {
    private Toolbar toolbar;
    private TextView tv_edit;
    private TextView toolbar_cancel;

    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.ll_remark)
    LinearLayout ll_remark;
    @BindView(R.id.ll_phone)
    LinearLayout ll_phone;
    @BindView(R.id.et_remark)
    EditText et_remark;
    @BindView(R.id.view_phone)
    View view_phone;
    @BindView(R.id.view_remark)
    View view_remark;
    @BindView(R.id.iv_clear)
    ImageView iv_clear;
    @BindView(R.id.tv_send)
    TextView tv_send;
    @BindView(R.id.tv_delete)
    TextView tv_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_contact_detail;
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
        toolbar.setTitle("");
        tv_edit = toolbar.findViewById(R.id.toolbar_other);
        tv_edit.setVisibility(View.VISIBLE);
        toolbar_cancel = toolbar.findViewById(R.id.toolbar_cancel);
        toolbar_cancel.setTextColor(getResources().getColor(R.color.mine_bg));
        toolbar_cancel.setText("取消");
        toolbar.setBackgroundColor(getResources().getColor(R.color.white));

        tv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tv_edit.getText().equals("编辑")) {
                    showEdit();
                } else {
                    showCustom();
                }
            }
        });
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustom();
            }
        });

        showCustom();
    }

    private void showCustom() {
        tv_edit.setText("编辑");
        toolbar_cancel.setVisibility(View.GONE);
        tv_edit.setTextColor(getResources().getColor(R.color.mine_bg));
        toolbar.setNavigationIcon(R.mipmap.back_black);

        view_phone.setVisibility(View.GONE);
        view_remark.setVisibility(View.VISIBLE);
        iv_clear.setVisibility(View.GONE);
        tv_name.setTextColor(getResources().getColor(R.color.dialog_title));
        et_remark.setTextColor(getResources().getColor(R.color.dialog_title));
        tv_phone.setTextColor(getResources().getColor(R.color.dialog_title));
        et_remark.setEnabled(false);

        tv_send.setVisibility(View.VISIBLE);
        tv_delete.setVisibility(View.GONE);

        ll_phone.setPadding(0, 0, 0, 0);
        ll_remark.setPadding(0, 0, 0, 0);
    }

    private void showEdit() {
        tv_edit.setText("保存");
        toolbar.setNavigationIcon(null);
        toolbar_cancel.setVisibility(View.VISIBLE);

        view_phone.setVisibility(View.VISIBLE);
        view_remark.setVisibility(View.GONE);
        iv_clear.setVisibility(View.VISIBLE);
        tv_name.setTextColor(getResources().getColor(R.color.personal_message));
        tv_phone.setTextColor(getResources().getColor(R.color.personal_message));
        et_remark.setEnabled(true);

        tv_send.setVisibility(View.GONE);
        tv_delete.setVisibility(View.VISIBLE);

        ll_phone.setPadding(100, 0, 0, 0);
        ll_remark.setPadding(100, 0, 0, 0);
    }
}
