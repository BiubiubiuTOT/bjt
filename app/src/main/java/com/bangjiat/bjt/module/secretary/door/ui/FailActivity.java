package com.bangjiat.bjt.module.secretary.door.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.module.main.ui.activity.BaseColorToolBarActivity;
import com.bangjiat.bjt.module.secretary.contact.beans.BuildUserInfo;
import com.bangjiat.bjt.module.secretary.door.beans.IsIntoBuildingResult;
import com.bangjiat.bjt.module.secretary.door.contract.BuildUserInfoContract;
import com.bangjiat.bjt.module.secretary.door.contract.IntoBuildingContract;
import com.bangjiat.bjt.module.secretary.door.presenter.BuildUserInfoPresenter;
import com.bangjiat.bjt.module.secretary.door.presenter.IntoBuildingPresenter;
import com.orhanobut.logger.Logger;

import butterknife.OnClick;

/**
 * 入驻申请 审核未通过
 */
public class FailActivity extends BaseColorToolBarActivity implements BuildUserInfoContract.View, IntoBuildingContract.View {
    private String phone;
    private boolean isCall;
    private BuildUserInfoContract.Presenter presenter;
    private IntoBuildingContract.Presenter isIntoPresenter;
    private String code;
    private boolean isCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isIntoPresenter = new IntoBuildingPresenter(this);
        presenter = new BuildUserInfoPresenter(this);
        presenter.getInfo(DataUtil.getToken(mContext));
        isIntoPresenter.isIntoBuilding(DataUtil.getToken(mContext));
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

    @OnClick(R.id.btn_reapply)
    public void clickReApply(View view) {
        if (code != null) {
            start();
        } else {
            presenter.getInfo(DataUtil.getToken(mContext));
            isCode = true;
        }

    }

    private void start() {
        Intent intent = new Intent(mContext, IntoBuildingActivity.class);
        intent.putExtra("data", code);
        startActivity(intent);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_apply_fail;
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

    @Override
    public void showDialog() {

    }

    @Override
    public void dismissDialog() {

    }

    @Override
    public void success() {

    }

    @Override
    public void fail(String err) {

    }

    @Override
    public void getIsIntoBuildingSuccess(IsIntoBuildingResult result) {
        if (result != null) {
            this.code = result.getCode();
            if (isCode)
                start();
        }
    }
}
