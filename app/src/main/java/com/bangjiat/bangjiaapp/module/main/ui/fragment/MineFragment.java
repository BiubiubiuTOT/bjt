package com.bangjiat.bangjiaapp.module.main.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.bangjiat.bangjiaapp.R;
import com.bangjiat.bangjiaapp.common.BaseFragment;
import com.bangjiat.bangjiaapp.common.DataUtil;
import com.bangjiat.bangjiaapp.module.account.ui.LoginActivity;
import com.bangjiat.bangjiaapp.module.main.ui.activity.AboutActivity;
import com.bangjiat.bangjiaapp.module.main.ui.activity.ContactServiceActivity;
import com.bangjiat.bangjiaapp.module.main.ui.activity.FeedBackActivity;
import com.bangjiat.bangjiaapp.module.personaldata.ui.PersonalHeadActivity;
import com.bangjiat.bangjiaapp.module.personaldata.beans.UserInfoBean;
import com.bangjiat.bangjiaapp.module.personaldata.contract.GetUserInfoContract;
import com.bangjiat.bangjiaapp.module.personaldata.presenter.GetUserInfoPresenter;
import com.bangjiat.bangjiaapp.module.personaldata.ui.PersonalDataActivity;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;
import fv.galois.wcbmenu.WCBMenu;


public class MineFragment extends BaseFragment implements GetUserInfoContract.View {
    private List<String> mList;
    private GetUserInfoContract.Presenter presenter;


    @Override
    protected void initView() {
        presenter = new GetUserInfoPresenter(this);
        presenter.getUserInfo(DataUtil.getToken(mContext));
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

    @OnClick(R.id.ll_edit)
    public void clickEdit(View view) {
        startActivity(new Intent(mContext, PersonalDataActivity.class));
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

    @Override
    public void getUserInfoFail(String err) {
        Toast.makeText(mContext, err, Toast.LENGTH_LONG).show();
    }

    @Override
    public void getUserInfoSuccess(UserInfoBean bean) {
        Logger.d(bean.toString());

    }
}

