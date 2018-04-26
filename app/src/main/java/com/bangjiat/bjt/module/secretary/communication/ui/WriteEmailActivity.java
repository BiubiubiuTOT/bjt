package com.bangjiat.bjt.module.secretary.communication.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.main.ui.activity.BaseToolBarActivity;

public class WriteEmailActivity extends BaseToolBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_write_email;
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setBackgroundColor(getResources().getColor(R.color.white));
        toolbar.setTitle("");

        TextView tv_cancel = toolbar.findViewById(R.id.toolbar_cancel);
        TextView tv_send = toolbar.findViewById(R.id.toolbar_other);
        TextView tv_title = toolbar.findViewById(R.id.toolbar_title);

        tv_cancel.setText("取消");
        tv_send.setText("发送");
        tv_title.setText("写邮件");

        tv_cancel.setTextColor(getResources().getColor(R.color.black));
        tv_send.setTextColor(getResources().getColor(R.color.mine_bg));
        tv_title.setTextColor(getResources().getColor(R.color.black));

        tv_cancel.setVisibility(View.VISIBLE);
        tv_send.setVisibility(View.VISIBLE);

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tv_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
