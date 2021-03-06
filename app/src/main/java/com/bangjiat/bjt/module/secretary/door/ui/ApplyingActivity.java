package com.bangjiat.bjt.module.secretary.door.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.module.main.ui.activity.BaseColorToolBarActivity;
import com.bangjiat.bjt.module.secretary.contact.beans.BuildUserInfo;
import com.bangjiat.bjt.module.secretary.door.contract.BuildUserInfoContract;
import com.bangjiat.bjt.module.secretary.door.presenter.BuildUserInfoPresenter;
import com.orhanobut.logger.Logger;

import butterknife.OnClick;

/**
 * 入驻申请 审核中
 */
public class ApplyingActivity extends BaseColorToolBarActivity implements BuildUserInfoContract.View {
    private String phone;
    private boolean isCall;
    private BuildUserInfoContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new BuildUserInfoPresenter(this);
        presenter.getInfo(DataUtil.getToken(mContext));
    }

    @OnClick(R.id.tv_contact_admin)
    public void clickAdmin(View view) {
        if (phone != null)
            dialPhone(phone);
        else {
            presenter.getInfo(DataUtil.getToken(mContext));
            isCall = true;
        }
    }

    /**
     * 拨打电话（跳转到拨号界面，用户手动点击拨打）
     *
     * @param phoneNum 电话号码
     */
    public void dialPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }

    @OnClick(R.id.btn_return)
    public void clickReturn(View view) {
        finish();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_applying;
    }

    @Override
    protected String getTitleStr() {
        return "入驻申请";
    }


    @Override
    public void error(String err) {
        Logger.e(err);
    }

    @Override
    public void success(BuildUserInfo info) {
        if (info != null) {
            Logger.d(info.toString());
            phone = info.getContactWay();
            if (isCall) {
                dialPhone(phone);
            }
        }
    }
}
