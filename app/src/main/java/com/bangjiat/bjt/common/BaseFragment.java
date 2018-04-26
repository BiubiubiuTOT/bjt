package com.bangjiat.bjt.common;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;


public abstract class BaseFragment extends Fragment {
    public View mView;
    public Context mContext;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutResId(), container, false);
        ButterKnife.bind(this, mView);
        mContext = getContext();
        initView();
        return mView;
    }


    abstract protected void initView();

    /**
     * 返回当前Activity布局文件的id
     *
     * @return 布局
     */
    abstract protected int getLayoutResId();

}

