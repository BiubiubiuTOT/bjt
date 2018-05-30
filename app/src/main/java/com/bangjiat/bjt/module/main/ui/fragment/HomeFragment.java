package com.bangjiat.bjt.module.main.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.BaseFragment;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.common.RefreshViewHolder;
import com.bangjiat.bjt.common.UpdateAppUtil;
import com.bangjiat.bjt.module.home.company.ui.AddOrSelectCompanyActivity;
import com.bangjiat.bjt.module.home.notice.beans.NoticeBean;
import com.bangjiat.bjt.module.home.notice.contract.NoticeContract;
import com.bangjiat.bjt.module.home.notice.presenter.NoticePresenter;
import com.bangjiat.bjt.module.home.notice.ui.AllNoticeActivity;
import com.bangjiat.bjt.module.home.notice.ui.NoticeItemActivity;
import com.bangjiat.bjt.module.home.scan.beans.QrCodeDataUser;
import com.bangjiat.bjt.module.home.scan.ui.OpenDoorCodeActivity;
import com.bangjiat.bjt.module.home.scan.ui.ScanActivity;
import com.bangjiat.bjt.module.home.visitor.ui.VisitorActivity;
import com.bangjiat.bjt.module.home.work.ui.WorkMainActivity;
import com.bangjiat.bjt.module.me.personaldata.beans.BuildUser;
import com.bangjiat.bjt.module.me.personaldata.beans.CompanyUserBean;
import com.bangjiat.bjt.module.me.personaldata.beans.SpaceUser;
import com.bangjiat.bjt.module.me.personaldata.beans.UserInfo;
import com.bangjiat.bjt.module.me.personaldata.beans.UserInfoBean;
import com.bangjiat.bjt.module.me.personaldata.contract.GetUserInfoContract;
import com.bangjiat.bjt.module.me.personaldata.presenter.GetUserInfoPresenter;
import com.bangjiat.bjt.module.secretary.contact.beans.ScanUser;
import com.bangjiat.bjt.module.secretary.contact.beans.SearchContactResult;
import com.bangjiat.bjt.module.secretary.contact.contract.SearchContactContract;
import com.bangjiat.bjt.module.secretary.contact.presenter.SearchContactPresenter;
import com.bangjiat.bjt.module.secretary.contact.view.ContactInfoActivity;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.orhanobut.logger.Logger;
import com.orm.SugarRecord;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bertsir.zbar.QrConfig;
import cn.bertsir.zbar.QrManager;
import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.bgabanner.BGALocalImageSize;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;


