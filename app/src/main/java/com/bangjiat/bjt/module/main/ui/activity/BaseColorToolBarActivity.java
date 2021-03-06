package com.bangjiat.bjt.module.main.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.bangjiat.bjt.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseColorToolBarActivity extends AppCompatActivity {
    public Context mContext;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        mContext = this;
        ButterKnife.bind(this);
        initToolbar();
    }

    private void initToolbar() {
        toolbar.setTitle("");
        TextView textView = findViewById(R.id.toolbar_title);
        textView.setText(getTitleStr());
        toolbar.setNavigationIcon(R.mipmap.back_white);

        setSupportActionBar(toolbar);
    }

    /**
     * 返回当前Activity布局文件的id
     *
     * @return 布局
     */
    abstract protected int getLayoutResId();

    abstract protected String getTitleStr();


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
