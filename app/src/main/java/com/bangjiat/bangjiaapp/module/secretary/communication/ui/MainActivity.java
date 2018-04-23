package com.bangjiat.bangjiaapp.module.secretary.communication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bangjiat.bangjiaapp.R;
import com.bangjiat.bangjiaapp.module.main.ui.activity.BaseToolBarActivity;

import butterknife.OnClick;

public class MainActivity extends BaseToolBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main2;
    }

    @OnClick(R.id.ll_receive)
    public void clickReceive(View view) {

    }

    @OnClick(R.id.ll_send)
    public void clickSend(View view) {
        startActivity(new Intent(mContext, OutBoxActivity.class));
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        TextView tv_title = toolbar.findViewById(R.id.toolbar_title);
        ImageView image = toolbar.findViewById(R.id.toolbar_image);
        tv_title.setText("信息交流");
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        toolbar.setNavigationIcon(R.mipmap.back_white);
    }
}
