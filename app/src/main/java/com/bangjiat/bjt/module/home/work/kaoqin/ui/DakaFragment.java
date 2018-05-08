package com.bangjiat.bjt.module.home.work.kaoqin.ui;

import android.app.Dialog;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.DistanceItem;
import com.amap.api.services.route.DistanceResult;
import com.amap.api.services.route.DistanceSearch;
import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.BaseFragment;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.common.WifiUtil;
import com.bangjiat.bjt.module.home.work.kaoqin.beans.DakaHistoryResult;
import com.bangjiat.bjt.module.home.work.kaoqin.beans.InDakaInput;
import com.bangjiat.bjt.module.home.work.kaoqin.beans.OutDakaInput;
import com.bangjiat.bjt.module.home.work.kaoqin.beans.RuleInput;
import com.bangjiat.bjt.module.home.work.kaoqin.contract.DakaContract;
import com.bangjiat.bjt.module.home.work.kaoqin.contract.RoleContract;
import com.bangjiat.bjt.module.home.work.kaoqin.presenter.DakaPresenter;
import com.bangjiat.bjt.module.home.work.kaoqin.presenter.RolePresenter;
import com.dou361.dialogui.DialogUIUtils;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;


public class DakaFragment extends BaseFragment implements RoleContract.View, DistanceSearch.OnDistanceSearchListener, DakaContract.View {
    @BindView(R.id.tv_in_time)
    TextView tv_in_time;
    @BindView(R.id.tv_in_remark)
    TextView tv_in_remark;
    @BindView(R.id.tv_out_time)
    TextView tv_out_time;
    @BindView(R.id.tv_daka_text)
    TextView tv_daka_text;
    @BindView(R.id.tv_in_text)
    TextView tv_in_text;
    @BindView(R.id.tv_out_text)
    TextView tv_out_text;
    @BindView(R.id.tv_in_status)
    TextView tv_in_status;

    @BindView(R.id.tv_time)
    TextView tv_time;
    private Timer timer;
    private RuleInput input;
    private RoleContract.Presenter presenter;
    private int type;
    private String wifiName;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    private double latitude;
    private double longitude;
    private DistanceSearch distanceSearch;
    private DistanceSearch.DistanceQuery distanceQuery;
    private DakaContract.Presenter dakaPresenter;
    private String address;
    private Dialog dialog;

    @OnClick(R.id.ll_daka)
    public void clickDaka(View view) {
        WifiUtil util = new WifiUtil(mContext);
        wifiName = util.getWifiName();

        if (!wifiName.equals(input.getWifiName())) {
            error("当前连接wifi与设置wifi不符，打卡失败");
            return;
        }

        String workDay = input.getWorkDay();
        int i = Constants.dayOfWeek();
        Logger.d("dayOfWeek:" + i);
        if (!workDay.contains(String.valueOf(i))) {
            error("今天不上班");
            return;
        }

        distance();
    }

    private void distance() {
        Logger.d(input.toString());
        LatLonPoint start = new LatLonPoint(Double.parseDouble(input.getLatitude()), Double.parseDouble(input.getLongitude()));
        LatLonPoint end = new LatLonPoint(latitude, longitude);

        Logger.d("start: " + start + " end: " + end);

//设置起点和终点，其中起点支持多个
        List<LatLonPoint> latLonPoints = new ArrayList<>();
        latLonPoints.add(start);
        distanceQuery.setOrigins(latLonPoints);
        distanceQuery.setDestination(end);
        distanceQuery.setType(DistanceSearch.TYPE_DISTANCE);

        distanceSearch.calculateRouteDistanceAsyn(distanceQuery);
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        initMap();

        presenter = new RolePresenter(this);
        dakaPresenter = new DakaPresenter(this);
        String token = DataUtil.getToken(mContext);
        dakaPresenter.getDaka(token, "", "");
        input = RuleInput.first(RuleInput.class);
        if (input == null)
            presenter.getRole(token);
        else {
            setText();
        }

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(1);
            }
        }, 0, 1000);
    }

    private void initMap() {
        distanceQuery = new DistanceSearch.DistanceQuery();
        distanceSearch = new DistanceSearch(mContext);
        distanceSearch.setDistanceSearchListener(this);
        mLocationOption = new AMapLocationClientOption();
        mLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation.getErrorCode() == 0) {
                    latitude = aMapLocation.getLatitude();
                    longitude = aMapLocation.getLongitude();
                    address = aMapLocation.getAddress();
                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                }
            }
        };
        //初始化定位
        mLocationClient = new AMapLocationClient(mContext);
