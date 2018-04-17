package com.bangjiat.bangjiaapp.module.main.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bangjiat.bangjiaapp.R;
import com.bangjiat.bangjiaapp.common.BaseActivity;
import com.bangjiat.bangjiaapp.module.main.ui.fragment.HomeFragment;
import com.bangjiat.bangjiaapp.module.main.ui.fragment.MineFragment;
import com.bangjiat.bangjiaapp.module.main.ui.fragment.ParkFragment;
import com.bangjiat.bangjiaapp.module.main.ui.fragment.SecretaryFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 首页
 */
public class MainActivity extends BaseActivity {
    private Fragment fragment_home, fragment_secretary, fragment_park, fragment_mine;

    @BindView(R.id.tv_home)
    TextView tv_home;
    @BindView(R.id.tv_park)
    TextView tv_park;
    @BindView(R.id.tv_secretary)
    TextView tv_secretary;
    @BindView(R.id.tv_mine)
    TextView tv_mine;

    @BindView(R.id.iv_home)
    ImageView iv_home;
    @BindView(R.id.iv_park)
    ImageView iv_park;
    @BindView(R.id.iv_secretary)
    ImageView iv_secretary;
    @BindView(R.id.iv_mine)
    ImageView iv_mine;

    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        switchPage(0);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @OnClick(R.id.ll_home)
    public void clickHome(View view) {
        switchPage(0);
    }

    @OnClick(R.id.ll_park)
    public void clickPark(View view) {
        switchPage(1);
    }

    @OnClick(R.id.ll_secretary)
    public void clickSecretary(View view) {
        switchPage(2);
    }

    @OnClick(R.id.ll_mine)
    public void clickMine(View view) {
        switchPage(3);
    }


    private void switchPage(int flag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        hideFragment(fragmentTransaction);
        setColor(flag);

        switch (flag) {
            case 0:
                if (fragment_home != null) {
                    fragmentTransaction.show(fragment_home);
                } else {
                    fragment_home = new HomeFragment();
                    fragmentTransaction.add(R.id.framelayout, fragment_home);
                }
                break;
            case 1:
                if (fragment_park != null) {
                    fragmentTransaction.show(fragment_park);
                } else {
                    fragment_park = new ParkFragment();
                    fragmentTransaction.add(R.id.framelayout, fragment_park);
                }
                setColor(flag);
                break;
            case 2:
                if (fragment_secretary != null) {
                    fragmentTransaction.show(fragment_secretary);
                } else {
                    fragment_secretary = new SecretaryFragment();
                    fragmentTransaction.add(R.id.framelayout, fragment_secretary);
                }
                break;
            case 3:
                if (fragment_mine != null) {
                    fragmentTransaction.show(fragment_mine);
                } else {
                    fragment_mine = new MineFragment();
                    fragmentTransaction.add(R.id.framelayout, fragment_mine);
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
                tv_home.setTextColor(getResources().getColor(R.color.mine_bg));
                tv_mine.setTextColor(getResources().getColor(R.color.black));
                tv_park.setTextColor(getResources().getColor(R.color.black));
                tv_secretary.setTextColor(getResources().getColor(R.color.black));

                iv_home.setImageResource(R.mipmap.iv_home_1);
                iv_mine.setImageResource(R.mipmap.iv_mine);
                iv_park.setImageResource(R.mipmap.iv_park);
                iv_secretary.setImageResource(R.mipmap.iv_secretary);
                break;
            case 1:
                tv_park.setTextColor(getResources().getColor(R.color.mine_bg));
                tv_mine.setTextColor(getResources().getColor(R.color.black));
                tv_home.setTextColor(getResources().getColor(R.color.black));
                tv_secretary.setTextColor(getResources().getColor(R.color.black));

                iv_home.setImageResource(R.mipmap.iv_home);
                iv_mine.setImageResource(R.mipmap.iv_mine);
                iv_park.setImageResource(R.mipmap.iv_park_1);
                iv_secretary.setImageResource(R.mipmap.iv_secretary);
                break;
            case 2:
                tv_secretary.setTextColor(getResources().getColor(R.color.mine_bg));
                tv_mine.setTextColor(getResources().getColor(R.color.black));
                tv_park.setTextColor(getResources().getColor(R.color.black));
                tv_home.setTextColor(getResources().getColor(R.color.black));

                iv_home.setImageResource(R.mipmap.iv_home);
                iv_mine.setImageResource(R.mipmap.iv_mine);
                iv_park.setImageResource(R.mipmap.iv_park);
                iv_secretary.setImageResource(R.mipmap.iv_secretary_1);
                break;
            case 3:
                tv_mine.setTextColor(getResources().getColor(R.color.mine_bg));
                tv_home.setTextColor(getResources().getColor(R.color.black));
                tv_park.setTextColor(getResources().getColor(R.color.black));
                tv_secretary.setTextColor(getResources().getColor(R.color.black));

                iv_home.setImageResource(R.mipmap.iv_home);
                iv_mine.setImageResource(R.mipmap.iv_mine_1);
                iv_park.setImageResource(R.mipmap.iv_park);
                iv_secretary.setImageResource(R.mipmap.iv_secretary);
                break;
        }
    }

    /**
     * 隐藏所有的Fragment
     *
     * @param ft
     */
    private void hideFragment(FragmentTransaction ft) {
        if (fragment_home != null) {
            ft.hide(fragment_home);
        }
        if (fragment_mine != null) {
            ft.hide(fragment_mine);
        }
        if (fragment_park != null) {
            ft.hide(fragment_park);
        }
        if (fragment_secretary != null) {
            ft.hide(fragment_secretary);
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                exit();
                return true;
            default:
                return super.onKeyDown(keyCode, event);
        }
    }

    private void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }
}
