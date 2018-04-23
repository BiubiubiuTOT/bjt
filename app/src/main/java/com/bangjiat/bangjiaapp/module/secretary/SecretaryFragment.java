package com.bangjiat.bangjiaapp.module.secretary;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bangjiat.bangjiaapp.R;
import com.bangjiat.bangjiaapp.common.BaseFragment;
import com.bangjiat.bangjiaapp.module.secretary.service.ui.MainActivity;
import com.bangjiat.bangjiaapp.module.secretary.workers.ui.WorkersManageActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import butterknife.BindView;
import butterknife.OnClick;


public class SecretaryFragment extends BaseFragment {
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

    @OnClick(R.id.card_door)
    public void clickDoor(View view) {
        startActivity(new Intent(mContext, com.bangjiat.bangjiaapp.module.secretary.door.ui.MainActivity.class));
    }

    @OnClick(R.id.card_service)
    public void clickCardService(View view) {
        startActivity(new Intent(mContext, MainActivity.class));
    }

    @OnClick(R.id.card_message)
    public void clickMessage(View view) {
        startActivity(new Intent(mContext, com.bangjiat.bangjiaapp.module.secretary.communication.ui.MainActivity.class));
    }

    @OnClick(R.id.card_contact_us)
    public void clickContactUs(View view) {

    }

    @OnClick(R.id.card_people)
    public void clickPeople(View view) {
        startActivity(new Intent(mContext, WorkersManageActivity.class));
    }
}

