package com.bangjiat.bjt.module.main.ui.fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.BannerBean;
import com.bangjiat.bjt.common.BaseFragment;
import com.bangjiat.bjt.common.ComplexViewMF;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.common.RefreshViewHolder;
import com.bangjiat.bjt.common.UpdateAppUtil;
import com.bangjiat.bjt.module.home.company.ui.AddOrSelectCompanyActivity;
import com.bangjiat.bjt.module.home.company.ui.CompanyInfoActivity;
import com.bangjiat.bjt.module.home.notice.beans.NoticeBean;
import com.bangjiat.bjt.module.home.notice.contract.BannerContract;
import com.bangjiat.bjt.module.home.notice.contract.NoticeContract;
import com.bangjiat.bjt.module.home.notice.presenter.BannerPresenter;
import com.bangjiat.bjt.module.home.notice.presenter.NoticePresenter;
import com.bangjiat.bjt.module.home.notice.ui.AllNoticeActivity;
import com.bangjiat.bjt.module.home.notice.ui.NoticeItemActivity;
import com.bangjiat.bjt.module.home.scan.beans.QrCodeDataCompany;
import com.bangjiat.bjt.module.home.scan.beans.QrCodeDataUser;
import com.bangjiat.bjt.module.home.scan.ui.OpenDoorCodeActivity;
import com.bangjiat.bjt.module.home.scan.ui.ScanActivity;
import com.bangjiat.bjt.module.home.visitor.ui.VisitorActivity;
import com.bangjiat.bjt.module.home.work.ui.WorkMainActivity;
import com.bangjiat.bjt.module.main.ui.activity.WebActivity;
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
import com.bumptech.glide.Glide;
import com.gongwen.marqueen.MarqueeFactory;
import com.gongwen.marqueen.MarqueeView;
import com.gongwen.marqueen.util.OnItemClickListener;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.orhanobut.logger.Logger;
import com.orm.SugarRecord;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bertsir.zbar.QrConfig;
import cn.bertsir.zbar.QrManager;
import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.bgabanner.BGALocalImageSize;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;


