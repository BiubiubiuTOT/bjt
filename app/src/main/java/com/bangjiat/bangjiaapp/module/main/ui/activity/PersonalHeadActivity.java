package com.bangjiat.bangjiaapp.module.main.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bangjiat.bangjiaapp.R;
import com.bumptech.glide.Glide;

import butterknife.BindView;

public class PersonalHeadActivity extends BaseWhiteToolBarActivity {
    @BindView(R.id.iv_head)
    ImageView iv_head;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        Glide.with(mContext).load(R.mipmap.head).centerCrop().into(iv_head);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_personal_head;
    }

    @Override
    protected String getTitleStr() {
        return "个人头像";
    }
}