//设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);

        AMapLocationClientOption option = new AMapLocationClientOption();
        /**
         * 设置定位场景，目前支持三种场景（签到、出行、运动，默认无场景）
         */
        option.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
        if (null != mLocationClient) {
            mLocationClient.setLocationOption(option);
            //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
            mLocationClient.stopLocation();
            mLocationClient.startLocation();
        }
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        mLocationOption.setInterval(1000 * 60);
//给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
//启动定位
        mLocationClient.startLocation();
    }

    private void setText() {
        tv_in_time.setText("上班时间" + input.getInTime());
        tv_out_time.setText("下班时间" + input.getOutTime());

        if (Constants.isInDay("00:00", "12:00")) {
            tv_daka_text.setText("上班打卡");
            type = 0;
        } else {
            tv_daka_text.setText("下班打卡");
            type = 1;
        }
    }

    android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            tv_time.setText(Constants.getTime());
        }
    };

    @Override
    public void showErr(String err) {

    }

    @Override
    public void getRoleSuccess(RuleInput result) {
        if (result != null) {
            result.save();
            input = result;
            setText();

        } else {
            Toast.makeText(mContext, "请先设置考勤规则", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void saveRoleSuccess() {

    }

    @Override
    public void updateSuccess() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null)
            timer.cancel();
        mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
        mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
        EventBus.getDefault().unregister(mContext);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSettingEvent(String s) {
        input = RuleInput.first(RuleInput.class);
        Logger.d("onSettingEvent: " + input.toString());
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_daka;
    }

    @Override
    public void onDistanceSearched(DistanceResult distanceResult, int i) {
        if (i == 1000) {
            List<DistanceItem> distanceResults = distanceResult.getDistanceResults();
            float distance = distanceResults.get(0).getDistance();
            Logger.d(distance);
            if (distance > 200) {
                error("当前位置与设置位置超过200米距离，打卡失败");
            } else {
                String token = DataUtil.getToken(mContext);
                if (type == 0) {
                    InDakaInput inDakaInput = new InDakaInput();
                    inDakaInput.setInAddress(address);
                    inDakaInput.setInLatitude(String.valueOf(latitude));
                    inDakaInput.setInLongitude(String.valueOf(longitude));
                    inDakaInput.setInType(getInType());
                    inDakaInput.setInWay("1");
                    dakaPresenter.inDaka(token, inDakaInput);

                } else {
                    OutDakaInput outDakaInput = new OutDakaInput();
                    outDakaInput.setOutAddress(address);
                    outDakaInput.setOutLatitude(String.valueOf(latitude));
                    outDakaInput.setOutLongitude(String.valueOf(longitude));
                    outDakaInput.setOutType(getOutType());
                    outDakaInput.setOutWay("1");
                    outDakaInput.setClockId("");
                    dakaPresenter.outDaka(token, outDakaInput);
                }
            }
        }
    }

    private String getOutType() {
        if (Constants.isInDay(input.getOutTime(), "24:00"))
            return "1";
        else return "2";
    }

    private String getInType() {
        if (Constants.isInDay(input.getInTime(), "12:00"))
            return "2";
        else {
            return "1";
        }
    }

    @Override
    public void showDialog() {
        dialog = DialogUIUtils.showLoadingVertical(mContext, "加载中").show();
    }

    @Override
    public void dismissDialog() {
        if (dialog != null)
            dialog.dismiss();
    }

    @Override
    public void error(String err) {
        Toast.makeText(mContext, err, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void inDakaSuccess() {
        tv_in_text.setText("打卡时间： " + tv_time.getText().toString());
        error("打卡成功");
    }

    @Override
    public void getDakaSuccess(List<DakaHistoryResult> result) {
        if (result != null) {

        }
    }

    @Override
    public void outDakaSuccess() {
        tv_out_text.setText("打卡时间： " + tv_time.getText().toString());
        error("打卡成功");
    }
}