public class HomeFragment extends BaseFragment implements NoticeContract.View, SearchContactContract.View,
        BGARefreshLayout.BGARefreshLayoutDelegate, GetUserInfoContract.View, BannerContract.View {
    @BindView(R.id.banner_guide_content)
    BGABanner mContentBanner;
    @BindView(R.id.rl_refresh)
    BGARefreshLayout mRefreshLayout;
    @BindView(R.id.iv_not_read)
    ImageView iv_not_read;
    @BindView(R.id.marqueeView)
    MarqueeView<LinearLayout, NoticeBean.SysNoticeListBean> marqueeView;

    MarqueeFactory<LinearLayout, NoticeBean.SysNoticeListBean> marqueeFactory;

    private GetUserInfoContract.Presenter userPresenter;

    private List<NoticeBean.SysNoticeListBean> sysNoticeListBean;
    private NoticeContract.Presenter presenter;
    private SearchContactContract.Presenter searchPresenter;
    private BannerContract.Presenter bannerPresenter;
    private String token;
    private List<String> bannerPath;
    private List<BannerBean> bannerBeans;
    private int type;
    private boolean isLoop;

    protected void initView() {
        EventBus.getDefault().register(this);
        presenter = new NoticePresenter(this);
        searchPresenter = new SearchContactPresenter(this);
        bannerPresenter = new BannerPresenter(this);
        token = DataUtil.getToken(mContext);

        marqueeFactory = new ComplexViewMF(mContext);
        marqueeView.setMarqueeFactory(marqueeFactory);
        marqueeView.setOnItemClickListener(new OnItemClickListener<LinearLayout, NoticeBean.SysNoticeListBean>() {
            @Override
            public void onItemClickListener(LinearLayout linearLayout, NoticeBean.SysNoticeListBean sysNoticeListBean, int i) {
                if (sysNoticeListBean.getCtime() != 0) {
                    Intent intent = new Intent(mContext, NoticeItemActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("data", sysNoticeListBean);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });

        sysNoticeListBean = NoticeBean.SysNoticeListBean.listAll(NoticeBean.SysNoticeListBean.class);
        if (sysNoticeListBean == null)
            presenter.getAllNotice(token);
        else if (sysNoticeListBean.size() == 0) {
            presenter.getAllNotice(token);
        } else setData(sysNoticeListBean);

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
        mContentBanner.setDelegate(new BGABanner.Delegate<ImageView, String>() {
            @Override
            public void onBannerItemClick(BGABanner banner, ImageView itemView, String model, int position) {
                if (bannerBeans.size() > 0) {
                    BannerBean bannerBean = bannerBeans.get(position);
//                    if (bannerBean.getType() == 1) { //广告，可以打开链接
                    Intent intent = new Intent(mContext, WebActivity.class);
                    intent.putExtra("url", bannerBean.getLink());
                    startActivity(intent);
//                    }
                }

            }
        });

        mRefreshLayout.setDelegate(this);
        mRefreshLayout.setRefreshViewHolder(new RefreshViewHolder(mContext, false));
        bannerPresenter.getBannerList(token);
    }

    private void setData(List<NoticeBean.SysNoticeListBean> sysNoticeListBean) {
        marqueeView.stopFlipping();
        if (sysNoticeListBean.size() == 0) {
            sysNoticeListBean = new ArrayList<>();
            NoticeBean.SysNoticeListBean e = new NoticeBean.SysNoticeListBean();
            e.setName("暂无公告信息");
            e.setContent("");
            sysNoticeListBean.add(e);
            isLoop = false;
        } else {
            isLoop = true;
        }

        marqueeFactory.setData(sysNoticeListBean);
        if (isLoop)
            marqueeView.startFlipping();
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
                    type = new JSONObject(result).getInt("type");
                    if (type == 2) {//好友二维码
                        QrCodeDataUser user = new Gson().fromJson(result, QrCodeDataUser.class);
                        if (user != null) {
                            searchPresenter.searchContact(DataUtil.getToken(mContext), user.getUn());
                        } else {
                            fail("");
                        }
                    } else if (type == 1) {//公司二维码
                        QrCodeDataCompany company = new Gson().fromJson(result, QrCodeDataCompany.class);
                        if (company != null) {
                            Intent intent = new Intent(mContext, CompanyInfoActivity.class);
                            intent.putExtra("data", result);
                            startActivity(intent);
                        } else {
                            fail("");
                        }
                    }
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                    fail("");
                } catch (JSONException e) {
                    fail("");
                    e.printStackTrace();
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
    public void onResume() {
        super.onResume();
        List<NoticeBean.SysNoticeListBean> list = NoticeBean.SysNoticeListBean.listAll(NoticeBean.SysNoticeListBean.class);
        if (list == null) return;

        List<NoticeBean.SysNoticeListBean> aTrue = NoticeBean.SysNoticeListBean.find(NoticeBean.SysNoticeListBean.class, "isRead=?", "1");
        if (aTrue == null) {
            iv_not_read.setVisibility(View.VISIBLE);
        } else {
            iv_not_read.setVisibility(list.size() == aTrue.size() ? View.GONE : View.VISIBLE);
        }

    }

    @Override
    public void getAllNoticeResult(NoticeBean bean) {
        mRefreshLayout.endRefreshing();
        List<NoticeBean.SysNoticeListBean> list = new ArrayList<>();
        update();
        if (bean != null) {
            List<NoticeBean.SysNoticeListBean> sysNoticeList = bean.getSysNoticeList();
            if (sysNoticeList != null && sysNoticeList.size() > 0) {
                list.addAll(sysNoticeList);
            }
            List<NoticeBean.SysNoticeListBean> buildNoticeList = bean.getBuildNoticeList();
            if (buildNoticeList != null && buildNoticeList.size() > 0) {
                list.addAll(buildNoticeList);
            }
            if (list.size() > 0) {
                Collections.sort(list, new Comparator<NoticeBean.SysNoticeListBean>() {
                    @Override
                    public int compare(NoticeBean.SysNoticeListBean o1, NoticeBean.SysNoticeListBean o2) {
                        if (o1.getCtime() < o2.getCtime()) {
                            return 1;
                        } else if (o1.getCtime() == o2.getCtime()) {
                            return 0;
                        }
                        return -1;
                    }
                });


                NoticeBean.SysNoticeListBean first = NoticeBean.SysNoticeListBean.first(NoticeBean.SysNoticeListBean.class);
                if (first != null) {
                    for (NoticeBean.SysNoticeListBean data : list) {
                        List<NoticeBean.SysNoticeListBean> sysNoticeListBeans = NoticeBean.SysNoticeListBean.
                                find(NoticeBean.SysNoticeListBean.class, "ctime=?", String.valueOf(data.getCtime()));
                        if (sysNoticeListBeans.isEmpty()) {
                            data.save();
                        }
                    }
                } else
                    SugarRecord.saveInTx(list);

                onResume();
            }
            setData(list);
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
                    bannerPresenter.getBannerList(token);
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
        Constants.deleteDb();

        UserInfo userInfo = bean.getUserInfo();
        CompanyUserBean companyUser = bean.getCompanyUser();
        List<BuildUser> buildUser = bean.getBuildUser();
        List<SpaceUser> spaceUser = bean.getSpaceUser();

        if (userInfo == null) return;

        DataUtil.setPhone(mContext, userInfo.getPhone());
        DataUtil.setUserId(mContext, userInfo.getUserId());

        userInfo.setPark(bean.getPark());
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

    @Override
    public void getBannerListSuccess(List<BannerBean> list) {
        bannerPath = new ArrayList<>();
        bannerBeans = new ArrayList<>();
        if (list != null && list.size() > 0) {
            Collections.sort(list, new Comparator<BannerBean>() {
                @Override
                public int compare(BannerBean o1, BannerBean o2) {
                    if (o1.getOrderValue() > o2.getOrderValue()) {
                        return 1;
                    }
                    return -1;
                }
            });

            Logger.d(list.toString());
            Logger.d("time: " + System.currentTimeMillis());
            for (BannerBean bannerBean : list) {
//                if (bannerBean.getStatus() == 1 && bannerBean.getShowStartTime() > System.currentTimeMillis() &&
//                        System.currentTimeMillis() < bannerBean.getShowEndTime()) {
                bannerPath.add(bannerBean.getImagePath());
                bannerBeans.add(bannerBean);
//                }
            }

            if (bannerBeans.size() > 0) {
                mContentBanner.setData(bannerPath, null);
                mContentBanner.setAdapter(new BGABanner.Adapter<ImageView, String>() {
                    @Override
                    public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
                        Glide.with(mContext)
                                .load(model)
                                .placeholder(R.drawable.holder)
                                .error(R.drawable.holder)
                                .centerCrop()
                                .dontAnimate()
                                .into(itemView);
                    }
                });
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(mContext);
    }

    @Override
    public void error(String err) {
        Logger.e(err);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSettingEvent(String s) {
        if (s.isEmpty()) return;

        Logger.d("收到消息推送： " + s);

        Resources res = getResources();
        String[] formats = res.getStringArray(R.array.push);
        List<String> list = Arrays.asList(formats);
        if (list.contains(s)) {
            presenter.getAllNotice(token);
            userPresenter.getUserInfo(token);
        }
    }
}

