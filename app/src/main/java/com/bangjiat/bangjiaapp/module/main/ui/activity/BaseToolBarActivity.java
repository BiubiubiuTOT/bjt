package com.bangjiat.bangjiaapp.module.main.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.bangjiat.bangjiaapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseToolBarActivity extends AppCompatActivity {
    public Context mContext;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        mContext = this;
        ButterKnife.bind(this);
        initToolbar(toolbar);
        setSupportActionBar(toolbar);
    }

    /**
     * 返回当前Activity布局文件的id
     *
     * @return 布局
     */
    abstract protected int getLayoutResId();

    abstract protected void initToolbar(Toolbar toolbar);
}
