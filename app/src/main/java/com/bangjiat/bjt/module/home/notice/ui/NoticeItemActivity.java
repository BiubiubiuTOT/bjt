package com.bangjiat.bjt.module.home.notice.ui;

import android.os.Bundle;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.main.ui.activity.BaseWhiteToolBarActivity;
import com.bangjiat.bjt.module.home.notice.beans.NoticeBean;

import butterknife.BindView;

public class NoticeItemActivity extends BaseWhiteToolBarActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_content)
    TextView tv_content;
    @BindView(R.id.tv_time)
    TextView tv_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    protected String getTitleStr() {
        return "公告栏";
    }

}
