package com.bangjiat.bjt.common;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bangjiat.bjt.R;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：Rance on 2016/12/15 15:56
 * 邮箱：rance935@163.com
 */
public class FullImageActivity extends BaseActivity {
    @BindView(R.id.full_image)
    ImageView fullImage;
    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        path = getIntent().getStringExtra("path");
        Glide.with(this).load(path).into(fullImage);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_full_image;
    }


    @Override
    public void onBackPressed() {
        finish();
    }

    @OnClick(R.id.full_image)
    public void onClick() {
        finish();
    }

}
