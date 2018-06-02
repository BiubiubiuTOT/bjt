package com.bangjiat.bjt.module.secretary.communication.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bangjiat.bjt.R;
import com.bangjiat.bjt.common.DataUtil;
import com.bangjiat.bjt.module.main.ui.activity.BaseToolBarActivity;
import com.bangjiat.bjt.module.secretary.communication.contract.DealBoxContract;
import com.bangjiat.bjt.module.secretary.communication.presenter.DealBoxPresenter;
import com.bangjiat.bjt.module.secretary.contact.view.ContactListActivity;
import com.dou361.dialogui.DialogUIUtils;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

import static com.bangjiat.bjt.module.secretary.communication.ui.BoxActivity.WRITE_EMAIL;

public class MainActivity extends BaseToolBarActivity implements DealBoxContract.View {
    @BindView(R.id.tv_unread_counts)
    TextView tv_unread_counts;

    private Dialog dialog;
    private DealBoxContract.Presenter presenter;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        presenter = new DealBoxPresenter(this);
        token = DataUtil.getToken(mContext);
        presenter.getUnReadCounts(token);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main2;
    }

    @OnClick(R.id.ll_receive)
    public void clickReceive(View view) {
        Intent intent = new Intent(mContext, BoxActivity.class);
        intent.putExtra("type", 0);
        startActivity(intent);
    }

    @OnClick(R.id.tv_contact)
    public void clickContact(View view) {
        startActivity(new Intent(mContext, ContactListActivity.class));
    }

    @OnClick(R.id.ll_send)
    public void clickSend(View view) {
        Intent intent = new Intent(mContext, BoxActivity.class);
        intent.putExtra("type", 1);
        startActivity(intent);
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        TextView tv_title = toolbar.findViewById(R.id.toolbar_title);
        ImageView image = toolbar.findViewById(R.id.toolbar_image);
        image.setImageResource(R.mipmap.email);
        tv_title.setText("信息交流");
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(mContext, WriteEmailActivity.class), WRITE_EMAIL);
            }
        });

        toolbar.setNavigationIcon(R.mipmap.back_white);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == WRITE_EMAIL) {
                presenter.getUnReadCounts(token);
            }
        }
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
    public void fail(String err) {
        Logger.e(err);
    }

    @Override
    public void deleteOutBoxSuccess() {

    }

    @Override
    public void deleteInBoxSuccess() {

    }

    @Override
    public void markBoxSuccess() {

    }

    @Override
    public void getUnReadCountsSuccess(String s) {
        if (!s.equals("0"))
            tv_unread_counts.setText(s);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUnReadCountsChange(String s) {
        presenter.getUnReadCounts(token);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
