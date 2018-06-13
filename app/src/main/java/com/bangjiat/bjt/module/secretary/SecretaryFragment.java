package com.bangjiat.bjt.module.secretary;

import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.BaseFragment;
import com.bangjiat.bjt.common.Constants;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.module.home.company.ui.AddOrSelectCompanyActivity;
import com.bangjiat.bjt.module.secretary.contact.view.ContactListActivity;
import com.bangjiat.bjt.module.secretary.door.beans.IsIntoBuildingResult;
import com.bangjiat.bjt.module.secretary.door.contract.IntoBuildingContract;
import com.bangjiat.bjt.module.secretary.door.presenter.IntoBuildingPresenter;
import com.bangjiat.bjt.module.secretary.door.ui.ApplyingActivity;
import com.bangjiat.bjt.module.secretary.door.ui.FailActivity;
import com.bangjiat.bjt.module.secretary.door.ui.IntoBuildingActivity;
import com.bangjiat.bjt.module.secretary.service.ui.MainActivity;
import com.bangjiat.bjt.module.secretary.service.ui.ServiceHistoryActivity;
import com.bangjiat.bjt.module.secretary.workers.ui.WorkersManageActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnClick;


public class SecretaryFragment extends BaseFragment implements IntoBuildingContract.View {
    @BindView(R.id.iv_door)
    ImageView iv_door;
    @BindView(R.id.iv_contact_us)
    ImageView iv_contact_us;
    @BindView(R.id.iv_service)
    ImageView iv_service;
    @BindView(R.id.iv_message)
    ImageView iv_message;
    @BindView(R.id.iv_people)
    ImageView iv_people;
    @BindView(R.id.ll_admin)
    LinearLayout ll_admin;
    @BindView(R.id.tv_door)
    TextView tv_door;
    @BindView(R.id.tv_service)
    TextView tv_service;
    @BindView(R.id.tv_into_building)
    TextView tv_into;

    private IntoBuildingContract.Presenter presenter;
    private String token;
    private boolean isApply;
    private boolean isInto;
    private Dialog dialog;
    private int status;
    private String phone;

    protected void initView() {
        presenter = new IntoBuildingPresenter(this);
        token = DataUtil.getToken(mContext);

        Glide.with(this).load(R.mipmap.message).centerCrop().dontAnimate().diskCacheStrategy(DiskCacheStrategy.ALL).into(iv_message);
        Glide.with(this).load(R.mipmap.contact_us).centerCrop().dontAnimate().diskCacheStrategy(DiskCacheStrategy.ALL).into(iv_contact_us);
        Glide.with(this).load(R.mipmap.pic_people).centerCrop().dontAnimate().diskCacheStrategy(DiskCacheStrategy.ALL).into(iv_people);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Constants.isBuildingAdmin()) {//楼宇管理员
            show();

            tv_door.setText("·门禁审批·");
            tv_service.setText("·服务审批·");
        } else if (Constants.isCompanyAdmin()) {//公司管理员
            show();

            tv_door.setText("·门禁申请·");
            tv_service.setText("·服务申请·");

            isInto = DataUtil.isIntoBuilding(mContext);//是否入驻楼宇
            if (!isInto)
                presenter.isIntoBuilding(token);
        } else {
            ll_admin.setVisibility(View.GONE);
        }

    }

    private void show() {
        ll_admin.setVisibility(View.VISIBLE);
        Glide.with(this).load(R.mipmap.door).centerCrop().dontAnimate().diskCacheStrategy(DiskCacheStrategy.ALL).into(iv_door);
        Glide.with(this).load(R.mipmap.service).centerCrop().dontAnimate().diskCacheStrategy(DiskCacheStrategy.ALL).into(iv_service);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_secretary;
    }

    @OnClick(R.id.tv_into_building)
    public void clickInto(View view) {
        if (!isApply) {
            startActivity(new Intent(mContext, IntoBuildingActivity.class));
        }
    }

    @OnClick(R.id.card_door)
    public void clickDoor(View view) {
        if (Constants.isBuildingAdmin()) {
            startActivity(new Intent(mContext, com.bangjiat.bjt.module.secretary.door.ui.MainActivity.class));
            return;
        }

        if (Constants.isBuildingAdmin() && !Constants.isCompanyAdmin()) {
            startActivity(new Intent(mContext, com.bangjiat.bjt.module.secretary.door.ui.HistoryActivity.class));
            return;
        }

        if (isInto) {
            startActivity(new Intent(mContext, com.bangjiat.bjt.module.secretary.door.ui.MainActivity.class));
            return;
        }

        if (isApply) {
            switch (status) {
                case 1:
                    startActivity(new Intent(mContext, ApplyingActivity.class));
                    break;
                case 2:
                    startActivity(new Intent(mContext, com.bangjiat.bjt.module.secretary.door.ui.MainActivity.class));
                    break;
                case 3:
                    startActivity(new Intent(mContext, FailActivity.class));
                    break;
            }
        } else
            startActivity(new Intent(mContext, IntoBuildingActivity.class));
    }

    @OnClick(R.id.card_service)
    public void clickCardService(View view) {
        if (Constants.isBuildingAdmin()) {
            startActivity(new Intent(mContext, MainActivity.class));
            return;
        }

        if (Constants.isBuildingAdmin() && !Constants.isCompanyAdmin()) {
            startActivity(new Intent(mContext, ServiceHistoryActivity.class));
            return;
        }
        if (isInto) {
            startActivity(new Intent(mContext, MainActivity.class));
            return;
        }

        if (isApply) {
            switch (status) {
                case 1:
                    startActivity(new Intent(mContext, ApplyingActivity.class));
                    break;
                case 2:
                    startActivity(new Intent(mContext, MainActivity.class));
                    break;
                case 3:
                    startActivity(new Intent(mContext, FailActivity.class));
                    break;
            }
        } else
            startActivity(new Intent(mContext, IntoBuildingActivity.class));
    }

    @OnClick(R.id.card_message)
    public void clickMessage(View view) {
        startActivity(new Intent(mContext, com.bangjiat.bjt.module.secretary.communication.ui.MainActivity.class));
    }

    @OnClick(R.id.card_contact_us)
    public void clickContactUs(View view) {
        startActivity(new Intent(mContext, ContactListActivity.class));
    }

    @OnClick(R.id.card_people)
    public void clickPeople(View view) {
        startActivity(new Intent(mContext, Constants.isIntoCompany() ? WorkersManageActivity.class :
                AddOrSelectCompanyActivity.class));
    }

    @Override
    public void showDialog() {
//        dialog = DialogUIUtils.showLoadingVertical(mContext, "加载中").show();
//        dialog.setCancelable(false);
    }

    @Override
    public void dismissDialog() {
//        if (dialog != null)
//            dialog.dismiss();
    }

    @Override
    public void success() {

    }

    @Override
    public void fail(String err) {
        Logger.e(err);
    }

    @Override
    public void getIsIntoBuildingSuccess(IsIntoBuildingResult result) {
        isApply = result != null;
        if (result != null) {
            phone = result.getApplyer();
            Logger.d(result.toString());

            status = result.getStatus();
            Logger.d(result.toString());
            if (status == 2) {
                isInto = true;
                DataUtil.setIntoBuilding(mContext);
            }
        }
        if (!isApply && !Constants.isBuildingAdmin())
            tv_into.setVisibility(View.VISIBLE);
    }
}

