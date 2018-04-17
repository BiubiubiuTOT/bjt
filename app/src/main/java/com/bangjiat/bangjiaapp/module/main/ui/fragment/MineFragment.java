package com.bangjiat.bangjiaapp.module.main.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.bangjiat.bangjiaapp.R;
import com.bangjiat.bangjiaapp.common.BaseFragment;
import com.bangjiat.bangjiaapp.common.DataUtil;
import com.bangjiat.bangjiaapp.module.main.ui.activity.AboutActivity;
import com.bangjiat.bangjiaapp.module.main.ui.activity.ContactServiceActivity;
import com.bangjiat.bangjiaapp.module.main.ui.activity.FeedBackActivity;
import com.bangjiat.bangjiaapp.module.account.ui.LoginActivity;
import com.bangjiat.bangjiaapp.module.main.ui.activity.PersonalHeadActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;
import fv.galois.wcbmenu.WCBMenu;


public class MineFragment extends BaseFragment {
    private List<String> mList;

    protected void initView() {
        mList = new ArrayList<>();
        mList.add("确定");
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_mine;
    }

    @OnClick(R.id.ll_service)
    public void clickService(View view) {
        startActivity(new Intent(mContext, ContactServiceActivity.class));
    }

    @OnClick(R.id.ll_feed_back)
    public void clickFeedBack(View view) {
        startActivity(new Intent(mContext, FeedBackActivity.class));
    }

    @OnClick(R.id.iv_icon)
    public void clickIcon(View view) {
        startActivity(new Intent(mContext, PersonalHeadActivity.class));
    }

    @OnClick(R.id.ll_about)
    public void clickAbout(View view) {
        startActivity(new Intent(mContext, AboutActivity.class));
    }

    @OnClick(R.id.tv_exit)
    public void clickExit(View view) {
        showExitDialog();
    }

    private void showExitDialog() {
        final WCBMenu wcbMenu = new WCBMenu(mContext);
        wcbMenu.setTitle("确定要退出登录吗？")
                .setCancel("取消")
                .setStringList(mList)
                .setItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        wcbMenu.dismiss();
                        DataUtil.setLogin(mContext, false);
                        startActivity(new Intent(mContext, LoginActivity.class));
                        getActivity().finish();
                    }
                })
                .show();
    }
}

