package com.bangjiat.bjt.module.home.work.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.adorkable.iosdialog.AlertDialog;
import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.main.ui.activity.BaseToolBarActivity;

import butterknife.OnClick;

public class EditCompanyActivity extends BaseToolBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick(R.id.tv_delete)
    public void clickDelete(View view) {
        showDia();
    }

    private void showDia() {
        new AlertDialog(mContext).builder().setMsg("删除后数据无法恢复\n" +
                "确认删除吗?").
                setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }).setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }).show();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_edit_company;
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        TextView tv_title = toolbar.findViewById(R.id.toolbar_title);
        TextView tv_done = toolbar.findViewById(R.id.toolbar_other);

        tv_title.setText("编辑公司");
        tv_done.setText("完成");
        toolbar.setNavigationIcon(R.mipmap.back_black);
        toolbar.setBackgroundColor(getResources().getColor(R.color.white));
        tv_done.setTextColor(getResources().getColor(R.color.black));
        tv_title.setTextColor(getResources().getColor(R.color.black));

        tv_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
