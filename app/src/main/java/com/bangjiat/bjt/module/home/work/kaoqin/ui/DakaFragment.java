package com.bangjiat.bjt.module.home.work.kaoqin.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Message;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import com.bangjiat.bjt.common.TimeUtils;
import com.bangjiat.bjt.common.WifiUtil;
import com.bangjiat.bjt.module.home.work.kaoqin.beans.DakaHistoryResult;
import com.bangjiat.bjt.module.home.work.kaoqin.beans.DakaTotalResult;
import com.bangjiat.bjt.module.home.work.kaoqin.beans.InDakaInput;
import com.bangjiat.bjt.module.home.work.kaoqin.beans.OutDakaInput;
import com.bangjiat.bjt.module.home.work.kaoqin.beans.RuleInput;
import com.bangjiat.bjt.module.home.work.kaoqin.contract.ClockContract;
import com.bangjiat.bjt.module.home.work.kaoqin.contract.DakaContract;
import com.bangjiat.bjt.module.home.work.kaoqin.contract.RoleContract;
import com.bangjiat.bjt.module.home.work.kaoqin.presenter.ClockPresenter;
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


public class DakaFragment extends BaseFragment implements RoleContract.View, DistanceSearch.OnDistanceSearchListener,
        DakaContract.View, ClockContract.View {
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
    @BindView(R.id.tv_out_status)
    TextView tv_out_status;
    @BindView(R.id.tv_out_remark)
    TextView tv_out_remark;

    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.ll_daka)
    LinearLayout ll_daka;

    private Timer timer;
    private RuleInput input;
    private RoleContract.Presenter presenter;
    private int type;
    private String way;
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
    private ClockContract.Presenter clockPresenter;
    private String address;
    private Dialog dialog;
    private String token;
    private String clockId;
    private DakaHistoryResult bean;

    @OnClick(R.id.ll_daka)
    public void clickDaka(View view) {
        if (input != null) {
            WifiUtil util = new WifiUtil(mContext);
            wifiName = util.getWifiName();

            if (!wifiName.equals(input.getWifiName())) {//连接wifi为配置wifi则直接打卡，否则判断距离
                way = "2";//流量
                distance();
            } else {
                way = "1";//wifi
                daka();
            }

        } else {
            error("请先设置考勤");
        }
    }

    private void distance() {
        Logger.d(input.toString());
        if (input.getLatitude() == null) {
            error("请先设置办公地点");
            return;
        }
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
        clockPresenter = new ClockPresenter(this);
        token = DataUtil.getToken(mContext);
        input = RuleInput.first(RuleInput.class);
        if (input == null) {
            presenter.getRole(token);
        } else {
            setText();
        }
        clockPresenter.getClockList(token, TimeUtils.getBeginOfDay(), System.currentTimeMillis());

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
            error("请先设置考勤规则");
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

        tv_in_time.setText("上班时间" + input.getInTime());
        tv_out_time.setText("下班时间" + input.getOutTime());

        if (bean == null) {
            if (Constants.isInDay("00:00", "12:00")) {
                tv_daka_text.setText("上班打卡");
                type = 0;
            } else {
                tv_daka_text.setText("下班打卡");
                type = 1;
            }
        }
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
                showWaiqinDia();
            } else {
                daka();
            }
        }
    }

    private void daka() {
        String token = DataUtil.getToken(mContext);
        if (type == 0) {
            String inType = getInType();
            if (inType.equals("2")) {
                showLateDia();
            } else {
                InDakaInput inDakaInput = new InDakaInput();
                inDakaInput.setInAddress(address);
                inDakaInput.setInLatitude(String.valueOf(latitude));
                inDakaInput.setInLongitude(String.valueOf(longitude));
                inDakaInput.setInType(inType);
                inDakaInput.setInWay(way);
                dakaPresenter.inDaka(token, inDakaInput);
            }
        } else {
            String outType = getOutType();
            if (outType.equals("2")) {
                showLeaveDia();
            } else {
                OutDakaInput outDakaInput = new OutDakaInput();
                outDakaInput.setOutAddress(address);
                outDakaInput.setOutLatitude(String.valueOf(latitude));
                outDakaInput.setOutLongitude(String.valueOf(longitude));
                outDakaInput.setOutType(outType);
                outDakaInput.setOutWay(way);
                if (clockId != null) {
                    outDakaInput.setClockId(clockId);
                } else outDakaInput.setClockId("");

                Logger.d(outDakaInput.toString());
                dakaPresenter.outDaka(token, outDakaInput);
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
        Logger.d(err);
        Constants.showErrorDialog(mContext, err);
    }

    @Override
    public void getAllClockListSuccess(List<DakaHistoryResult> results) {

    }

    @Override
    public void getClockListSuccess(List<DakaHistoryResult> results) {
        if (results != null) {
            if (results.size() > 0) {
                bean = results.get(0);
                clockId = bean.getClockId();
                Logger.d(bean.toString());
                int inType = bean.getInType();
                int outType = bean.getOutType();
                if (inType != 0) {
                    tv_in_status.setVisibility(View.VISIBLE);
                    if (inType == 1) {
                        tv_in_status.setText("正常");
                    } else if (inType == 2) {
                        tv_in_status.setText("迟到");
                        tv_in_remark.setText(bean.getLateRemark());
                    } else {
                        tv_in_status.setText("外勤");
                        tv_in_remark.setText(bean.getLateRemark());
                    }

                    tv_in_text.setText("打卡时间" + TimeUtils.changeToHM(bean.getInTime()));
                    tv_daka_text.setText("下班打卡");
                    type = 1;
                } else {
                    if (Constants.isInDay("00:00", "12:00")) {
                        tv_daka_text.setText("上班打卡");
                        type = 0;
                    } else {
                        tv_daka_text.setText("下班打卡");
                        type = 1;
                    }
                }

                if (outType != 0) {
                    tv_out_text.setText(TimeUtils.changeToHM(bean.getOutTime()));

                    tv_out_status.setVisibility(View.VISIBLE);
                    if (outType == 1) {
                        tv_out_status.setText("正常");
                    } else if (outType == 2) {
                        tv_out_status.setText("早退");
                        tv_out_remark.setText(bean.getLeaveRemark());
                    } else {
                        tv_out_status.setText("外勤");
                        tv_out_remark.setText(bean.getLeaveRemark());
                    }
                    tv_out_text.setText("打卡时间" + TimeUtils.changeToHM(bean.getOutTime()));
                    ll_daka.setVisibility(View.GONE);
                }

            }
        }

    }

    @Override
    public void getUserClockListSuccess(List<DakaHistoryResult> results) {

    }

    @Override
    public void getClockTotalSuccess(DakaTotalResult result) {

    }

    @Override
    public void getUserClockTotalSuccess(DakaTotalResult result) {

    }

    @Override
    public void inDakaSuccess() {
        showDia();
    }

    // 播放默认铃声
    // 返回Notification id
    public void playSound(final Context context) {
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone r = RingtoneManager.getRingtone(context, notification);
        r.play();
    }


    private void showDia() {
        playSound(mContext);
        new com.adorkable.iosdialog.AlertDialog(mContext).builder().setMsg("打卡成功").setCancelable(false).
                setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        clockPresenter.getClockList(token, TimeUtils.getBeginOfDay(), System.currentTimeMillis());
                    }
                }).show();
    }

    @Override
    public void outDakaSuccess() {
        showDia();
    }

    private void showWaiqinDia() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        View view = LayoutInflater.from(mContext).inflate(R.layout.leave_layout, null);
        Button btn_cancel = view.findViewById(R.id.btn_cancel);
        Button btn_submit = view.findViewById(R.id.btn_submit);
        final EditText et_msg = view.findViewById(R.id.et_msg);
        TextView tv_time = view.findViewById(R.id.tv_time);
        TextView tv_address = view.findViewById(R.id.tv_address);
        TextView tv_title = view.findViewById(R.id.txt_title);
        tv_time.setText("打卡时间：" + Constants.getTime());
        tv_address.setText("打卡地点：" + wifiName);
        tv_title.setText("当前位置不在打卡范围内，是否外勤打卡？");


        builder.setCancelable(false)
                .setView(view);
        final AlertDialog alertDialog = builder.create();

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (alertDialog.isShowing())
                    alertDialog.dismiss();
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string = et_msg.getText().toString();

                if (alertDialog.isShowing())
                    alertDialog.dismiss();

                if (type == 0) {//早上外勤打卡
                    InDakaInput inDakaInput = new InDakaInput();
                    inDakaInput.setInAddress(address);
                    inDakaInput.setInLatitude(String.valueOf(latitude));
                    inDakaInput.setInLongitude(String.valueOf(longitude));
                    inDakaInput.setInType("3");
                    inDakaInput.setLateRemark(string);
                    inDakaInput.setInWay(way);
                    dakaPresenter.inDaka(token, inDakaInput);
                } else {
                    OutDakaInput outDakaInput = new OutDakaInput();
                    outDakaInput.setOutAddress(address);
                    outDakaInput.setOutLatitude(String.valueOf(latitude));
                    outDakaInput.setOutLongitude(String.valueOf(longitude));
                    outDakaInput.setOutType("3");
                    outDakaInput.setOutWay(way);
                    outDakaInput.setLeaveRemark(string);
                    if (clockId != null) {
                        outDakaInput.setClockId(clockId);
                    } else outDakaInput.setClockId("");

                    Logger.d(outDakaInput.toString());
                    dakaPresenter.outDaka(token, outDakaInput);
                }
            }
        });

        alertDialog.show();

        //设置大小
        WindowManager.LayoutParams layoutParams = alertDialog.getWindow().getAttributes();
        WindowManager m = getActivity().getWindowManager();
        Display d = m.getDefaultDisplay();
        layoutParams.width = (int) (d.getWidth() * 0.85);
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        alertDialog.getWindow().setAttributes(layoutParams);
    }

    private void showLeaveDia() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        View view = LayoutInflater.from(mContext).inflate(R.layout.leave_layout, null);
        Button btn_cancel = view.findViewById(R.id.btn_cancel);
        Button btn_submit = view.findViewById(R.id.btn_submit);
        final EditText et_msg = view.findViewById(R.id.et_msg);
        TextView tv_time = view.findViewById(R.id.tv_time);
        TextView tv_address = view.findViewById(R.id.tv_address);
        tv_time.setText("打卡时间：" + Constants.getTime());
        tv_address.setText("打卡地点：" + wifiName);


        builder.setCancelable(false)
                .setView(view);
        final AlertDialog alertDialog = builder.create();

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (alertDialog.isShowing())
                    alertDialog.dismiss();
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string = et_msg.getText().toString();

                if (alertDialog.isShowing())
                    alertDialog.dismiss();

                OutDakaInput outDakaInput = new OutDakaInput();
                outDakaInput.setOutAddress(address);
                outDakaInput.setOutLatitude(String.valueOf(latitude));
                outDakaInput.setOutLongitude(String.valueOf(longitude));
                outDakaInput.setOutType("2");
                outDakaInput.setOutWay(way);
                outDakaInput.setLeaveRemark(string);
                if (clockId != null) {
                    outDakaInput.setClockId(clockId);
                } else outDakaInput.setClockId("");


                Logger.d(outDakaInput.toString());
                dakaPresenter.outDaka(token, outDakaInput);
            }
        });

        alertDialog.show();

        //设置大小
        WindowManager.LayoutParams layoutParams = alertDialog.getWindow().getAttributes();
        WindowManager m = getActivity().getWindowManager();
        Display d = m.getDefaultDisplay();
        layoutParams.width = (int) (d.getWidth() * 0.85);
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        alertDialog.getWindow().setAttributes(layoutParams);
    }

    private void showLateDia() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        View view = LayoutInflater.from(mContext).inflate(R.layout.leave_layout, null);
        Button btn_cancel = view.findViewById(R.id.btn_cancel);
        Button btn_submit = view.findViewById(R.id.btn_submit);
        final EditText et_msg = view.findViewById(R.id.et_msg);
        TextView tv_title = view.findViewById(R.id.txt_title);
        TextView tv_time = view.findViewById(R.id.tv_time);
        TextView tv_address = view.findViewById(R.id.tv_address);
        tv_time.setText("打卡时间：" + Constants.getTime());
        tv_address.setText("打卡地点：" + wifiName);
        tv_title.setText("确定要打迟到卡吗？");


        builder.setCancelable(false)
                .setView(view);
        final AlertDialog alertDialog = builder.create();

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (alertDialog.isShowing())
                    alertDialog.dismiss();
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string = et_msg.getText().toString();

                if (alertDialog.isShowing())
                    alertDialog.dismiss();

                InDakaInput inDakaInput = new InDakaInput();
                inDakaInput.setInAddress(address);
                inDakaInput.setInLatitude(String.valueOf(latitude));
                inDakaInput.setInLongitude(String.valueOf(longitude));
                inDakaInput.setInType("2");
                inDakaInput.setLateRemark(string);
                inDakaInput.setInWay(way);
                dakaPresenter.inDaka(token, inDakaInput);
            }
        });

        alertDialog.show();
        //设置大小
        WindowManager.LayoutParams layoutParams = alertDialog.getWindow().getAttributes();
        WindowManager m = getActivity().getWindowManager();
        Display d = m.getDefaultDisplay();
        layoutParams.width = (int) (d.getWidth() * 0.85);
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        alertDialog.getWindow().setAttributes(layoutParams);
    }
}

