package com.bangjiat.bjt.module.park.pay.ui;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.common.TimeUtils;
import com.bangjiat.bjt.module.main.ui.activity.BaseWhiteToolBarActivity;
import com.bangjiat.bjt.module.park.pay.beans.ParkingDetail;
import com.bangjiat.bjt.module.park.pay.beans.PayBean;
import com.bangjiat.bjt.module.park.pay.beans.PayInput;
import com.bangjiat.bjt.module.park.pay.beans.PayListResult;
import com.bangjiat.bjt.module.park.pay.contract.PayContract;
import com.bangjiat.bjt.module.park.pay.presenter.PayPresenter;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.dou361.dialogui.DialogUIUtils;
import com.orhanobut.logger.Logger;
import com.xgr.easypay.EasyPay;
import com.xgr.easypay.alipay.AliPay;
import com.xgr.easypay.alipay.AlipayInfoImpli;
import com.xgr.easypay.callback.IPayCallback;
import com.xgr.easypay.wxpay.WXPay;
import com.xgr.easypay.wxpay.WXPayInfoImpli;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTouch;

public class PayActivity extends BaseWhiteToolBarActivity implements PayContract.View {
    @BindView(R.id.tv_parking)
    TextView tv_parking;
    @BindView(R.id.tv_car_number)
    TextView tv_car_number;
    @BindView(R.id.tv_money_month)
    TextView tv_money_month;

    @BindView(R.id.et_pay_mode)
    EditText et_pay_mode;
    @BindView(R.id.et_end_time)
    EditText et_end_time;
    @BindView(R.id.et_start_time)
    EditText et_start_time;
    @BindView(R.id.et_total_month)
    EditText et_total_month;
    @BindView(R.id.et_total_money)
    EditText et_total_money;
    @BindView(R.id.et_msg)
    EditText et_msg;
    @BindView(R.id.et_pay_type)
    EditText et_pay_type;
    @BindView(R.id.tv_des)
    TextView tv_des;
    @BindView(R.id.tv_money_des)
    TextView tv_money_des;

    private TimePickerView pvTime;
    private List<String> options1Items;
    private OptionsPickerView<String> pvModes;
    private OptionsPickerView<String> pvTypes;
    private OptionsPickerView<String> pvMonths;
    private OptionsPickerView<String> pvYears;
    public Date date;
    private List<String> months;
    private List<String> years;
    private List<String> types;
    private int number = -1;
    private int payMode;
    private int payType = -1;
    private Dialog dialog;
    private PayContract.Presenter presenter;
    private long beginTime;
    private long endTime;
    private String token;
    private String totalFee;
    private PayListResult result;
    private String monthFee;
    private ParkingDetail detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView() {
        result = (PayListResult) getIntent().getSerializableExtra("data");
        token = DataUtil.getToken(mContext);
        presenter = new PayPresenter(this);
        if (result != null) {
            tv_parking.setText(result.getSpaceName());
            tv_car_number.setText(result.getPlateNumber());
            presenter.getParkingDetail(token, result.getSpaceId());
        }

        initMonths();
        initTypes();
        initYears();
        options1Items = new ArrayList<>();
        options1Items.add("支付宝");
        options1Items.add("微信");
        initTimePicker();
        initMothPicker();
        initModePicker();
        initTypePicker();
        initYearPicker();
    }

