package com.bangjiat.bjt.module.secretary.contact.view;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.ClearEditText;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.common.KeyboardUtil;
import com.bangjiat.bjt.module.main.ui.activity.BaseWhiteToolBarActivity;
import com.bangjiat.bjt.module.secretary.contact.beans.SearchContactResult;
import com.bangjiat.bjt.module.secretary.contact.contract.SaveContactContract;
import com.bangjiat.bjt.module.secretary.contact.presenter.SaveContactPresenter;
import com.bangjiat.bjt.module.secretary.contact.util.GlideCircleTransform;
import com.bumptech.glide.Glide;
import com.dou361.dialogui.DialogUIUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

public class ContactInfoActivity extends BaseWhiteToolBarActivity implements SaveContactContract.View {
    @BindView(R.id.et_name)
    ClearEditText et_name;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.iv_head)
    ImageView iv_head;
    private SearchContactResult result;
    private SaveContactContract.Presenter presenter;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        presenter = new SaveContactPresenter(this);
        result = (SearchContactResult) getIntent().getSerializableExtra("data");

        if (result != null) {
            tv_phone.setText(result.getSlaveUsername());
            et_name.setText(result.getSlaveNickname());
            Glide.with(mContext).load(R.mipmap.my_head)
                    .centerCrop().
                    transform(new GlideCircleTransform(this))
                    .into(iv_head);
        }
    }

    @OnClick(R.id.btn_add)
    public void clickAdd(View view) {
        result.setSlaveNickname(et_name.getText().toString());
        presenter.saveContact(DataUtil.getToken(mContext), result);
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
    public void fail(String err) {
        Toast.makeText(mContext, err, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void success() {
        KeyboardUtil.HideKeyboard(et_name);
        EventBus.getDefault().post("");
        Toast.makeText(mContext, "添加成功", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_scan_contact_info;
    }

    @Override
    protected String getTitleStr() {
        return "联系人信息";
    }
}
