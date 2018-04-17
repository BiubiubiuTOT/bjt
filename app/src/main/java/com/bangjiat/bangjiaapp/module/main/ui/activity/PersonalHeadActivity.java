package com.bangjiat.bangjiaapp.module.main.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bangjiat.bangjiaapp.R;
import com.bumptech.glide.Glide;
import com.githang.statusbar.StatusBarCompat;

import butterknife.BindView;

public class PersonalHeadActivity extends BaseToolBarActivity {
    @BindView(R.id.iv_head)
    ImageView iv_head;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.white));
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
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        TextView textView = findViewById(R.id.toolbar_title);
        textView.setText("个人头像");
        textView.setTextColor(getResources().getColor(R.color.black));
        toolbar.setBackgroundColor(getResources().getColor(R.color.white));
        toolbar.setNavigationIcon(R.mipmap.back_black);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
