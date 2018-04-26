package com.bangjiat.bjt.module.me.personaldata.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.main.ui.activity.BaseToolBarActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class EditDataActivity extends BaseToolBarActivity {
    private TextView tv_title;
    private int type;
    private String message;

    @BindView(R.id.ll_input)
    LinearLayout ll_input;
    @BindView(R.id.ll_sex)
    LinearLayout ll_sex;
    @BindView(R.id.iv_man)
    ImageView iv_man;
    @BindView(R.id.iv_woman)
    ImageView iv_woman;
    @BindView(R.id.et_input)
    EditText et_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        String title = intent.getStringExtra(PersonalDataActivity.TITLE);
        message = intent.getStringExtra(PersonalDataActivity.MESSAGE);
        tv_title.setText(title);
        type = intent.getIntExtra(PersonalDataActivity.TYPE, 0);

        switch (type) {
            case PersonalDataActivity.TYPE_NICKNAME:
                ll_input.setVisibility(View.VISIBLE);
                ll_sex.setVisibility(View.GONE);
                et_input.setText(message);
                et_input.selectAll();
                break;
            case PersonalDataActivity.TYPE_SEX:
                ll_input.setVisibility(View.GONE);
                ll_sex.setVisibility(View.VISIBLE);

                if (message.equals("男")) {
                    iv_woman.setVisibility(View.GONE);
                    iv_man.setVisibility(View.VISIBLE);
                } else {
                    iv_man.setVisibility(View.GONE);
                    iv_woman.setVisibility(View.VISIBLE);
                }

                break;
            case PersonalDataActivity.TYPE_PHONE:
                ll_input.setVisibility(View.VISIBLE);
                ll_sex.setVisibility(View.GONE);
                et_input.setText(message);
                et_input.selectAll();
                break;
        }
    }

    @OnClick(R.id.ll_man)
    public void clickMan(View view) {
        iv_woman.setVisibility(View.GONE);
        iv_man.setVisibility(View.VISIBLE);
        message = "男";
    }

    @OnClick(R.id.ll_woman)
    public void clickWoman(View view) {
        iv_man.setVisibility(View.GONE);
        iv_woman.setVisibility(View.VISIBLE);
        message = "女";
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_update_data;
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        tv_title = findViewById(R.id.toolbar_title);
        TextView textView1 = findViewById(R.id.toolbar_other);
        textView1.setText("保存");
        toolbar.setNavigationIcon(R.mipmap.back_white);

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (type != PersonalDataActivity.TYPE_SEX) {
                    message = et_input.getText().toString();
                }
                Intent intent = new Intent();
                intent.putExtra(PersonalDataActivity.MESSAGE, message);

                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