    private void initTypePicker() {
        pvTypes = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                payType = options1 + 1;
                et_pay_type.setText(types.get(options1));

                if (options1 == 1) {
                    et_total_month.setHint("请选择交纳年数");
                    tv_des.setText("交纳年数");
                    tv_money_des.setText("每年费用");
                    tv_money_month.setText(detail.getYearFee());
                } else {
                    et_total_month.setHint("请选择交纳月数");
                    tv_des.setText("交纳月数");
                    tv_money_des.setText("每月费用");
                    tv_money_month.setText(detail.getMonthFee());
                }
                number = -1;
                et_total_month.setText("");
                et_end_time.setText("");
                et_total_money.setText("");
                et_start_time.setText("");
                date = null;
            }
        }).setSubmitColor(Color.BLACK)
                .setCancelColor(Color.BLACK).build();
        pvTypes.setPicker(types);
    }

    private void initMonths() {
        months = new ArrayList<>();
        months.add("一个月");
        months.add("二个月");
        months.add("三个月");
        months.add("四个月");
        months.add("五个月");
        months.add("六个月");
        months.add("七个月");
        months.add("八个月");
        months.add("九个月");
        months.add("十个月");
        months.add("十一个月");
        months.add("十二个月");
    }

    private void initYears() {
        years = new ArrayList<>();
        years.add("一年");
        years.add("两年");
        years.add("三年");
        years.add("四年");
        years.add("五年");
    }

    private void initTypes() {
        types = new ArrayList<>();
        types.add("月付费");
        types.add("年付费");
    }

    private void initModePicker() {
        pvModes = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                payMode = options1 + 1;
                et_pay_mode.setText(options1Items.get(options1));
            }
        }).setSubmitColor(Color.BLACK)
                .setCancelColor(Color.BLACK).build();
        pvModes.setPicker(options1Items);

    }

    private void initMothPicker() {
        pvMonths = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int i, int i1, int i2, View view) {
                PayActivity.this.number = i + 1;
                String s = months.get(i);
                et_total_month.setText(s);
                float n = Float.parseFloat(tv_money_month.getText().toString());
                et_total_money.setText(String.valueOf((i + 1) * n));

                setEnd();
            }
        })
                .setSubmitColor(Color.BLACK)
                .setCancelColor(Color.BLACK).build();
        pvMonths.setPicker(months);
    }

    private void initYearPicker() {
        pvYears = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int i, int i1, int i2, View view) {
                PayActivity.this.number = i + 1;
                String s = years.get(i);
                et_total_month.setText(s);
                float n = Float.parseFloat(tv_money_month.getText().toString());
                et_total_money.setText(String.valueOf((i + 1) * n));

                setEnd();
            }
        })
                .setSubmitColor(Color.BLACK)
                .setCancelColor(Color.BLACK).build();
        pvYears.setPicker(years);
    }

    private void initTimePicker() {
        pvTime = new TimePickerBuilder(mContext, new OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date, View v) {
                PayActivity.this.date = date;
                beginTime = date.getTime();

                setEnd();

                et_start_time.setText(TimeUtils.changeToYMD(beginTime));

            }
        }).setSubmitColor(Color.BLACK)
                .setCancelColor(Color.BLACK)
                .build();
    }

    private void setEnd() {
        if (number != -1 && date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            if (payType == 2) {
                calendar.add(calendar.MONTH, number * 12);
            } else
                calendar.add(calendar.MONTH, number);

            endTime = calendar.getTime().getTime();

            et_end_time.setText(TimeUtils.changeToYMD(endTime));
        }
    }

    @OnTouch(R.id.et_total_month)
    public boolean onTouchTotalMonth(View v, MotionEvent event) {
        if (payType == 2) {
            pvYears.show();
        } else pvMonths.show();
        return true;
    }

    @OnTouch(R.id.et_start_time)
    public boolean onTouchStartTime(View v, MotionEvent event) {
        pvTime.show();
        return true;
    }

    @OnTouch(R.id.et_pay_mode)
    public boolean onTouchPayMode(View v, MotionEvent event) {
        pvModes.show();
        return true;
    }

    @OnTouch(R.id.et_pay_type)
    public boolean onTouchPayType(View v, MotionEvent event) {
        pvTypes.show();
        return true;
    }

    @OnClick(R.id.btn_done)
    public void clickDone(View view) {

        if (payType == -1) {
            fail("请选择缴费方式");
            return;
        }
        if (number == -1) {
            if (payType == 2) {
                fail("请选择交纳年数");
            } else fail("请选择交纳月数");

            return;
        }
        if (date == null) {
            fail("请选择生效日期");
            return;
        }
        if (payMode == -1) {
            fail("请选择支付方式");
            return;

        }

        PayBean bean = new PayBean();
        totalFee = et_total_money.getText().toString();
        bean.setTotalFee(totalFee);
        bean.setPayWay(payMode);
        bean.setBeginTime(beginTime);
        bean.setEndTime(endTime);
        bean.setNumber(number);
        bean.setFee(monthFee);
        bean.setType(payType);
        bean.setCarId(result.getCarId());//车辆编号
        bean.setSpaceId(result.getSpaceId());//停车场编号
        bean.setSpaceName(result.getSpaceName());//停车场名称
        bean.setPlateNumber(result.getPlateNumber());//车牌号

        Logger.d(bean.toString());

        presenter.addPayInfo(token, bean);
    }

    /**
     * 支付宝支付
     */
    private void alipay(String s) {
        AliPay aliPay = new AliPay();
        //构造支付宝订单实体。一般都是由服务端直接返回。
        AlipayInfoImpli alipayInfoImpli = new AlipayInfoImpli();
        alipayInfoImpli.setOrderInfo(s);
        //策略场景类调起支付方法开始支付，以及接收回调。
        EasyPay.pay(aliPay, this, alipayInfoImpli, new IPayCallback() {
            @Override
            public void success() {
                Constants.showSuccessExitDialog(PayActivity.this, "支付成功");
            }

            @Override
            public void failed() {
                toast("支付失败");
            }

            @Override
            public void cancel() {
                toast("支付取消");
            }
        });
    }

    private void toast(String s) {
        Toast.makeText(mContext, s, Toast.LENGTH_SHORT).show();
    }

    /**
     * 微信支付
     */
    private void wxpay(WXPayInfoImpli wxPayInfoImpli) {
        String wxAppId = "wx432ba0b2e3addde9";
        WXPay wxPay = WXPay.getInstance(this, wxAppId);
        EasyPay.pay(wxPay, this, wxPayInfoImpli, new IPayCallback() {
            @Override
            public void success() {
                Constants.showSuccessExitDialog(PayActivity.this, "支付成功");
            }

            @Override
            public void failed() {
                toast("支付失败");
            }

            @Override
            public void cancel() {
                toast("支付取消");
            }
        });
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_pay;
    }

    @Override
    protected String getTitleStr() {
        return "停车缴费";
    }

    @Override
    public void showDialog() {
        if (dialog != null) {
            if (!dialog.isShowing())
                dialog.show();
        } else {
            dialog = DialogUIUtils.showLoadingVertical(mContext, "加载中").show();
            dialog.setCancelable(false);
        }
    }

    @Override
    public void dismissDialog() {
        if (dialog != null)
            dialog.dismiss();
    }

    @Override
    public void getPayListSuccess(List<PayListResult> str) {

    }

    @Override
    public void paySuccess(String str) {
        Logger.d(str);
        if (payMode == 1) {
            alipay(str);
        } else wxpay(parseJson(str));
    }

    private WXPayInfoImpli parseJson(String str) {
        WXPayInfoImpli orderInfo = new WXPayInfoImpli();
        try {
            String packageValue = new JSONObject(str).getString("package");
            String noncestr = new JSONObject(str).getString("noncestr");
            String partnerid = new JSONObject(str).getString("partnerid");
            String timestamp = new JSONObject(str).getString("timestamp");
            String prepayid = new JSONObject(str).getString("prepayid");
            String sign = new JSONObject(str).getString("sign");
            String appid = new JSONObject(str).getString("appid");

            orderInfo.setTimestamp(timestamp);
            orderInfo.setPackageValue(packageValue);
            orderInfo.setPrepayId(prepayid);
            orderInfo.setNonceStr(noncestr);
            orderInfo.setPartnerid(partnerid);
            orderInfo.setSign(sign);
            orderInfo.setAppid(appid);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return orderInfo;
    }

    @Override
    public void addPayInfoSuccess(String err) {
        PayInput input = new PayInput(err, totalFee);
        presenter.pay(token, input);
    }

    @Override
    public void getParkingDetailSuccess(ParkingDetail detail) {
        if (detail != null) {
            this.detail = detail;
            monthFee = detail.getMonthFee();
            tv_money_month.setText(monthFee);
        }
    }

    @Override
    public void fail(String err) {
        Logger.e(err);
        Constants.showErrorDialog(mContext, err);
    }
}
