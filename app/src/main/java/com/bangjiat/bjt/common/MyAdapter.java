package com.bangjiat.bjt.common;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ligh
 * @email 1256144200@qq.com
 * @date 2018/4/28 0028
 */

public class MyAdapter extends FragmentPagerAdapter {


    private Context context;
    private List<Fragment> list = new ArrayList<>();


    public MyAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);

        this.list = list;
    }

    public MyAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }
}
