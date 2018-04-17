package com.bangjiat.bangjiaapp.module.main.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bangjiat.bangjiaapp.R;
import com.bangjiat.bangjiaapp.common.BaseFragment;
import com.bangjiat.bangjiaapp.module.notice.beans.NoticeBean;
import com.bangjiat.bangjiaapp.module.notice.ui.AllNoticeActivity;
import com.bangjiat.bangjiaapp.module.notice.ui.NoticeItemActivity;
import com.bangjiat.bangjiaapp.module.scan.ui.ScanActivity;
import com.bangjiat.bangjiaapp.module.visitor.ui.VisitorActivity;
import com.bumptech.glide.Glide;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.bgabanner.BGABanner;


public class HomeFragment extends BaseFragment {
    @BindView(R.id.banner_guide_content)
    BGABanner mContentBanner;

    protected void initView() {
        mContentBanner.setData(Arrays.asList("http://pic2.ooopic.com/12/42/25/02bOOOPIC95_1024.jpg",
                "http://pic4.nipic.com/20091121/3764872_215617048242_2.jpg",
                "http://imgsrc.baidu.com/image/c0%3Dshijue1%2C0%2C0%2C294%2C40/sign=9b867a04b299a9012f38537575fc600e/4d086e061d950a7b86bee8d400d162d9f2d3c913.jpg"), null);
        mContentBanner.setDelegate(new BGABanner.Delegate<ImageView, String>() {
            @Override
            public void onBannerItemClick(BGABanner banner, ImageView itemView, String model, int position) {
                Toast.makeText(banner.getContext(), "点击了" + position, Toast.LENGTH_SHORT).show();
            }
        });
        mContentBanner.setAdapter(new BGABanner.Adapter<ImageView, String>() {
            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
                Glide.with(itemView.getContext())
                        .load(model)
                        .placeholder(R.drawable.holder).error(R.drawable.holder).dontAnimate().centerCrop()
                        .into(itemView);
            }
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @OnClick(R.id.tv_more_notice)
    public void clickMoreNotice(View view) {
        startActivity(new Intent(mContext, AllNoticeActivity.class));
    }

    @OnClick(R.id.tv_content)
    public void clickContent(View view) {
        Intent intent = new Intent(mContext, NoticeItemActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", new NoticeBean("国庆放假通知：", "国庆七天假期，国际互联网金融特区大厦国庆七天假期，国际互联网金融特区大厦国庆七天假期，国际互联网金融特区大厦国庆七天假期，国际互联网金融特区大厦国庆七天假期，国际互联网金融特国庆七天假期，国际互联网金融特区大厦国庆七天假期，" +
                "国际互联网金融特区大厦国庆七天假期，国际互联网金融特区大厦国庆七天假期，国际互联网金融特区大厦国庆七天假期，国际互联网金融特区大厦。", "2018-03-24 16:54"));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @OnClick(R.id.tv_visitor)
    public void clickVisitor(View view) {
        startActivity(new Intent(mContext, VisitorActivity.class));
    }

    @OnClick(R.id.iv_scan)
    public void clickScan(View view) {
        startActivity(new Intent(mContext, ScanActivity.class));
    }
}

