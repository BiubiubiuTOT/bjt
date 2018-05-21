package com.bangjiat.bjt.module.secretary.contact.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.module.home.scan.beans.QrCodeDataUser;
import com.bangjiat.bjt.module.home.scan.ui.ScanActivity;
import com.bangjiat.bjt.module.main.ui.activity.BaseColorToolBarActivity;
import com.bangjiat.bjt.module.secretary.contact.beans.ScanUser;
import com.bangjiat.bjt.module.secretary.contact.beans.SearchContactResult;
import com.bangjiat.bjt.module.secretary.contact.contract.SearchContactContract;
import com.bangjiat.bjt.module.secretary.contact.presenter.SearchContactPresenter;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bertsir.zbar.QrConfig;
import cn.bertsir.zbar.QrManager;

public class AddContactActivity extends BaseColorToolBarActivity implements SearchContactContract.View {
    @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.tv_hint)
    TextView tv_hint;
    private SearchContactContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        presenter = new SearchContactPresenter(this);
        et_phone.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                tv_hint.setVisibility(View.GONE);
                presenter.searchContact(DataUtil.getToken(mContext), et_phone.getText().toString());
                return true;
            }
        });
    }

    @OnClick(R.id.ll_scan)
    public void clickScan(View view) {
        QrConfig options = new QrConfig.Builder().create();
        QrManager.getInstance().init(options).startScan(new QrManager.OnScanResultCallback() {
            @Override
            public void onScanSuccess(String result) {
                try {
                    QrCodeDataUser user = new Gson().fromJson(result, QrCodeDataUser.class);
                    if (user != null)
                        presenter.searchContact(DataUtil.getToken(mContext), user.getUn());
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
    protected int getLayoutResId() {
        return R.layout.activity_add_contact;
    }

    @Override
    protected String getTitleStr() {
        return "新建联系人";
    }


    @Override
    public void fail(String err) {
        tv_hint.setVisibility(View.VISIBLE);
    }

    @Override
    public void success(SearchContactResult bean) {
        Intent intent = new Intent(mContext, ContactInfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", bean);
        intent.putExtras(bundle);
        startActivity(intent);

        finish();
    }

    @Override
    public void getContactByScanSuccess(ScanUser user) {

    }
}
