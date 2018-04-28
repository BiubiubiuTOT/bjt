package com.bangjiat.bjt.module.home.work.kaoqin.ui;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.BaseFragment;
import com.bangjiat.bjt.common.MyAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class CountFragment extends BaseFragment {
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

    private CountDayFragment countDayFragment;
    private CountMonthFragment countMonthFragment;
    private CountMyFragment countMyFragment;
    private int index;
    private List<Fragment> fragments;
    private MyAdapter myAdapter;


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

    @Override
    protected void initView() {
        countDayFragment = new CountDayFragment();
        countMonthFragment = new CountMonthFragment();
        countMyFragment = new CountMyFragment();

        fragments = new ArrayList<>();

        fragments.add(countDayFragment);
        fragments.add(countMonthFragment);
        fragments.add(countMyFragment);


        myAdapter = new MyAdapter(this.getChildFragmentManager(), fragments);

        viewPager.setAdapter(myAdapter);
        initClick();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_count;
    }


}

