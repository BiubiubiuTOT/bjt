package com.bangjiat.bjt.module.me.bill.ui;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.adorkable.iosdialog.AlertDialog;
import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.module.main.ui.activity.BaseColorToolBarActivity;
import com.bangjiat.bjt.module.me.bill.beans.PageBillBean;
import com.bangjiat.bjt.module.me.bill.beans.PayBillBean;
import com.bangjiat.bjt.module.park.pay.beans.ParkPayHistory;
import com.bangjiat.bjt.module.park.pay.beans.ParkingDetail;
import com.bangjiat.bjt.module.park.pay.beans.PayListResult;
import com.bangjiat.bjt.module.park.pay.contract.PayContract;
import com.bangjiat.bjt.module.park.pay.presenter.PayPresenter;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
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
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class PayBillActivity extends BaseColorToolBarActivity implements PayContract.View {
    @BindView(R.id.tv_type)
    TextView tv_type;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_money)
    TextView tv_money;

    private List<String> list;
    private OptionsPickerView<String> pvModes;
    private PageBillBean.RecordsBean bean;
    private PayContract.Presenter presenter;
    int type;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView() {
        presenter = new PayPresenter(this);
        bean = (PageBillBean.RecordsBean) getIntent().getSerializableExtra("data");
        if (bean != null) {
            int type = bean.getType();
            if (type == 1) {
                tv_title.setText("房租");
            } else if (type == 2)
                tv_title.setText("物业费");

            tv_money.setText("¥ " + bean.getMoney());
        }

        list = new ArrayList<>();
        list.add("支付宝");
        list.add("微信");

        pvModes = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                tv_type.setText(list.get(options1));
                type = options1 + 1;
            }
        }).setSubmitColor(Color.BLACK).setCancelColor(Color.BLACK).build();
        pvModes.setPicker(list);
    }

    @OnClick(R.id.tv_type)
    public void clickType(View view) {
        pvModes.show();
    }

    @OnClick(R.id.btn_next)
    public void clickNext(View view) {
//        new PassWordDialog(this).setTitle("请输入交易密码")
//                .setSubTitle("缴费金额")
//                .setMoney("268.78").setCompleteListener(new DialogCompleteListener() {
//            @Override
//            public void dialogCompleteListener(String money, String pwd) {
//                startActivity(new Intent(mContext, PayResultActivity.class));
//            }
//        }).show();
        if (type != 0) {
            pay();
        } else {
            fail("请选择缴费方式");
        }
    }

    private void pay() {
        PayBillBean billBean = new PayBillBean(bean.getBillId(), bean.getMoney(), type);
        Logger.d(billBean.toString());

        presenter.payBill(DataUtil.getToken(mContext), billBean);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_pay_bill;
    }

    @Override
    protected String getTitleStr() {
        return "账单缴费";
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

    }

    @Override
    public void addPayInfoSuccess(String err) {

    }

    @Override
    public void getParkingDetailSuccess(ParkingDetail detail) {

    }

    @Override
    public void fail(String err) {
        Logger.e(err);
        Constants.showErrorDialog(mContext, err);
    }

    @Override
    public void payBillSuccess(String str) {
        Logger.d(str);

        if (type == 1) {
            alipay(str);
        } else wxpay(parseJson(str));
    }

    @Override
    public void getParkPayHistorySuccess(ParkPayHistory history) {

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
                showSuccess();
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

    private void showSuccess() {
        new AlertDialog(mContext).builder().setMsg("支付成功").setCancelable(false).
                setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setResult(RESULT_OK);
                        finish();
                    }
                }).show();
    }

    private void toast(String s) {
        Toast toast = Toast.makeText(getApplicationContext(),
                s, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
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
                showSuccess();
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
}
