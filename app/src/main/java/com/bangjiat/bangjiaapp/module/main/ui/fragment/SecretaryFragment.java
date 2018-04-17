package com.bangjiat.bangjiaapp.module.main.ui.fragment;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bangjiat.bangjiaapp.R;
import com.bangjiat.bangjiaapp.common.BaseFragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import butterknife.BindView;


public class SecretaryFragment extends BaseFragment {
    private View view;
    private Context context;

    @BindView(R.id.iv_door)
    ImageView iv_door;
    @BindView(R.id.iv_contact_us)
    ImageView iv_contact_us;
    @BindView(R.id.iv_service)
    ImageView iv_service;
    @BindView(R.id.iv_message)
    ImageView iv_message;
    @BindView(R.id.iv_people)
    ImageView iv_people;

    protected void initView() {
        Glide.with(this).load(R.mipmap.door).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().into(iv_door);
        Glide.with(this).load(R.mipmap.service).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().into(iv_service);
        Glide.with(this).load(R.mipmap.message).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().into(iv_message);
        Glide.with(this).load(R.mipmap.contact_us).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().into(iv_contact_us);
        Glide.with(this).load(R.mipmap.pic_people).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().into(iv_people);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_secretary;
    }


}

