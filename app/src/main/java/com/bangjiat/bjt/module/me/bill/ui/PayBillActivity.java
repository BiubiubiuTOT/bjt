package com.bangjiat.bjt.module.me.bill.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.module.main.ui.activity.BaseColorToolBarActivity;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.fizzer.doraemon.passwordinputdialog.PassWordDialog;
import com.fizzer.doraemon.passwordinputdialog.impl.DialogCompleteListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class PayBillActivity extends BaseColorToolBarActivity {
    @BindView(R.id.tv_type)
    TextView tv_type;

    private List<String> list;
    private OptionsPickerView<String> pvModes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView() {
        list = new ArrayList<>();
        list.add("微信");
        list.add("支付宝");
//        list.add("建设银行0528");
//        list.add("中国银行1671");
//        list.add("账户余额");

        pvModes = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                tv_type.setText(list.get(options1));
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
        new PassWordDialog(this).setTitle("请输入交易密码")
                .setSubTitle("缴费金额")
                .setMoney("268.78").setCompleteListener(new DialogCompleteListener() {
            @Override
            public void dialogCompleteListener(String money, String pwd) {
                startActivity(new Intent(mContext, PayResultActivity.class));
            }
        }).show();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_pay_bill;
    }

    @Override
    protected String getTitleStr() {
        return "账单缴费";
    }
}
