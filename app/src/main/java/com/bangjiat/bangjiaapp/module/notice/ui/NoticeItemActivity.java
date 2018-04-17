package com.bangjiat.bangjiaapp.module.notice.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.bangjiat.bangjiaapp.R;
import com.bangjiat.bangjiaapp.module.main.ui.activity.BaseToolBarActivity;
import com.bangjiat.bangjiaapp.module.notice.beans.NoticeBean;
import com.githang.statusbar.StatusBarCompat;

import butterknife.BindView;

public class NoticeItemActivity extends BaseToolBarActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_content)
    TextView tv_content;
    @BindView(R.id.tv_time)
    TextView tv_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.white));
        initData();
    }

    private void initData() {
        NoticeBean bean = (NoticeBean) getIntent().getSerializableExtra("data");

        tv_title.setText(bean.getTitle());
        tv_content.setText(bean.getContent());
        tv_time.setText(bean.getTime());
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_notice_item;
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        TextView textView = findViewById(R.id.toolbar_title);
        textView.setText("公告栏");
        textView.setTextColor(getResources().getColor(R.color.black));
        toolbar.setBackgroundColor(getResources().getColor(R.color.white));
        toolbar.setNavigationIcon(R.mipmap.back_black);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
