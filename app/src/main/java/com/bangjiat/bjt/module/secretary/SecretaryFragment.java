package com.bangjiat.bjt.module.secretary;

import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

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
import com.bangjiat.bjt.module.secretary.workers.ui.WorkersManageActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dou361.dialogui.DialogUIUtils;
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
    private IntoBuildingContract.Presenter presenter;
    private String token;
    private boolean isApply;
    private boolean isInto;
    private Dialog dialog;
    private int status;

    protected void initView() {
        presenter = new IntoBuildingPresenter(this);
        token = DataUtil.getToken(mContext);
        isInto = DataUtil.isIntoBuilding(mContext);

        Glide.with(this).load(R.mipmap.door).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().into(iv_door);
        Glide.with(this).load(R.mipmap.service).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().into(iv_service);
        Glide.with(this).load(R.mipmap.message).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().into(iv_message);
        Glide.with(this).load(R.mipmap.contact_us).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().into(iv_contact_us);
        Glide.with(this).load(R.mipmap.pic_people).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().into(iv_people);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isInto) {
            presenter.isIntoBuilding(token);
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_secretary;
    }

    @OnClick(R.id.card_door)
    public void clickDoor(View view) {
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
        dialog = DialogUIUtils.showLoadingVertical(mContext, "加载中").show();
        dialog.setCancelable(false);
    }

    @Override
    public void dismissDialog() {
        if (dialog != null)
            dialog.dismiss();
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
            status = result.getStatus();
            Logger.d(result.toString());
            if (status == 2) {
                isInto = true;
                DataUtil.setIntoBuilding(mContext);
            }
        }
    }
}

