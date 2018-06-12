package com.bangjiat.bjt.module.me;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.BaseFragment;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.common.WCBMenu;
import com.bangjiat.bjt.common.WcbBean;
import com.bangjiat.bjt.module.main.account.ui.LoginActivity;
import com.bangjiat.bjt.module.main.ui.activity.AboutActivity;
import com.bangjiat.bjt.module.main.ui.activity.ContactServiceActivity;
import com.bangjiat.bjt.module.me.bill.ui.MyBillActivity;
import com.bangjiat.bjt.module.me.feedback.ui.FeedBackActivity;
import com.bangjiat.bjt.module.me.personaldata.beans.CompanyUserBean;
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

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class MineFragment extends BaseFragment implements GetUserInfoContract.View {
    @BindView(R.id.iv_icon)
    ImageView iv_icon;
    private List<WcbBean> mList;
    private GetUserInfoContract.Presenter presenter;
    private UserInfo userInfo;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_des)
    TextView tv_des;

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);

        presenter = new GetUserInfoPresenter(this);
        mList = new ArrayList<>();
        mList.add(new WcbBean("确定"));

        userInfo = UserInfo.first(UserInfo.class);
        if (userInfo != null) {
            setText(userInfo);
//            tv_des.setText("");
        } else presenter.getUserInfo(DataUtil.getToken(mContext));
    }

    private void showIcon(UserInfo userInfo) {
        if (userInfo.getAvatar() != null) {
            Glide.with(mContext).load(userInfo.getAvatar()).centerCrop().placeholder(R.mipmap.my_head).
                    transform(new GlideCircleTransform(mContext)).into(iv_icon);
        }
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
        startActivity(intent);
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
                        Constants.deleteDb();
                        DataUtil.clearOtherSetting(mContext);
                        PushServiceFactory.getCloudPushService().unbindAccount(new CommonCallback() {
                            @Override
                            public void onSuccess(String s) {
                                Logger.d("阿里云解绑成功");
                            }

                            @Override
                            public void onFailed(String s, String s1) {
                                Logger.d("阿里云解绑失败： " + s + " " + s1);
                            }
                        });

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
        userInfo = bean.getUserInfo();
        CompanyUserBean companyUser = bean.getCompanyUser();
        DataUtil.setPhone(mContext, userInfo.getPhone());
        DataUtil.setUserId(mContext, userInfo.getUserId());

        setText(userInfo);

        userInfo.save();
        if (companyUser != null)
            companyUser.save();
    }

    private void setText(UserInfo userInfo) {
        String nickname = userInfo.getNickname();
        if (nickname != null) {
            tv_name.setText(nickname);
        } else {
            tv_name.setText("点击修改个人信息");
        }
        String avatar = userInfo.getAvatar();
        if (avatar != null) {
            showIcon(userInfo);
        } else {
            Glide.with(mContext).load(R.mipmap.my_head).centerCrop().
                    transform(new GlideCircleTransform(mContext)).into(iv_icon);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(UserInfo s) {
        Logger.d(s.toString());
        setText(s);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

