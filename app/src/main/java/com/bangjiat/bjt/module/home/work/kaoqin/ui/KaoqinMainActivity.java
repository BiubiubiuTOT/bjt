package com.bangjiat.bjt.module.home.work.kaoqin.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.TimeUtils;
import com.bangjiat.bjt.module.main.ui.activity.BaseToolBarActivity;
import com.githang.statusbar.StatusBarCompat;

import butterknife.BindView;
import butterknife.OnClick;

public class KaoqinMainActivity extends BaseToolBarActivity {
    private Fragment fragment_daka, fragment_count, fragment_setting;

    @BindView(R.id.tv_daka)
    TextView tv_daka;
    @BindView(R.id.view_daka)
    View view_daka;
    @BindView(R.id.tv_count)
    TextView tv_count;
    @BindView(R.id.view_count)
    View view_count;
    @BindView(R.id.tv_setting)
    TextView tv_setting;
    @BindView(R.id.view_setting)
    View view_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.white));
        switchPage(0);
    }

    @OnClick(R.id.tv_daka)
    public void clickDaka(View view) {
        switchPage(0);
    }

    @OnClick(R.id.tv_count)
    public void clickCount(View view) {
        switchPage(1);
    }

    @OnClick(R.id.tv_setting)
    public void clickSetting(View view) {
        switchPage(2);
    }


    private void switchPage(int flag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        hideFragment(fragmentTransaction);
        setColor(flag);

        switch (flag) {
            case 0:
                if (fragment_daka != null) {
                    fragmentTransaction.show(fragment_daka);
                } else {
                    fragment_daka = new DakaFragment();
                    fragmentTransaction.add(R.id.framelayout, fragment_daka);
                }
                break;
            case 1:
                if (fragment_count != null) {
                    fragmentTransaction.show(fragment_count);
                } else {
                    fragment_count = new CountFragment();
                    fragmentTransaction.add(R.id.framelayout, fragment_count);
                }
                break;
            case 2:
                if (fragment_setting != null) {
                    fragmentTransaction.show(fragment_setting);
                } else {
                    fragment_setting = new SettingFragment();
                    fragmentTransaction.add(R.id.framelayout, fragment_setting);
                }
                break;
            default:
                break;
        }
        fragmentTransaction.commit();
    }

    private void setColor(int flag) {
        switch (flag) {
            case 0:
                tv_daka.setTextColor(getResources().getColor(R.color.mine_bg));
                tv_count.setTextColor(getResources().getColor(R.color.dialog_message_text_color));
                tv_setting.setTextColor(getResources().getColor(R.color.dialog_message_text_color));

                view_daka.setVisibility(View.VISIBLE);
                view_count.setVisibility(View.INVISIBLE);
                view_setting.setVisibility(View.INVISIBLE);
                break;
            case 1:
                tv_count.setTextColor(getResources().getColor(R.color.mine_bg));
                tv_daka.setTextColor(getResources().getColor(R.color.dialog_message_text_color));
                tv_setting.setTextColor(getResources().getColor(R.color.dialog_message_text_color));

                view_count.setVisibility(View.VISIBLE);
                view_daka.setVisibility(View.INVISIBLE);
                view_setting.setVisibility(View.INVISIBLE);
                break;
            case 2:
                tv_setting.setTextColor(getResources().getColor(R.color.mine_bg));
                tv_daka.setTextColor(getResources().getColor(R.color.dialog_message_text_color));
                tv_count.setTextColor(getResources().getColor(R.color.dialog_message_text_color));

                view_count.setVisibility(View.INVISIBLE);
                view_daka.setVisibility(View.INVISIBLE);
                view_setting.setVisibility(View.VISIBLE);
                break;
        }
    }

    /**
     * 隐藏所有的Fragment
     *
     * @param ft
     */
    private void hideFragment(FragmentTransaction ft) {
        if (fragment_daka != null) {
            ft.hide(fragment_daka);
        }
        if (fragment_count != null) {
            ft.hide(fragment_count);
        }
        if (fragment_setting != null) {
            ft.hide(fragment_setting);
        }
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_kaoqin_main;
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        TextView tv_title = toolbar.findViewById(R.id.toolbar_title);
        TextView tv_time = toolbar.findViewById(R.id.toolbar_other);

        tv_title.setText("考勤");
        tv_time.setText(TimeUtils.getTime());

        tv_title.setTextColor(getResources().getColor(R.color.black));
        tv_time.setTextColor(getResources().getColor(R.color.black));
        toolbar.setBackgroundColor(getResources().getColor(R.color.white));
    }
}
