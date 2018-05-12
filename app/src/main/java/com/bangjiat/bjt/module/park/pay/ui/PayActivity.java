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
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.common.TimeUtils;
import com.bangjiat.bjt.module.main.ui.activity.BaseWhiteToolBarActivity;
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
    private TimePickerView pvTime;
    private List<String> options1Items;
    private OptionsPickerView<String> pvModes;
    private OptionsPickerView<String> pvMonths;
    public Date date;
    private List<String> months;
    private int month = -1;
    private int payType;
    private Dialog dialog;
    private PayContract.Presenter presenter;
    private long beginTime;
    private long endTime;
    private String token;
    private String totalFee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView() {
        token = DataUtil.getToken(mContext);
        presenter = new PayPresenter(this);
        initMonths();
        options1Items = new ArrayList<>();
        options1Items.add("支付宝");
        options1Items.add("微信");
        initTimePicker();
        initMothPicker();
        initModePicker();
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

    private void initModePicker() {
        pvModes = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                payType = options1;
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
                PayActivity.this.month = i + 1;
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
        if (month != -1 && date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(calendar.MONTH, month);

            endTime = calendar.getTime().getTime();

            et_end_time.setText(TimeUtils.changeToYMD(endTime));
        }
    }

    @OnTouch(R.id.et_total_month)
    public boolean onTouchTotalMonth(View v, MotionEvent event) {
        pvMonths.show();
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

    @OnClick(R.id.btn_done)
    public void clickDone(View view) {
        if (month == -1) {
            fail("请选择缴纳月数");
            return;
        }
        if (date == null) {
            fail("请选择生效日期");
            return;
        }
        PayBean bean = new PayBean();
        totalFee = et_total_money.getText().toString();
        bean.setTotalFee(totalFee);
        bean.setPayWay(payType);
        bean.setBeginTime(beginTime);
        bean.setEndTime(endTime);
        bean.setMonths(month);
        bean.setMonthFee(tv_money_month.getText().toString());
        bean.setCarId(1);//车辆编号
        bean.setSpaceId(1);//停车场编号
        bean.setSpaceName("");//停车场名称
        bean.setPlateNumber("");//车牌号

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
                toast("支付成功");
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
    private void wxpay() {
        //实例化微信支付策略
        String wxAppId = "wx432ba0b2e3addde9";
        WXPay wxPay = WXPay.getInstance(this, wxAppId);
        //构造微信订单实体。一般都是由服务端直接返回。
        WXPayInfoImpli wxPayInfoImpli = new WXPayInfoImpli();
        wxPayInfoImpli.setTimestamp("");
        wxPayInfoImpli.setSign("");
        wxPayInfoImpli.setPrepayId("");
        wxPayInfoImpli.setPartnerid("");
        wxPayInfoImpli.setAppid("");
        wxPayInfoImpli.setNonceStr("");
        wxPayInfoImpli.setPackageValue("Sign=WXPay");
        //策略场景类调起支付方法开始支付，以及接收回调。
        EasyPay.pay(wxPay, this, wxPayInfoImpli, new IPayCallback() {
            @Override
            public void success() {
                toast("支付成功");
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
        } else
            dialog = DialogUIUtils.showLoadingVertical(mContext, "加载中").show();
    }

    @Override
    public void dismissDialog() {
        if (dialog != null)
            dialog.dismiss();
    }

    @Override
    public void getPayListSuccess(PayListResult str) {

    }

    @Override
    public void paySuccess(String str) {
        if (payType == 1) {
            alipay(str);
        } else wxpay();
    }

    @Override
    public void addPayInfoSuccess(String err) {
        PayInput input = new PayInput(err, totalFee);
        presenter.pay(token, input);
    }

    @Override
    public void fail(String err) {
        Logger.e(err);
    }
}