public class HomeFragment extends BaseFragment implements NoticeContract.View, SearchContactContract.View,
        BGARefreshLayout.BGARefreshLayoutDelegate, GetUserInfoContract.View {
    @BindView(R.id.banner_guide_content)
    BGABanner mContentBanner;
    @BindView(R.id.tv_content)
    TextView tv_content;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.rl_refresh)
    BGARefreshLayout mRefreshLayout;
    private GetUserInfoContract.Presenter userPresenter;

    private NoticeBean.SysNoticeListBean sysNoticeListBean;
    private NoticeContract.Presenter presenter;
    private SearchContactContract.Presenter searchPresenter;
    private String token;

    protected void initView() {
        presenter = new NoticePresenter(this);
        searchPresenter = new SearchContactPresenter(this);
        token = DataUtil.getToken(mContext);
        presenter.getAllNotice(token);

        userPresenter = new GetUserInfoPresenter(this);
        UserInfo userInfo = UserInfo.first(UserInfo.class);
        if (userInfo == null)
            userPresenter.getUserInfo(token);

        BGALocalImageSize localImageSize = new BGALocalImageSize(720, 1280, 320, 640);
        mContentBanner.setData(localImageSize, ImageView.ScaleType.CENTER_CROP,
                R.drawable.banner1,
                R.drawable.banner2,
                R.drawable.banner3,
                R.drawable.banner4);
        mContentBanner.setDelegate(new BGABanner.Delegate<ImageView, Integer>() {
            @Override
            public void onBannerItemClick(BGABanner banner, ImageView itemView, Integer model, int position) {
//                Toast.makeText(banner.getContext(), "点击了" + position, Toast.LENGTH_SHORT).show();
            }
        });

        mRefreshLayout.setDelegate(this);
        mRefreshLayout.setRefreshViewHolder(new RefreshViewHolder(mContext, false));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @OnClick(R.id.tv_more_notice)
    public void clickMoreNotice(View view) {
        startActivity(new Intent(mContext, AllNoticeActivity.class));
    }

    private void update() {
        UpdateAppUtil appUtil = new UpdateAppUtil(mContext);
        appUtil.checkVersion();
    }

    @OnClick(R.id.tv_content)
    public void clickContent(View view) {
        if (sysNoticeListBean != null) {
            Intent intent = new Intent(mContext, NoticeItemActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("data", sysNoticeListBean);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    @OnClick(R.id.tv_visitor)
    public void clickVisitor(View view) {
        startActivity(new Intent(mContext, Constants.isIntoCompany() ? VisitorActivity.class : AddOrSelectCompanyActivity.class));
    }

    @OnClick(R.id.iv_scan)
    public void clickScan(View view) {
        startScan();
    }

    @OnClick(R.id.tv_open_door)
    public void clickOpenDoor(View view) {
        startActivity(new Intent(mContext, Constants.isIntoCompany() ?
                OpenDoorCodeActivity.class : AddOrSelectCompanyActivity.class));
    }

    @OnClick(R.id.tv_work)
    public void clickWork(View view) {
        startActivity(new Intent(mContext, Constants.isIntoCompany() ? WorkMainActivity.class : AddOrSelectCompanyActivity.class));
    }


    private void startScan() {
        Logger.d("startScan");

        QrConfig options = new QrConfig.Builder().create();
        QrManager.getInstance().init(options).startScan(new QrManager.OnScanResultCallback() {
            @Override
            public void onScanSuccess(String result) {
                try {
                    QrCodeDataUser user = new Gson().fromJson(result, QrCodeDataUser.class);
                    if (user != null)
                        searchPresenter.searchContact(DataUtil.getToken(mContext), user.getUn());
                    else {
                        fail("");
                    }
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                    fail("");
                }
            }
        });

        Intent intent = new Intent(mContext, ScanActivity.class);
        intent.putExtra(QrConfig.EXTRA_THIS_CONFIG, options);
        startActivity(intent);
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void dismissDialog() {

    }

    @Override
    public void getAllNoticeResult(NoticeBean bean) {
        mRefreshLayout.endRefreshing();
        update();
        if (bean != null) {
            List<NoticeBean.SysNoticeListBean> sysNoticeList = bean.getSysNoticeList();
            if (sysNoticeList != null && sysNoticeList.size() > 0) {
                sysNoticeListBean = sysNoticeList.get(0);
                tv_title.setText(sysNoticeListBean.getName());
                tv_content.setText(sysNoticeListBean.getContent());
            }
        }
    }

    @Override
    public void showError(String err) {
        mRefreshLayout.endRefreshing();
    }

    @Override
    public void fail(String err) {
        Constants.showErrorDialog(mContext, "二维码识别失败");
    }

    @Override
    public void success(SearchContactResult bean) {
        Intent intent = new Intent(mContext, ContactInfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", bean);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void getContactByScanSuccess(ScanUser user) {

    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout bgaRefreshLayout) {
        Constants.deleteDb();
        bgaRefreshLayout.beginRefreshing();
        getData();
    }

    private void getData() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    sleep(1500);
                    presenter.getAllNotice(token);
                    userPresenter.getUserInfo(token);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout bgaRefreshLayout) {
        return false;
    }

    @Override
    public void getUserInfoFail(String err) {
        Toast.makeText(mContext, err, Toast.LENGTH_LONG).show();
    }


    @Override
    public void getUserInfoSuccess(UserInfoBean bean) {
        Logger.d(bean);

        UserInfo userInfo = bean.getUserInfo();
        CompanyUserBean companyUser = bean.getCompanyUser();
        List<BuildUser> buildUser = bean.getBuildUser();
        List<SpaceUser> spaceUser = bean.getSpaceUser();

        DataUtil.setPhone(mContext, userInfo.getPhone());
        DataUtil.setUserId(mContext, userInfo.getUserId());

        userInfo.save();
        if (companyUser != null)
            companyUser.save();

        if (buildUser != null && buildUser.size() > 0) {
            Logger.d(buildUser.toString());
            SugarRecord.saveInTx(buildUser);
        }

        if (spaceUser != null && spaceUser.size() > 0) {
            Logger.d(spaceUser.toString());
            SugarRecord.saveInTx(spaceUser);
        }
    }
}

