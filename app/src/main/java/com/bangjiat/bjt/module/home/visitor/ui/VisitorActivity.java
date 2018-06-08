package com.bangjiat.bjt.module.home.visitor.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.MyAdapter;
import com.bangjiat.bjt.module.main.ui.activity.BaseToolBarActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class VisitorActivity extends BaseToolBarActivity {
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.rb1)
    RadioButton rb1;
    @BindView(R.id.rb2)
    RadioButton rb2;
    @BindView(R.id.rb3)
    RadioButton rb3;
    private int index;

    private Fragment fragment_visitor, fragment_invite, fragment_history;
    private List<Fragment> fragments;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_visitor;
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        TextView textView = toolbar.findViewById(R.id.toolbar_title);
        textView.setText("访客");
        textView.setTextColor(getResources().getColor(R.color.black));
        toolbar.setBackgroundColor(getResources().getColor(R.color.white));
        toolbar.setNavigationIcon(R.mipmap.back_black);
        TextView other = toolbar.findViewById(R.id.toolbar_other);

        other.setText("邀请");
        other.setTextColor(getResources().getColor(R.color.black));
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, InviteActivity.class));
            }
        });
    }


    private void initClick() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0: {
                        rb1.setChecked(true);
                    }
                    break;
                    case 1: {
                        rb2.setChecked(true);
                    }
                    break;
                    case 2: {
                        rb3.setChecked(true);
                    }
                    break;
                }
            }


            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });


        //rg  点击rg跳转
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb1:
                        index = 0;
                        break;
                    case R.id.rb2:
                        index = 1;
                        break;
                    case R.id.rb3:
                        index = 2;
                        break;
                }
                if (viewPager.getCurrentItem() != index) {
                    viewPager.setCurrentItem(index, false);
                }


            }
        });


    }

    protected void initView() {
        fragment_visitor = new VisitorFragment();
        fragment_invite = new InviteFragment();
        fragment_history = new HistoryFragment();

        fragments = new ArrayList<>();

        fragments.add(fragment_visitor);
        fragments.add(fragment_history);
        fragments.add(fragment_invite);

        myAdapter = new MyAdapter(getSupportFragmentManager(), fragments);

        viewPager.setAdapter(myAdapter);
        initClick();
    }


}
