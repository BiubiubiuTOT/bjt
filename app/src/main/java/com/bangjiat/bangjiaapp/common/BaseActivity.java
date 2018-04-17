package com.bangjiat.bangjiaapp.common;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        mContext = this;
        ButterKnife.bind(this);
    }

    /**
     * 返回当前Activity布局文件的id
     *
     * @return 布局
     */
    abstract protected int getLayoutResId();
}
