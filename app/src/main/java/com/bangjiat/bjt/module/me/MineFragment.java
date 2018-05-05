package com.bangjiat.bjt.module.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.BaseFragment;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.common.WCBMenu;
import com.bangjiat.bjt.common.WcbBean;
import com.bangjiat.bjt.module.main.account.ui.LoginActivity;
import com.bangjiat.bjt.module.main.ui.activity.AboutActivity;
import com.bangjiat.bjt.module.main.ui.activity.ContactServiceActivity;
import com.bangjiat.bjt.module.me.bill.ui.MyBillActivity;
import com.bangjiat.bjt.module.me.feedback.ui.FeedBackActivity;
import com.bangjiat.bjt.module.me.personaldata.beans.UserInfo;
import com.bangjiat.bjt.module.me.personaldata.beans.UserInfoBean;
import com.bangjiat.bjt.module.me.personaldata.contract.GetUserInfoContract;
import com.bangjiat.bjt.module.me.personaldata.presenter.GetUserInfoPresenter;
import com.bangjiat.bjt.module.me.personaldata.ui.PersonalDataActivity;
import com.bangjiat.bjt.module.me.personaldata.ui.PersonalHeadActivity;
import com.bangjiat.bjt.module.me.setting.ui.SettingActivity;
import com.bangjiat.bjt.module.secretary.contact.util.GlideCircleTransform;
import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;


public class MineFragment extends BaseFragment implements GetUserInfoContract.View {
    @BindView(R.id.iv_icon)
    ImageView iv_icon;
    private List<WcbBean> mList;
    private GetUserInfoContract.Presenter presenter;
    private UserInfo userInfo;
    @BindView(R.id.tv_name)
    TextView tv_name;
    private static final int EDIT = 2;

    @Override
    protected void initView() {
        Glide.with(mContext).load("http://img5.duitang.com/uploads/item/201611/18/20161118090311_Cw2dU.jpeg").centerCrop().
                transform(new GlideCircleTransform(mContext)).into(iv_icon);
        presenter = new GetUserInfoPresenter(this);
        mList = new ArrayList<>();
        mList.add(new WcbBean("确定"));

        userInfo = UserInfo.first(UserInfo.class);
        if (userInfo != null)
            tv_name.setText(userInfo.getNickname());
        else presenter.getUserInfo(DataUtil.getToken(mContext));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_mine;
    }

    @OnClick(R.id.ll_service)
    public void clickService(View view) {
        startActivity(new Intent(mContext, ContactServiceActivity.class));
    }

    @OnClick(R.id.ll_setting)
    public void clickSetting(View view) {
        startActivity(new Intent(mContext, SettingActivity.class));
    }

    @OnClick(R.id.ll_feed_back)
    public void clickFeedBack(View view) {
        startActivity(new Intent(mContext, FeedBackActivity.class));
    }

    @OnClick(R.id.iv_icon)
    public void clickIcon(View view) {
        startActivity(new Intent(mContext, PersonalHeadActivity.class));
    }

    @OnClick(R.id.ll_bill)
    public void clickBill(View view) {
        startActivity(new Intent(mContext, MyBillActivity.class));
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
        Intent intent = new Intent(mContext, PersonalDataActivity.class);
        Bundle bundle = new Bundle();
        if (userInfo != null)
            bundle.putSerializable("data", userInfo);
        intent.putExtras(bundle);
        startActivityForResult(intent, EDIT);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == EDIT) {
                userInfo = (UserInfo) data.getSerializableExtra("data");
                tv_name.setText(userInfo.getNickname());
                UserInfo.delete(UserInfo.class);
                userInfo.save();
            }
        }
    }

    @Override
    public void getUserInfoSuccess(UserInfoBean bean) {
        Logger.d(bean.toString());
        userInfo = bean.getUserInfo();
        DataUtil.setPhone(mContext, userInfo.getPhone());
        DataUtil.setUserId(mContext, userInfo.getUserId());
        tv_name.setText(userInfo.getNickname());

        userInfo.save();
        UserInfoBean.CompanyUserBean companyUser = bean.getCompanyUser();
        companyUser.save();
    }
}

